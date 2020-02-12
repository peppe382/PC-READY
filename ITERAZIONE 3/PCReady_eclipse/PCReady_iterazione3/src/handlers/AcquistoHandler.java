package handlers;

import java.util.*;

import dominio.*;
import dominio.componenti.*;


public class AcquistoHandler {

	private Cliente clienteCorrente;
	private Carrello carrello;
	private Catalogo catalogo;
	private Ordine ordineCorrente;
	private Componente componenteCorrente;
	private Map<Integer, Componente> mappaCorrente;

	public AcquistoHandler(Catalogo catalogo, Cliente cliente) {
		this.catalogo = catalogo;
		this.carrello = new Carrello();
		this.mappaCorrente = new HashMap<Integer, Componente>();
		this.clienteCorrente = cliente;
		this.ordineCorrente = null;
		this.componenteCorrente = null;
	}
	
	public void iniziaAcquisto() {
		this.carrello = new Carrello();
	}
	
	public Map<Integer, Componente> selezionaCategoria(String idCategoria) {
		try {
			return this.mappaCorrente = this.catalogo.ottieniComponentiByCategoria(idCategoria);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Componente selezionaProdotto(int idProdotto) {
		try {
			this.componenteCorrente  = this.mappaCorrente.get(idProdotto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.componenteCorrente ;
	}
	
	
	//fai aggiungere String codice
	public String aggiungiInCarrello() {
		
		String str = "";
		int numeroDoppioni = 0;
		List<CopiaComponente> listaCopie = new ArrayList<CopiaComponente>();
		
		switch(this.componenteCorrente.getCategoria()) {
			case "Configurazione":
				Configurazione conf = (Configurazione) this.componenteCorrente;
				for (Componente elemento : conf.getListaComponenti()) {
						numeroDoppioni = this.carrello.controllaDoppioni(elemento); //Controllo i doppioni per l'elemento corrente
						CopiaComponente copiaDaAggiungere = elemento.controllaDisponibilitaCopie(numeroDoppioni);
						if (copiaDaAggiungere != null) {
							listaCopie.add(copiaDaAggiungere);
						}
						numeroDoppioni = 0; //Re-inizializzo il numero dei doppioni a zero
				}
				break;
			default: 
				numeroDoppioni = this.carrello.controllaDoppioni(this.componenteCorrente);
				CopiaComponente copiaDaAggiungere = this.componenteCorrente.controllaDisponibilitaCopie(numeroDoppioni);
				if (copiaDaAggiungere != null) {
					listaCopie.add(copiaDaAggiungere);
				}
				break;
		}
		
		if (listaCopie.size() != 0) {
			str = "\n---CARRELLO RIEPILOGO---\nPREZZO TOTALE: " + this.carrello.aggiungiComponente(this.componenteCorrente, listaCopie) + "\n";
		}
		else return str = "NON CI SONO COPIE DISPONIBILI PER IL COMPONENTE SCELTO, CARRELLO INVARIATO \n" + "L'IMPORTO AMMONTA A: " + this.carrello.getPrezzoTotale();
		
		
		str += "ECCO L'ELENCO DELLE COMPONENTI PRESENTI NEL CARRELLO: \n";
		for (Componente componente : this.carrello.getMappaComponenti().keySet()) {
			str += "ID COPIE DEL COMPONENTE "+componente.getNome();
			for (CopiaComponente copia : this.carrello.getMappaComponenti().get(componente)) {
				str += ": "+copia.getCodice()+"\n";
			}
			str += componente.toString();
		}
		return str;
		
	}
	
	
	public Ordine terminaAcquisto(String indirizzo, String citta, int CAP) {
		if (!this.carrello.getMappaComponenti().isEmpty()) {
			this.ordineCorrente = new Ordine(this.clienteCorrente, this.carrello.getMappaComponentiCarrello() ,indirizzo, citta, CAP);
			return this.ordineCorrente;
		}
		else return null;
	}
	
	
	public String selezionaModalitaDiPagamento(String metodoPagamento, int numeroCarta, int cvv) {
		String str = "";
		str = this.ordineCorrente.selezionaModalit‡DiPagamento(metodoPagamento, numeroCarta, cvv);
		PCReady sistema = PCReady.getInstance();
		sistema.salvaOrdine(this.ordineCorrente, this.clienteCorrente.getId());
		return str;
	}
	
	
	public void rimuoviCopieComponente() {
		this.ordineCorrente.rimuoviCopieOrdinate();
	}
	
	
	
}
