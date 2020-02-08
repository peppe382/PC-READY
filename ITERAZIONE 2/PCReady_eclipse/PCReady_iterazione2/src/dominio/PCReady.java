package dominio;

import data.Parser;
import handlers.*;

public class PCReady {


	private static PCReady singleton;
	
	private String versione = "0.1";
	
	private ConfigurationHandler handlerConfigurazioni;
	private GestisciComponentiHandler handlerComponenti;
	
	
	
	
	/** COSTRUTTORI e FUNZ. SINGLETON 
	
	 * Costruttore singleton protected. Si potra ottenere l'unica istanza singleton
	 * usando la funzione "getIstance()"  **/
	
	protected PCReady() {
		
	}
	
	public static synchronized PCReady getIstance() {
		if(singleton == null) {
			singleton = new PCReady();
			singleton.caricaSistema();
		}
		return singleton;
	}
	
	
	/**
	 * Da chiamare solo all'avvio, riempie il sistema con le informazioni salvate su JSON **/
	 
	protected void caricaSistema() {
		// riempie il sistema con i dati conosciuti tramite la lettura di un
		// file JSON
		/*Parser par = new Parser("data/test_data.json");
		par.initialParsing();
		System.out.println("PC Ready correttamente caricato.\n");
		System.out.println(this.toString());
		System.out.println("-------------------------------------------------------------");
		*/
	}
	
	
	/**
	 * Aggiorna il JSON con tutte le nuove informazioni generate **/
	 
	public void salvaSistema() {
		/*Parser.saveAll("data/test_data.json");*/
	}
	
	
	
	
	/********** GETTERS E SETTERS  **********/
	
	public ConfigurationHandler getHandlerConfigurazioni() {
		return handlerConfigurazioni;
	}

	public GestisciComponentiHandler getHandlerComponenti() {
		return handlerComponenti;
	}

	public void setHandlerConfigurazioni(ConfigurationHandler handlerConfigurazioni) {
		this.handlerConfigurazioni = handlerConfigurazioni;
	}

	public void setHandlerComponenti(GestisciComponentiHandler handlerComponenti) {
		this.handlerComponenti = handlerComponenti;
	}
	
}
