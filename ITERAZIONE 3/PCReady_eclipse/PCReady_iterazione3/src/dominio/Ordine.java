package dominio;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Ordine {
	
	Map<Integer,Componente> mappaComponenti;
	Cliente cliente;
	private String indirizzo;
	private String citta;
	private int CAP;
	private String metodoPagamento;
	private int id;
	private int numeroCarta;
	private int cvv;  
	private static final AtomicLong counter = new AtomicLong(0);
	
	public Ordine(Cliente cliente, Map<Integer,Componente> mappaComponentiCarrello, String indirizzo, String citta, int CAP) {
		// TODO Auto-generated constructor stub 
		this.cliente = cliente;
		this.mappaComponenti = mappaComponentiCarrello;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.CAP = CAP;
		setId();
		
	}
	
	public String selezionaModalit‡DiPagamento(String metodoPagamento, int numeroCarta, int cvv){
		
		this.metodoPagamento = metodoPagamento;
		this.numeroCarta = numeroCarta;
		this.cvv = cvv;
		String infoOrdine = "Riepilogo ordine "+ id+":\n Cliente: "+cliente+":\n Indirizzo: "+indirizzo+":\n Citt‡: "+citta+":\n CAP: "+CAP+":\n Riepilogo delle componenti:\n";
		for(Integer key : mappaComponenti.keySet()) {
			Componente comp = mappaComponenti.get(key);
			infoOrdine += comp.toString();
		}
		return infoOrdine;
	}

	public void setId() {
		this.id = (int) counter.incrementAndGet();
	}
	
}
