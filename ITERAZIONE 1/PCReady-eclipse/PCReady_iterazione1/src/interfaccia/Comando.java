package interfaccia;

import dominio.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public interface Comando {

	public String getCodiceComando();
	
	public String getDescrizioneComando();
	
    public void esegui(PCReady sistema);
	
}
