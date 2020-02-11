package dominio;

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
		this.handlerComponenti = GestisciComponentiHandler.getInstance();
		this.handlerConfigurazioni = null;
	}
	
	public static synchronized PCReady getInstance() {
		if(singleton == null) {
			singleton = new PCReady();
		}
		return singleton;
	}
	
	
	/**
	 * Arresta il Sistema dopo aver salvato i dati permanenti su database
	 **/
	 
	public void spegniSistema() {
		this.getHandlerComponenti().salvaCatalogo();
	}

	
	/********** GETTERS E SETTERS  **********/
	
	public ConfigurationHandler getHandlerConfigurazioni() {
		return this.handlerConfigurazioni;
	}

	public GestisciComponentiHandler getHandlerComponenti() {
		return this.handlerComponenti;
	}

	public void setHandlerConfigurazioni() {
		this.handlerConfigurazioni = new ConfigurationHandler(getHandlerComponenti().getCatalogo());
	}
	
	public void setHandlerConfigurazioni(String comando_bundle) {
		if (comando_bundle.equals("Bundle")){ //In futuro tale if controllerá se un admin loggato sta chiamando il comando...
			this.handlerConfigurazioni = new ConfigurationHandler(getHandlerComponenti().getCatalogo(), comando_bundle);
		}
	}

	public void setHandlerComponenti() {
		this.handlerComponenti = GestisciComponentiHandler.getInstance();
	}
	
	
	
}
