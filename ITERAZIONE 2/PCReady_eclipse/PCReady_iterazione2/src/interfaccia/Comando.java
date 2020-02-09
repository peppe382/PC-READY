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
	
	private int codice;
	private String descrizione;
	
	public Comando(int codice, String descrizione) {
		this.setCodiceComando(codice);
		this.setDescrizioneComando(descrizione);
	} 
	
	public Comando() { // questi tipi di Comandi sono solo di Utility e non verranno chiamati dall'Utente, ma solo da
						// altri Comandi
		this.setCodiceComando(-1);
		this.setDescrizioneComando("");
	}

	public int getCodiceComando() {
		return this.codice;
	}
	
	public void setCodiceComando(int codice){
		this.codice = codice;
	}
	
	public String getDescrizioneComando() {
		return this.descrizione;
	}
	
	public void setDescrizioneComando(String descrizione) {
		this.descrizione = descrizione;
	}
	
    public abstract void esegui(Console console);
	
}
