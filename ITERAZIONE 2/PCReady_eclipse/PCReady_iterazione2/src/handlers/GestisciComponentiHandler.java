package handlers;

import dominio.*;
import data.Parser;

public class GestisciComponentiHandler {

private Catalogo catalogo;
private Componente componenteCorrente;
	
	
	//  Costruttori 
	
	public GestisciComponentiHandler(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
	public GestisciComponentiHandler() {
		this.catalogo = Parser.createCatalogo();
	}

	
	
	//  Getters e Setters 
	
	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
	public void setComponenteCorrente(Componente componenteCorrente) {
		this.componenteCorrente = componenteCorrente;
	}
	
	public Componente getComponenteCorrente() {
		return componenteCorrente;
	}
	
	//  Funzioni di Progetto
	
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione) {
		this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
		this.catalogo.aggiungiInCatalogo(this.componenteCorrente);
	}
	
	public String creaCopie (int numero) {
		return this.componenteCorrente.aggiungiCopie(numero);
	}
	
	public Componente selezionaComponente (int codice, String categoria) {
		return this.catalogo.getComponente(codice, categoria);
	}
	
	
}
