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

	private int codice;

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public CopiaComponente() { 
		//Costruttore che corrisponde alla funzione creaCopia del diagramma delle classi di progetto
		this.codice = (int) Counter.getNextNumber();
	}
	
	
	
}
