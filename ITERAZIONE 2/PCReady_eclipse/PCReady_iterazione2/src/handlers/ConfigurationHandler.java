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
	public ConfigurationHandler() {
		this.mappaCorrente = new HashMap<Integer, Componente>();
		this.checker = new CompatibilityChecker();
		this.catalogo = new Catalogo();
	}
	
	
	public ConfigurationHandler(CompatibilityChecker checker, Catalogo catalogo) {
		this.checker = checker;
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
		Componente comp = null;
		try {
			comp = this.mappaCorrente.get(idComponente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comp;
	}
	
	
	public  Map<Integer, Componente> selezionaCategoria(String idCategoria) {
		return this.mappaCorrente = this.catalogo.ottieniComponentiByCategoria(idCategoria);
	}
	
	public String confermaComponente() {
		if (this.checker.controlloComponente(this.componenteCorrente)) {
			return this.conf.aggiungiComponenteInConfigurazione(this.componenteCorrente);
		}
		//Da decidere la policy comportamentale else
	}
	
	
	public String terminaAssemblaggio() {
		if (this.checker.controllaPresenzaComponenti()) {
			if (this.checker.controllaConsumoEnergetico()) {
				this.conf.riepilogaConfigurazione();
			}
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
