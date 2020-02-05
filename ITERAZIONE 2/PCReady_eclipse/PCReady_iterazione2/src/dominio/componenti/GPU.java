package dominio.componenti;

import dominio.Componente;

public class GPU extends Componente {
	
	private String formFactor;

	public GPU(String nome, double prezzo, int consumo_energetico, String descrizione, String formFactor) {
		super(nome, prezzo, consumo_energetico, descrizione, "GPU");
		this.setFormFactor(formFactor);
	}


	public GPU(String formFactor) { // Costruttore di default
		super();
		super.setCategoria("GPU");
		this.setFormFactor(formFactor);
	}
	
	public String getFormFactor() {
		return this.formFactor;
	}
	
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}
}
