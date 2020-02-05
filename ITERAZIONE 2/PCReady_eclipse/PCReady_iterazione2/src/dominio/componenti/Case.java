package dominio.componenti;

import dominio.Componente;

public class Case extends Componente {
	
	private String formFactor;
	private int slot;

	public Case(String nome, double prezzo, int consumo_energetico, String descrizione, String formFactor, int slot) {
		super(nome, prezzo, consumo_energetico, descrizione, "Case");
		this.setFormFactor(formFactor);
		this.setSlot(slot);
	}


	public Case(String formFactor, int slot) { // Costruttore di default
		super();
		super.setCategoria("Case");
		this.setFormFactor(formFactor);
		this.setSlot(slot);
	}
	
	public String getFormFactor() {
		return this.formFactor;
	}
	
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}


	public int getSlot() {
		return slot;
	}


	public void setSlot(int slot) {
		this.slot = slot;
	}
}
