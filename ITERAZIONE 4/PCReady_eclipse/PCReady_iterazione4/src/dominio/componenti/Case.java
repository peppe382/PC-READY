package dominio.componenti;

import dominio.Componente;

public class Case extends Componente {
	
	private String formFactorMotherboard;
	private String formFactorPSU;
	private int slot;

	public Case(String nome, double prezzo, int consumo_energetico, String descrizione, String formFactorMotherboard, String formFactorPSU, int slot) {
		super(nome, prezzo, consumo_energetico, descrizione, "Case");
		this.setFormFactorMotherboard(formFactorMotherboard);
		this.setFormFactorPSU(formFactorPSU);
		this.setSlot(slot);
	}

	
	public Case(Componente comp, String formFactorMotherboard, String formFactorPSU, int slot) {
		super(comp.getId(), comp.getNome(), comp.getPrezzo(), comp.getConsumo_energetico(), comp.getDescrizione(), "Case");
		this.setFormFactorMotherboard(formFactorMotherboard);
		this.setFormFactorPSU(formFactorPSU);
		this.setSlot(slot);
	}
	

	public Case(String formFactorMotherboard, String formFactorPSU, int slot) { // Costruttore di default
		super();
		super.setCategoria("Case");
		this.setFormFactorMotherboard(formFactorMotherboard);
		this.setFormFactorPSU(formFactorPSU);
		this.setSlot(slot);
	}
	


	public String getFormFactorMotherboard() {
		return formFactorMotherboard;
	}


	public void setFormFactorMotherboard(String formFactorMotherboard) {
		this.formFactorMotherboard = formFactorMotherboard;
	}


	public String getFormFactorPSU() {
		return formFactorPSU;
	}


	public void setFormFactorPSU(String formFactorPSU) {
		this.formFactorPSU = formFactorPSU;
	}
	
	public int getSlot() {
		return slot;
	}


	public void setSlot(int slot) {
		this.slot = slot;
	}
}
