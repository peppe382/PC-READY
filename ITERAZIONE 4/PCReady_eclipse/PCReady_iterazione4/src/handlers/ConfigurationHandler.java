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
	private String stringaComunicazioni;
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public ConfigurationHandler(Catalogo catalogo) {
		this.creaConfigurazione();
		this.mappaCorrente = new HashMap<Integer, Componente>();
		this.checker = new CompatibilityChecker(this.conf);
		this.catalogo = catalogo;
		this.stringaComunicazioni = null;
	}
	
	public ConfigurationHandler(Catalogo catalogo, String comando_bundle) {
		this.creaBundle(comando_bundle);
		this.mappaCorrente = new HashMap<Integer, Componente>();
		this.checker = new CompatibilityChecker(this.conf);
		this.catalogo = catalogo;
		this.stringaComunicazioni = null;
	}

	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
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
	
	public String getStringaComunicazioni() {
		return stringaComunicazioni;
	}

	public void setStringaComunicazioni(String stringaComunicazioni) {
		this.stringaComunicazioni = stringaComunicazioni;
	}
	
	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
	
	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO
	
	// Crea una nuova Configurazione corrente
	public void creaConfigurazione() {
		this.conf = new Configurazione();
		/* Una funzione console dopo tale operazione deve ritornare all'utente/amministratore
		   tutte le categorie presenti nel sistema da cui scegliere le componenti */
	}
	
	// Crea un nuovo Bundle corrente
	public void creaBundle(String comando_bundle) {
		if (comando_bundle.equals("Bundle")) this.conf = new Bundle();
	}
	
	// Seleziona un Componente corrente
	public Componente selezionaComponente(int idComponente) {
		try {
			this.componenteCorrente  = this.mappaCorrente.get(idComponente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.componenteCorrente ;
	}
	
	// Seleziona una Categoria per ottenere la mappa di Componenti da cui scegliere
	public Map<Integer, Componente> selezionaCategoria(String idCategoria) {
		try {
			return this.mappaCorrente = this.catalogo.ottieniComponentiByCategoria(idCategoria);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Conferma la scelta del Componente da aggiungere alla Configurazione
	public String confermaComponente() {
		if (this.checker.controlloComponente(this.componenteCorrente)) {
			return this.conf.aggiungiComponenteInConfigurazione(this.componenteCorrente);
		}
		//Da decidere la policy comportamentale else
		else{
			Componente alternativa = this.checker.trovaAlternativa(this.mappaCorrente);
			String consiglio = null;
			if ( alternativa != null) {
				consiglio =  "COMPONENTE INCOMPATIBILE: COMPONENTE SOSTITUTIVO CONSIGLIATO: \n"+alternativa.toString() ;
			}
			return consiglio;
			//Se consiglio e' null, non e' presente nessun componente sostitutivo...
		}
	}
	
	// Controlla che sia possibile l'assemblaggio della Configurazione
	public Boolean terminaAssemblaggio() {
		String componenteMancante = this.checker.controllaPresenzaComponenti();
		if (componenteMancante == null){
			if (this.checker.controllaConsumoEnergetico()) {
				setStringaComunicazioni(this.conf.riepilogaConfigurazione());
				return true;
			}
			else {
				String stringa = "CONSUMO ENERGETICO ECCESSIVO, PROVA CON QUESTO ALIMENTATORE COMPATIBILE: \n";
				setStringaComunicazioni (stringa+this.checker.trovaAlimentatore(selezionaCategoria("PSU")).toString());
				return false;
			}
		}
		else{
			setStringaComunicazioni ("ASSENTE UN COMPONENTE DI CATEGORIA: "+componenteMancante);
			return false;
		}
	}
	
	// Conferma la Configurazione già convalidata
	public void confermaConfigurazione() {
		this.catalogo.salvaConfigurazione(this.conf);
	}
	
	// Aggiungi le info necessarie per il Bundle
	public void infoConfigurazione(Double sconto, String nome, String descrizione) {
		if (this.conf.getCategoria().contentEquals("Bundle")) {
			((Bundle) this.conf).infoBundle(nome,descrizione,sconto);
		}
	}
	
	// Elimina un Componente dalla Configurazione
	public String eliminaComponenteConfigurazione(int idComponente) {
		String comunicazione = "";
		if (this.conf.getListaComponenti().size() > 0) {
			Componente componenteDaEliminare = this.catalogo.getComponente(idComponente);
			if (this.conf.getListaComponenti().contains(componenteDaEliminare)) {
				if (this.conf.getListaComponenti().remove(componenteDaEliminare)) {
					comunicazione = "Componente rimosso \n";
				} else comunicazione = "Errore di rimozione \n";
			} else comunicazione = "Componente da rimuovere non contenuto nella lista \n";
		} else comunicazione = "Lista componenti vuota \n";
		return comunicazione;
	}
	
}
