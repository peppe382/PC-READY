package interfaccia;

import dominio.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public abstract class Comando {
	
	private String codice;
	private String descrizione;
	
	public Comando(String codice, String descrizione) {
		this.setCodiceComando(codice);
		this.setDescrizioneComando(descrizione);
	}

	public String getCodiceComando() {
		return this.codice;
	}
	
	public void setCodiceComando(String codice){
		this.codice = codice;
	}
	
	public String getDescrizioneComando() {
		return this.descrizione;
	}
	
	public void setDescrizioneComando(String descrizione) {
		this.descrizione = descrizione;
	}
	
    public abstract void esegui(PCReady sistema);
	
}
