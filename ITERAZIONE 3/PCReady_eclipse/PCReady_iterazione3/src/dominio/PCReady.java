package dominio;

import java.util.*;

import handlers.*;

public class PCReady {


	private static PCReady singleton;
	
	private String versione = "0.1";
	
	private ConfigurationHandler handlerConfigurazioni;
	private GestisciComponentiHandler handlerComponenti;
	private AcquistoHandler handlerAcquisto;
	private Cliente clienteCorrente;
	private Map<Integer, List<Ordine>> mappaOrdini;
	
	/** COSTRUTTORI e FUNZ. SINGLETON 
	
	 * Costruttore singleton protected. Si potra ottenere l'unica istanza singleton
	 * usando la funzione "getIstance()"  **/
	
	protected PCReady() {
		this.handlerComponenti = GestisciComponentiHandler.getInstance();
		this.handlerConfigurazioni = null;
		this.handlerAcquisto = null;
		this.mappaOrdini = null; //Verrá modificata con info dal parser...
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
	
	public void setHandlerAcquisto() {
		this.handlerAcquisto = new AcquistoHandler(getHandlerComponenti().getCatalogo(), this.clienteCorrente);
	}
	
	
	//Funzioni di progetto
	public void salvaOrdine(Ordine ordine, int idCliente) {
		boolean clienteInMappaOrdini = false;
		try {
			 if (!this.mappaOrdini.get(idCliente).isEmpty()) {
				 clienteInMappaOrdini = true;
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		List<Ordine> nuovaLista = new ArrayList<Ordine>();
		nuovaLista.add(ordine);
		if (clienteInMappaOrdini) {
			for (Ordine elemento : this.mappaOrdini.get(idCliente)) {
				nuovaLista.add(elemento);
			}
			this.mappaOrdini.put(idCliente, nuovaLista);
		}
		else this.mappaOrdini.put(idCliente, nuovaLista);
	}
}
