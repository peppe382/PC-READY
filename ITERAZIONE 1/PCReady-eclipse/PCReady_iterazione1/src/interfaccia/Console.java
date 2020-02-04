package interfaccia;

import java.util.HashMap;
import java.util.LinkedList;

import dominio.Categoria;
import dominio.Componente;
import dominio.Configurazione;
import dominio.PCReady;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public class Console {
	
	private PCReady sistema; // il sistema su cui deve operare la Console
	
	/********** COSTRUTTORI e FUNZ. SINGLETON *********/
	
	/**
	 * Costruttore principale, non-singleton, di Console
	 */
	protected Console() {
		this.sistema = PCReady.getInstance();
	}
	
	// -------------------------------------------------------------------
	
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
}
