package dominio;

import java.util.*;

public class Configurazione extends Componente {

	private List<Componente> listaComponenti; // lista di tutte le Componente presenti

	
	//  Costruttori
	
	public Configurazione(int id, String nome, double prezzo, int consumo_energetico, String descrizione,
			String categoria) {
		super(nome, prezzo, consumo_energetico, descrizione, categoria);
		this.listaComponenti = new ArrayList<Componente>();
	}


	public Configurazione() { // Costruttore di default
		super();
		super.setCategoria("Configurazione");
	}

	
	
	
	//  Getters e Setters

	public List<Componente> getListaComponenti() {
		return listaComponenti;
	}

	public void setListaComponenti(List<Componente> listaComponenti) {
		this.listaComponenti = listaComponenti;
	}
	
	
	
	//  Funzioni di progetto
	
	public String aggiungiComponenteInConfigurazione(Componente componenteCorrente) {
		this.listaComponenti.add(componenteCorrente);
		aggiornaAttributi(componenteCorrente);
		return this.toString();
	}
	
	private void aggiornaAttributi(Componente componenteCorrente) {
		super.setConsumo_energetico((super.getConsumo_energetico() + componenteCorrente.getConsumo_energetico()));
		super.setPrezzo((super.getConsumo_energetico() + componenteCorrente.getConsumo_energetico()));
	}
	
	public String riepilogaConfigurazione() {
		return this.toString();
	}

	//  toString
	public String toString() {
		String str = "";
		str += "Conf. #"+super.getId()+"\n";
		str += "EUR "+super.getPrezzo()+", consumo: "+super.getConsumo_energetico()+"W\n\n";
		str += "Composto da: ----------------------\n";
		for(int i = 0; i < listaComponenti.size(); i++) {
			str += listaComponenti.get(i).toString();
			str += "\n\n";
		}
		return str;
	}
	
	
	
	
	
	
	

	

}
