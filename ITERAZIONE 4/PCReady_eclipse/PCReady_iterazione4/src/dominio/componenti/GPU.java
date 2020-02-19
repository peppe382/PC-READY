package dominio.componenti;

import dominio.Componente;

public class GPU extends Componente {
	
	private int slot;

	public GPU(String nome, double prezzo, int consumo_energetico, String descrizione, int slot) {
		super(nome, prezzo, consumo_energetico, descrizione, "GPU");
		this.setSlot(slot);
	}
	
	
	public GPU(Componente comp, int slot) {
		super(comp.getId(), comp.getNome(), comp.getPrezzo(), comp.getConsumo_energetico(), comp.getDescrizione(), "GPU");
		this.setSlot(slot);
	}


	public GPU(int slot) { // Costruttore di default
		super();
		super.setCategoria("GPU");
		this.setSlot(slot);
	}


	public int getSlot() {
		return slot;
	}


	public void setSlot(int slot) {
		this.slot = slot;
	}
	

}
