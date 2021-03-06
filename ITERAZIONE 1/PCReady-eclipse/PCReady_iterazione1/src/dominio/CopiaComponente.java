package dominio;

import Utility.Counter;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class CopiaComponente {

	// Codice univoco della copia
	private int codice;
	
	
	/********** COSTRUTTORI *********/
	
	/**
	 * Costruttore per copie pre-esistenti
	 * @param codice: il codice univoco della CopiaComponente
	 */
	public CopiaComponente(int codice) {
		this.setCodice(codice);
	}
	
	/**
	 * Costruttore per nuove copie, con codice auto-generato
	 */
	public CopiaComponente() { 
		//Costruttore che corrisponde alla funzione creaCopia del diagramma delle classi di progetto
		this.codice = (int) Counter.getNextNumber();
	}
	
	
	/********** GETTERS & SETTERS **********/
	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

}
