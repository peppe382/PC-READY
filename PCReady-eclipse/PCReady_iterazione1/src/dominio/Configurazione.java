package dominio;

import java.util.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class Configurazione {

	private int id; // id univoco della Configurazione
	private double prezzo_tot; // prezzo finale della Configurazione all'acquisto, in Euro
	private int consumo_energetico; // consumo totale, in Watt
	
	private List<Componente> listaComponenti; // lista di tutte le Componente presenti
	
	
	/********** COSTRUTTORI *********/
	
	public Configurazione(int id) {
		this.setId(id);
		this.setPrezzo(0);
		this.setConsumo(0);
		this.inizializzaListaComponenti();
	}
	
	public Configurazione(){
		listaComponenti = new LinkedList<>();
	}
	
	
	/********** FUNZIONI di PROGETTO **********/
	
	public static Configurazione generaConfigurazione() {
		return new Configurazione();
	}
	
	public void inizializzaListaComponenti() {
		this.listaComponenti = new LinkedList<Componente>();
	}
	
	public void addComponente(Componente c) {
		this.listaComponenti.add(c);
		this.aggiornaAttributi();
	}
	
	public void aggiornaAttributi() {
		double prezzo = 0;
		int consumo = 0;
		for(int i = 0; i < this.listaComponenti.size(); i++) {
			Componente comp = this.listaComponenti.get(i);
			prezzo += comp.getPrezzo();
			consumo += comp.getConsumo_energetico();
		}
		this.setPrezzo(prezzo);
		this.setConsumo(consumo);
	}

	public void aggiornaAttributi(Componente componente){
		this.consumo_energetico += componente.getConsumo_energetico();
		this.prezzo_tot += componente.getPrezzo();
	}
	
	public boolean controllaConfigurazione() {
		return true;
	}
	
	public List<Componente> getComponenti(){
		return this.listaComponenti;
	}
	
	public void generaBundle(String nome, String descrizione, double prezzo){
		Bundle bundle = new Bundle(nome,descrizione,prezzo);	
	}
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrezzo() {
		return prezzo_tot;
	}

	public void setPrezzo(double prezzo_tot) {
		this.prezzo_tot = prezzo_tot;
	}

	public int getConsumo() {
		return consumo_energetico;
	}

	public void setConsumo(int consumo_energetico) {
		this.consumo_energetico = consumo_energetico;
	}
	

	public String toString() {
		String str = "";
		str += "Conf. #"+this.getId()+"\n";
		str += "EUR "+this.getPrezzo()+", consumo: "+this.getConsumo()+"W\n\n";
		str += "Composto da: ----------------------\n";
		for(int i = 0; i < listaComponenti.size(); i++) {
			str += listaComponenti.get(i).toString();
			str += "\n\n";
		}
		return str;
	}

}
