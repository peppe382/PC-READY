package handlers;

import java.util.*;
import dominio.*;
import dominio.componenti.*;

public class ConfigurationHandler {

	private Map<Integer, Componente> mappaCorrente;
	private Componente componenteCorrente;
	private Configurazione conf;
	private CompatibilityChecker checker;
	private Catalogo catalogo;
	
	
	//  Costruttori
	public ConfigurationHandler(Catalogo catalogo) {
		this.creaConfigurazione();
		this.mappaCorrente = new HashMap<Integer, Componente>();
		this.checker = new CompatibilityChecker(this.conf);
		this.catalogo = catalogo;
	}




	//  Getters e Setters
	public Map<Integer, Componente> getMappaCorrente() {
		return mappaCorrente;
	}


	public Componente getComponenteCorrente() {
		return componenteCorrente;
	}


	public Configurazione getConf() {
		return conf;
	}


	public CompatibilityChecker getChecker() {
		return checker;
	}


	public void setMappaCorrente(Map<Integer, Componente> mappaCorrente) {
		this.mappaCorrente = mappaCorrente;
	}


	public void setComponenteCorrente(Componente componenteCorrente) {
		this.componenteCorrente = componenteCorrente;
	}


	public void setConf(Configurazione conf) {
		this.conf = conf;
	}


	public void setChecker(CompatibilityChecker checker) {
		this.checker = checker;
	}
	
	
	//  Funzioni di progetto
	
	public void creaConfigurazione() {
		this.conf = new Configurazione();
		/* Una funzione console dopo tale operazione deve ritornare all'utente/amministratore
		   tutte le categorie presenti nel sistema da cui scegliere le componenti */
	}
	
	
	public Componente selezionaComponente(int idComponente) {
		try {
			this.componenteCorrente  = this.mappaCorrente.get(idComponente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.componenteCorrente ;
	}
	
	
	public Map<Integer, Componente> selezionaCategoria(String idCategoria) {
		try {
			return this.mappaCorrente = this.catalogo.ottieniComponentiByCategoria(idCategoria);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String confermaComponente() {
		if (this.checker.controlloComponente(this.componenteCorrente)) {
			return this.conf.aggiungiComponenteInConfigurazione(this.componenteCorrente);
		}
		//Da decidere la policy comportamentale else
		else{
			Componente alternativa = this.checker.trovaAlternativa(this.mappaCorrente);
			String consiglio = null;
			if ( alternativa != null) {
				consiglio =  "COMPONENTE SOSTITUTIVO CONSIGLIATO:"+alternativa.toString() ;
			}
			return consiglio;
			//Se consiglio � null, non � presente nessun componente sostitutivo...
		}
	}
	
	
	public String terminaAssemblaggio() {
		String componenteMancante = this.checker.controllaPresenzaComponenti();
		if (componenteMancante == null){
			if (this.checker.controllaConsumoEnergetico()) {
				return this.conf.riepilogaConfigurazione();
			}
			else {
				String stringa = "CONSUMO ENERGETICO ECCESSIVO, PROVA CON QUESTO ALIMENTATORE COMPATIBILE: ";
				return stringa+this.checker.trovaAlimentatore(selezionaCategoria("PSU")).toString();
			}
		}
		else{
			return "ASSENTE UN COMPONENTE DI CATEGORIA: "+componenteMancante;
		}
	}
	
	public void confermaConfigurazione() {
		this.catalogo.salvaConfigurazione(this.conf);
	}
	
	public void infoConfigurazione(Double sconto, String nome, String descrizione) {
		if (this.conf.getCategoria() == "Bundle") {
			((Bundle) this.conf).infoBundle(nome,descrizione,sconto);
		}
	}
	
}
