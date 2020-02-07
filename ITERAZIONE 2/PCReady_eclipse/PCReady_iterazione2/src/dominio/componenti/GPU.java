package dominio.componenti;

import dominio.Componente;

public class GPU extends Componente {
	
	private int slot;

	public GPU(String nome, double prezzo, int consumo_energetico, String descrizione, int slot) {
		super(nome, prezzo, consumo_energetico, descrizione, "GPU");
		this.setSlot(slot);
	}


	public GPU(String formFactor) { // Costruttore di default
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
