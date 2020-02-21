package dominio.componenti;

import dominio.Componente;

public class PSU extends Componente {
	
	private int potenzaErogata;
	private String tipologia;
	private String formFactor;

	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public PSU(String nome, double prezzo, String descrizione, int potenzaErogata, String tipologia, String formFactor) {
		super(nome, prezzo, 0, descrizione, "PSU");
		this.setPotenzaErogata(potenzaErogata);
		this.setTipologia(tipologia);
		this.setFormFactor(formFactor);
	}
	
	public PSU(Componente comp, int potenzaErogata, String tipologia, String formFactor) {
		super(comp.getId(), comp.getNome(), comp.getPrezzo(), comp.getConsumo_energetico(), comp.getDescrizione(), "PSU");
		this.setPotenzaErogata(potenzaErogata);
		this.setTipologia(tipologia);
		this.setFormFactor(formFactor);
	}

	public PSU(int potenzaErogata, String tipologia, String formFactor) { // Costruttore di default
		super();
		super.setCategoria("PSU");
		this.setPotenzaErogata(potenzaErogata);
		this.setTipologia(tipologia);
		this.setFormFactor(formFactor);
	}
	
	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS

	public int getPotenzaErogata() {
		return potenzaErogata;
	}

	public void setPotenzaErogata(int potenzaErogata) {
		this.potenzaErogata = potenzaErogata;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public String getFormFactor() {
		return this.formFactor;
	}
	
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}
}
