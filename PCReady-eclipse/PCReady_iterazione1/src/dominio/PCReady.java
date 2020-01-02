package dominio;

import java.util.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class PCReady {
	
	private static PCReady singleton;

	private Componente c; // Componente corrente
	private Componente componenteSelezionato;
	private Configurazione conf; // Configurazione corrente
	
	private Map<Integer, Categoria> mCat;
	private List<Componente> listaComponentiSistema;
	private List<Configurazione> listaConfigurazioni;
	
	protected PCReady() {
		this.mCat = new HashMap<Integer, Categoria>();
		this.listaComponentiSistema = new LinkedList<Componente>();
		this.listaConfigurazioni = new LinkedList<Configurazione>();
		this.caricaSistema();
	}
	
	public static synchronized PCReady getIstance() {
		if(singleton == null) singleton = new PCReady();
		return singleton;
	}
	
	protected void caricaSistema() {
		// riempie il sistema con i dati conosciuti tramite la lettura di un
		// file JSON
	}
	
}
