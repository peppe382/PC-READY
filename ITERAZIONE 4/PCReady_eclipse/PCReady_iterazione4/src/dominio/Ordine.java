package dominio;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Ordine {
	
	private int id;
	private Map<Componente,List<CopiaComponente>> mappaComponenti;
	private Cliente cliente;
	private String indirizzo;
	private String citta;
	private int CAP;
	private String metodoPagamento;
	private int numeroCarta;
	private int cvv;  
	private static final AtomicLong counter = new AtomicLong(0);
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public Ordine(Cliente cliente, Map<Componente, List<CopiaComponente>> mappaComponentiCarrello, String indirizzo, String citta, int CAP) {
		this.cliente = cliente;
		setMappaComponenti(mappaComponentiCarrello);
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.CAP = CAP;
		setId();
		this.metodoPagamento = null;
		this.numeroCarta = 0;
		this.cvv = 0;	
	}
	
	public Ordine(int id, Cliente cliente, Map<Componente, List<CopiaComponente>> mappaComponentiCarrello, String indirizzo, String citta, int CAP, String metodoPagamento, int numeroCarta, int cvv) {
		this.setId(id);
		this.cliente = cliente;
		this.mappaComponenti = mappaComponentiCarrello;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.CAP = CAP;
		this.metodoPagamento = metodoPagamento;
		this.numeroCarta = numeroCarta;
		this.cvv = cvv;	
	}
	
	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
	public Map<Componente, List<CopiaComponente>> getMappaComponenti() {
		return mappaComponenti;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public int getCAP() {
		return CAP;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public int getId() {
		return id;
	}

	public int getNumeroCarta() {
		return numeroCarta;
	}

	public int getCvv() {
		return cvv;
	}

	public void setMappaComponenti(Map<Componente, List<CopiaComponente>> mappaComponenti) {
		this.mappaComponenti = mappaComponenti;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCAP(int cAP) {
		CAP = cAP;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setId() {
		this.id = (int) counter.incrementAndGet();
	}

	public void setNumeroCarta(int numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	public static void setCounter(long id) {
		Ordine.counter.set(id);
	}

	public String toString() {
		String infoOrdine = "---Riepilogo ordine--- \n Id ordine: "+ id+"\n Nome cliente: "+cliente.getNome() +"\n Cognome cliente: "+ cliente.getCognome() +"\n Indirizzo: "+indirizzo+"\n Citta: "+citta+"\n CAP: "+CAP+"\n Metodo di pagamento: "+metodoPagamento+ "\n---Riepilogo delle componenti--- \n";
		for(Componente key : mappaComponenti.keySet()) {
			infoOrdine += key.toString() + "\nCOPIE SELEZIONATE: "+ mappaComponenti.get(key).toString();
		}
		return infoOrdine;
	}

	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO
	
	/**
	 * Logica di scelta della Modalita' di Pagamento
	 */
	public String selezionaModalitaDiPagamento(String metodoPagamento, int numeroCarta, int cvv){
		
		setMetodoPagamento(metodoPagamento);
		System.out.println("SALVO: "+metodoPagamento);
		setNumeroCarta(numeroCarta);
		setCvv(cvv);
		
		String infoOrdine = "---Riepilogo ordine--- \nId ordine: "+ id+"\n Nome cliente: "+cliente.getNome()
		+"\n Cognome cliente: "+ cliente.getCognome() +"\n Indirizzo: "+indirizzo+"\n Citta: "+citta+
		"\n CAP: "+CAP+"\n Metodo di pagamento:"+ metodoPagamento+"\n ---Riepilogo delle componenti--- \n";
		for(Componente key : mappaComponenti.keySet()) {
			infoOrdine += key.toString() + "\n COPIE SELEZIONATE: "+mappaComponenti.get(key).toString();
		}
		return infoOrdine;
	}
	
	/**
	 * Rimuovi tutte le Copie presenti nell'Ordine
	 */
	public void rimuoviCopieOrdinate() {
		for (Componente key : this.mappaComponenti.keySet()) {
			List<CopiaComponente> copieLoop = new ArrayList<CopiaComponente>(this.mappaComponenti.get(key));
			key.rimozioneCopie(copieLoop);
		}

	}
	
	/**
	 * Modifica/Aggiungi il recapito per la spedizione
	 */
	public void aggiornaAttributiSpedizione(String indirizzo, String citta, int CAP) {

		this.indirizzo = indirizzo;
		this.citta = citta;
		this.CAP = CAP;
		
	}
	
}
