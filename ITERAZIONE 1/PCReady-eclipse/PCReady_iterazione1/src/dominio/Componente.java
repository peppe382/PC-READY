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
public class Componente {

	private int id; // id univoco del Componente
	private String nome; // nome da mostrare all'utente
	private double prezzo; // prezzo in Euro
	private int consumo_energetico; // Consumo in Watt
	private String descrizione; // Descrizione completa
	
	private List<CopiaComponente> listaCopie; // Elenco di tutte le copie disponibili del componente
	
	
	/********** COSTRUTTORI *********/
	
	/**
	 * Costruttore completo per componente pre-esistente
	 * @param id
	 * @param nome
	 * @param prezzo
	 * @param consumo_energetico
	 * @param descrizione
	 */
	public Componente(int id, String nome, double prezzo, int consumo_energetico, String descrizione) {
		this.setId(id);
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setConsumo_energetico(consumo_energetico);
		this.setDescrizione(descrizione);
		
		this.setListaCopie(new LinkedList<CopiaComponente>());
	}

	/**
	 * Costruttore completo per componente nuovo, id auto-generato
	 * @param id
	 * @param nome
	 * @param prezzo
	 * @param consumo_energetico
	 * @param descrizione
	 */
	public Componente(String nome, int consumo_energetico, double prezzo, String descrizione){
		//Costruttore che corrisponde alla funzione aggiungiComponente del diagramma delle classi di progetto
		this.nome = nome;
		this.consumo_energetico = consumo_energetico;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.id = (int) Counter.getNextNumber(); //Id univoco
		
		this.setListaCopie(new LinkedList<CopiaComponente>());
	}
	
	
	/********** FUNZIONI di PROGETTO **********/
	
	/**
	 * Aggiunge una CopiaComponente appena generata alla lista di copie disponibili
	 * @param copia: la CopiaComponente generata
	 */
	public void aggiungiInListaCopie(CopiaComponente copia) {
		this.listaCopie.add(copia);
	}
	
	/**
	 * Come "aggiungiInListaCopie", ma per più copie auto-generate
	 * @param numero: il numero di CopiaComponente da aggiungere
	 */
	public void aggiungiCopie(int numero) {
		for (int i = 0; i<numero; i++) {
			this.aggiungiInListaCopie(new CopiaComponente());
		}
	}
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getConsumo_energetico() {
		return consumo_energetico;
	}

	public void setConsumo_energetico(int consumo_energetico) {
		this.consumo_energetico = consumo_energetico;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<CopiaComponente> getListaCopie() {
		return listaCopie;
	}

	public void setListaCopie(List<CopiaComponente> listaCopie) {
		this.listaCopie = listaCopie;
	}
	
	public String toString() {
		String str = "";
		str += "#"+this.getId()+" : "+this.getNome()+"\n";
		str += "EUR "+this.getPrezzo()+", consumo: "+this.getConsumo_energetico()+"W\n";
		str += "DESC: "+this.getDescrizione()+"\n\n";
		str += "Copie disponibili: "+this.getListaCopie().size();
		return str;
	}
	
}
