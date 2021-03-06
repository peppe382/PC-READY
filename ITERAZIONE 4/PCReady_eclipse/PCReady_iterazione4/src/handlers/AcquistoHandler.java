package handlers;

import java.util.*;

import org.junit.jupiter.api.DisplayName;

import dominio.*;
import dominio.componenti.*;


public class AcquistoHandler {

	private Cliente clienteCorrente;
	private Carrello carrello;
	private Catalogo catalogo;
	private Ordine ordineCorrente;
	private Componente componenteCorrente;
	private Map<Integer, Componente> mappaCorrente;
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public AcquistoHandler(Catalogo catalogo, Cliente cliente) {
		this.catalogo = catalogo;
		this.carrello = new Carrello();
		this.mappaCorrente = new HashMap<Integer, Componente>();
		this.clienteCorrente = cliente;
		this.ordineCorrente = null;
		this.componenteCorrente = null;
	}
	
	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
	public Cliente getClienteCorrente() {
		return clienteCorrente;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public Ordine getOrdineCorrente() {
		return ordineCorrente;
	}

	public Componente getComponenteCorrente() {
		return componenteCorrente;
	}

	public Map<Integer, Componente> getMappaCorrente() {
		return mappaCorrente;
	}

	public void setClienteCorrente(Cliente clienteCorrente) {
		this.clienteCorrente = clienteCorrente;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public void setOrdineCorrente(Ordine ordineCorrente) {
		this.ordineCorrente = ordineCorrente;
	}

	public void setComponenteCorrente(Componente componenteCorrente) {
		this.componenteCorrente = componenteCorrente;
	}

	public void setMappaCorrente(Map<Integer, Componente> mappaCorrente) {
		this.mappaCorrente = mappaCorrente;
	}
	
	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO
	
	// Inizializza il Carrello
	public void iniziaAcquisto() {
		this.carrello = new Carrello();
	}
	
	// Usa come listino corrente una lista di Componenti di una sola Categoria
	public Map<Integer, Componente> selezionaCategoria(String idCategoria) {
		try {
			return this.mappaCorrente = this.catalogo.ottieniComponentiByCategoria(idCategoria);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Seleziona un singolo Componente dal listino corrente
	public Componente selezionaProdotto(int idProdotto) {
		try {
			this.componenteCorrente  = this.mappaCorrente.get(idProdotto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.componenteCorrente ;
	}
	
	// Aggiungi un Componente al Carrello
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
			this.carrello.aggiungiComponente(this.componenteCorrente, listaCopie);
			str += "\nCARRELLO RIEPILOGO \nPREZZO TOTALE: " + this.carrello.getPrezzoTotale()+ "\n";
			str += "\nNUMERO PEZZI: " + this.carrello.getNumeroPezzi() + "\n";
		}
		else return str = "\nNON CI SONO COPIE DISPONIBILI PER IL COMPONENTE SCELTO, CARRELLO INVARIATO \n" + "L'IMPORTO AMMONTA A: " + this.carrello.getPrezzoTotale();
		
		
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
	
	// Termina l'aggiunta di Componenti, chiudendo l'Ordine
	public Ordine terminaAcquisto(String indirizzo, String citta, int CAP) {
		if (!this.carrello.getMappaComponenti().isEmpty()) {
			this.ordineCorrente = new Ordine(this.clienteCorrente, this.carrello.getMappaComponentiCarrello() ,indirizzo, citta, CAP);
			return this.ordineCorrente;
		}
		else return null;
	}
	
	// Seleziona una modalita' di pagamento per l'Ordine
	public String selezionaModalitaDiPagamento(String metodoPagamento, int numeroCarta, int cvv) {
		String str = "";
		str = this.ordineCorrente.selezionaModalitaDiPagamento(metodoPagamento, numeroCarta, cvv);
		PCReady sistema = PCReady.getInstance();
		sistema.salvaOrdine(this.ordineCorrente, this.clienteCorrente.getEmail());
		return str;
	}
	
	// Rimuove ogni CopiaComponente dal Carrello
	public void rimuoviCopieComponente() {
		this.ordineCorrente.rimuoviCopieOrdinate();
	}
	
	// Controlla se il Carrello è vuoto
	public boolean isEmptyCarrello() {
		return this.carrello.getMappaComponentiCarrello().isEmpty();
	}
	
	// Ricerca Ordini del Cliente corrente
	public String ottieniOrdineCliente() {
		PCReady pcReady = PCReady.getInstance();
		List<Ordine> listaOrdini = pcReady.getListaOrdiniCliente(clienteCorrente.getEmail());
		if (!listaOrdini.isEmpty()) {
		String riepilogoOrdineCliente = "Ordini dell'utente "+ clienteCorrente.getEmail() +": \n\n";
			for(Ordine ordine : listaOrdini) {
				 riepilogoOrdineCliente += ordine.toString();
				 riepilogoOrdineCliente += "\n";
			}
			return riepilogoOrdineCliente;
		}
		else return "Non vi sono ordini da modificare \n";
	}
	
	// Modifica l'Ordine con nuove informazioni
	public String aggiornaInformazioni(int id, String indirizzo, String citta, int CAP) {
		
		PCReady pcReady = PCReady.getInstance();
		return pcReady.modificaOrdine(id, indirizzo, citta, CAP, clienteCorrente.getEmail());
		
	}
	
	// Elimina uno specifico Componente dal Carrello
	public String eliminaComponenteAcquisto(int idComponente) {
		String comunicazione = "";
		if (!this.carrello.getMappaComponentiCarrello().isEmpty()) {
			Componente componenteDaEliminare = this.catalogo.getComponente(idComponente);
			if (this.carrello.getMappaComponentiCarrello().containsKey(componenteDaEliminare)) {
				this.carrello.getMappaComponenti().remove(componenteDaEliminare);
				comunicazione = "Componente rimosso \n";
			} else comunicazione = "Componente da rimuovere non contenuto nella lista \n";
		} else comunicazione = "Carrello vuoto \n";
		return comunicazione;
	}
	
}
