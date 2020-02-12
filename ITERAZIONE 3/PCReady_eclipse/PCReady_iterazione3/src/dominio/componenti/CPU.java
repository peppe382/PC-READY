package dominio.componenti;

import dominio.Componente;

public class CPU extends Componente {
	
	private String socket;

	public CPU(String nome, double prezzo, int consumo_energetico, String descrizione, String socket) {
		super(nome, prezzo, consumo_energetico, descrizione, "CPU");
		this.setSocket(socket);
	}
	
	
	public CPU(Componente comp, String socket) {
		super(comp.getId(), comp.getNome(), comp.getPrezzo(), comp.getConsumo_energetico(), comp.getDescrizione(), "CPU");
		this.setSocket(socket);
	}


	public CPU(String socket) { // Costruttore di default
		super();
		super.setCategoria("CPU");
		this.setSocket(socket);
	}


	public String getSocket() {
		return socket;
	}


	public void setSocket(String socket) {
		this.socket = socket;
	}
}
