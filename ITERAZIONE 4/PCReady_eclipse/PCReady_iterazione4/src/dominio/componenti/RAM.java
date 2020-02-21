package dominio.componenti;

import dominio.Componente;

public class RAM extends Componente {
	
	private String tipologia;
	private int frequenza;

	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public RAM(String nome, double prezzo, int consumo_energetico, String descrizione, String tipologia, int frequenza) {
		super(nome, prezzo, consumo_energetico, descrizione, "RAM");
		this.setTipologia(tipologia);
		this.setFrequenza(frequenza);
	}
	
	public RAM(Componente comp, String tipologia, int frequenza) {
		super(comp.getId(), comp.getNome(), comp.getPrezzo(), comp.getConsumo_energetico(), comp.getDescrizione(), "RAM");
		this.setTipologia(tipologia);
		this.setFrequenza(frequenza);
	}

	public RAM(String tipologia, int frequenza) { // Costruttore di default
		super();
		super.setCategoria("RAM");
		this.setTipologia(tipologia);
		this.setFrequenza(frequenza);
	}

	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public int getFrequenza() {
		return frequenza;
	}

	public void setFrequenza(int frequenza) {
		this.frequenza = frequenza;
	}

}
