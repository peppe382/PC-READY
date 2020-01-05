package dominio;

import java.util.*;

import Utility.Counter;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class Configurazione {

	private int id;
	private double prezzo_tot;
	private int consumo_energetico;
	private List<Componente> listaComponenti;
	
	

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
	
	
	
	public Configurazione(int id, double prezzo_tot, int consumo_energetico, List<Componente> listaComponenti) {
		
		this.id = id;
		this.prezzo_tot = prezzo_tot;
		this.consumo_energetico = consumo_energetico;
		this.listaComponenti = listaComponenti;
		
	}

	public Configurazione(){
		
		this.id = (int) Counter.getNextNumber();
		listaComponenti = new LinkedList<>();

	}
	
	public void addComponente(Componente componente){
		
		listaComponenti.add(componente);
		
	}
	
	public void aggiornaAttributi(Componente componente){
		
		this.consumo_energetico += componente.getConsumo_energetico();
		this.prezzo_tot += componente.getPrezzo();
		
	}
	
	public boolean controllaConfigurazione(){
		
		return true;
		
	}
	
	public List<Componente> getComponenti(){
		
		return this.listaComponenti;
		
	}
	
	public Bundle generaBundle(String nome, String descrizione, double sconto){
		
		Bundle bundle = new Bundle(nome,descrizione,sconto);
		
		return bundle;
	}
	
}
