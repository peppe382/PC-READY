package handlers;

import dominio.*;
import dominio.componenti.*;
import data.Parser;

public class GestisciComponentiHandler {

	private static GestisciComponentiHandler singleton;
	
	private Catalogo catalogo;
	private Componente componenteCorrente;
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI e SINGLETON
	
	public GestisciComponentiHandler() {
		this.catalogo = Parser.createCatalogo();
		this.componenteCorrente = null;
	}
	
	public static synchronized GestisciComponentiHandler getInstance() {
		if(singleton == null) {
			singleton = new GestisciComponentiHandler();
		}
		return singleton;
	}
	
	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
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
	
	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO

	// Salva il Catalogo su database
	public void salvaCatalogo() {
		Parser.salvaCatalogo(this.catalogo);
	}
	
	//GPU
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, int slotOccupati ) {
		if(codiceCategoria.equals("GPU")) {
			this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
			GPU gpu = new GPU (this.componenteCorrente, slotOccupati);
			this.catalogo.aggiungiInCatalogo(gpu);
			this.componenteCorrente = gpu;
		}
	}
	
	//PSU
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, int potenzaErogata, String tipologia, String formFactor ) {
		if(codiceCategoria.equals("PSU")) {
			this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
			PSU psu = new PSU (this.componenteCorrente, potenzaErogata, tipologia, formFactor);
			this.catalogo.aggiungiInCatalogo(psu);
			this.componenteCorrente = psu;
		}
	}
	
	//CPU
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, String socket) {
		if(codiceCategoria.equals("CPU")) {
			this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
			CPU cpu = new CPU (this.componenteCorrente, socket);
			this.catalogo.aggiungiInCatalogo(cpu);
			this.componenteCorrente = (Componente) cpu;
		}
	}
	
	//Storage
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, String memoria, double dimensioni, int velocita, String tipologia ) {
		if (codiceCategoria.equals("Storage")) {
			this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
			Storage storage = new Storage (this.componenteCorrente, memoria, dimensioni, velocita, tipologia);
			this.catalogo.aggiungiInCatalogo(storage);
			this.componenteCorrente = storage;
		}
	}
	
	//RAM
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, String tipologia, int frequenza ) {
		if(codiceCategoria.equals("RAM")) {
			this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
			RAM ram = new RAM (this.componenteCorrente, tipologia, frequenza);
			this.catalogo.aggiungiInCatalogo(ram);
			this.componenteCorrente = ram;
		}
	}
	
	//Case
	public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, String formFactorPSU, int slot, String formFactorMotherboard) {
		if(codiceCategoria.equals("Case")) {
			this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
			Case caseC = new Case (this.componenteCorrente, formFactorMotherboard, formFactorPSU, slot);
			this.catalogo.aggiungiInCatalogo(caseC);
			this.componenteCorrente = caseC;
		}
	}
	
	//Motherboard
    public void creaComponente(String nome, String codiceCategoria, int consumo, double prezzo, String descrizione, String socket, String formFactor, String tipologiaRAM) {
        if(codiceCategoria.equals("Motherboard")) {
            this.componenteCorrente = new Componente(nome, prezzo, consumo, descrizione, codiceCategoria);
            Motherboard motherboard = new Motherboard (this.componenteCorrente, socket, formFactor, tipologiaRAM);
            this.catalogo.aggiungiInCatalogo(motherboard);
            this.componenteCorrente = motherboard;
        }
    }
	
    // WRAPPER sulle funzioni del Catalogo
	public String creaCopie (int numero) {
		return this.componenteCorrente.aggiungiCopie(numero);
	}
	
	public Componente selezionaComponente (int codice, String categoria) {
		return this.catalogo.getComponente(codice, categoria);
	}
	
	public Componente selezionaComponente(int idComponente) {
		this.componenteCorrente = this.catalogo.getComponente(idComponente);	
		return this.componenteCorrente; 
	}
	
	public boolean rimuoviComponente(int idComponente){
		if (this.catalogo.rimuoviDaMappa(idComponente)){
			this.catalogo.rimuoviConfigurazioni(idComponente);
			return true;
		}else return false; 
	}
	
	public String setPromozione(double promozione) {
		return this.componenteCorrente.impostaPromozione(promozione);
	}
	
}
