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
		this.mCat = new Map<Integer, Categoria>();
		
	}
	
	public static synchronized PCReady getIstance() {
		if(singleton == null) singleton = new PCReady();
		return singleton;
	}
	
}
