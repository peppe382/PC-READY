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

	private int id;
	private double prezzo;
	private int consumo_energetico;
	private String descrizione;
	private String nome;
	
	private List<CopiaComponente> listaCopie;

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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//
	//Funzioni previste dal diagramma delle classi di progetto//
	//

	public Componente(String nome, int consumo_energetico, double prezzo, String descrizione){
		//Questo costruttore risulta essere la funzione aggiungiComponente
		this.nome = nome;
		this.consumo_energetico = consumo_energetico;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.id = (int) Counter.getNextNumber(); //Id univoco
		this.listaCopie = new LinkedList<CopiaComponente>(); //Corrisponde alla funzione "inizializzaListaCopie()"
	}
	
	public void aggiungiCopie(int numero) {
		for (int i = 0; i<numero; i++) {
			CopiaComponente newCopia;
			this.listaCopie.add(newCopia = new CopiaComponente()); //Corrisponde alla funzione aggiungiInListaCopie 
		}
	}
	
	
	
}
