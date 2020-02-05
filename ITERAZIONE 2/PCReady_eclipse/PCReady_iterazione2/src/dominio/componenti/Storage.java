package dominio.componenti;

import dominio.Componente;

public class Storage extends Componente {
	
	private String memoria;
	private double dimensioni;
	private int velocita;
	private String tipologia;

	public Storage(String nome, double prezzo, int consumo_energetico, String descrizione, String memoria, double dimensioni, int velocita, String tipologia) {
		super(nome, prezzo, consumo_energetico, descrizione, "Storage");
		this.setMemoria(memoria);
		this.setDimensioni(dimensioni);
		this.setVelocita(velocita);
		this.setTipologia(tipologia);
	}


	public Storage(String memoria, double dimensioni, int velocita, String tipologia) { // Costruttore di default
		super();
		super.setCategoria("Storage");
		this.setMemoria(memoria);
		this.setDimensioni(dimensioni);
		this.setVelocita(velocita);
		this.setTipologia(tipologia);
	}


	public String getMemoria() {
		return memoria;
	}


	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}


	public double getDimensioni() {
		return dimensioni;
	}


	public void setDimensioni(double dimensioni) {
		this.dimensioni = dimensioni;
	}


	public int getVelocita() {
		return velocita;
	}


	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}


	public String getTipologia() {
		return tipologia;
	}


	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	

}
