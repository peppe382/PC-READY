package dominio.componenti;

import dominio.Componente;

public class Case extends Componente {
	
	private String formFactor;

	public Case(String nome, double prezzo, int consumo_energetico, String descrizione, String formFactor) {
		super(nome, prezzo, consumo_energetico, descrizione, "Case");
		this.setFormFactor(formFactor);
	}


	public Case(String formFactor) { // Costruttore di default
		super();
		super.setCategoria("Case");
		this.setFormFactor(formFactor);
	}
	
	public String getFormFactor() {
		return this.formFactor;
	}
	
	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}
}
