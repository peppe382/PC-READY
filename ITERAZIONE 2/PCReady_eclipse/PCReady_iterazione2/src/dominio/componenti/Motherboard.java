package dominio.componenti;

import dominio.Componente;

public class Motherboard extends Componente {
	
	private String socket;
	private String formFactor;
	private String tipologiaRAM;

	public Motherboard(String nome, double prezzo, int consumo_energetico, String descrizione, String socket, String formFactor, String tipologiaRAM) {
		super(nome, prezzo, consumo_energetico, descrizione, "Motherboard");
		this.setSocket(socket);
		this.setFormFactor(formFactor);
		this.setTipologiaRAM(tipologiaRAM);
	}
	
	
	public Motherboard(Componente comp, String socket, String formFactor, String tipologiaRAM) {
        super(comp.getId(), comp.getNome(),comp.getPrezzo(),comp.getConsumo_energetico(),comp.getDescrizione(), "Motherboard");
        this.setSocket(socket);
        this.setFormFactor(formFactor);
        this.setTipologiaRAM(tipologiaRAM);
    }


	public Motherboard(String socket, String formFactor, String tipologiaRAM) { // Costruttore di default
		super();
		super.setCategoria("Motherboard");
		this.setSocket(socket);
		this.setFormFactor(formFactor);
		this.setTipologiaRAM(tipologiaRAM);
	}


	public String getSocket() {
		return socket;
	}


	public void setSocket(String socket) {
		this.socket = socket;
	}


	public String getFormFactor() {
		return formFactor;
	}


	public void setFormFactor(String formFactor) {
		this.formFactor = formFactor;
	}


	public String getTipologiaRAM() {
		return tipologiaRAM;
	}


	public void setTipologiaRAM(String tipologiaRAM) {
		this.tipologiaRAM = tipologiaRAM;
	}
}
