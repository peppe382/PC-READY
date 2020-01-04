package dominio;

import Utility.Counter;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class Bundle {

	private int id;
	private String nome;
	private String descrizione;
	private double sconto;
	
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getSconto() {
		return sconto;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

	public Bundle(String nome, String descrizione, double sconto) {
		//Corrisponde alla funzione createBundle del diagramma delle classi di progetto
		this.nome = nome;
		this.descrizione = descrizione;
		this.sconto = sconto;
		this.id = (int) Counter.getNextNumber();
	}
	
	
	
	
	
	
}
