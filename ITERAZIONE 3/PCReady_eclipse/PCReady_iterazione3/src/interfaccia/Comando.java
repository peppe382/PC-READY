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
	private boolean adminOnly;
	
	public Comando(int codice, String descrizione, boolean adminOnly) {
		this.setCodiceComando(codice);
		this.setDescrizioneComando(descrizione);
		this.setAdminOnly(adminOnly);
	} 
	
	public Comando() { // questi tipi di Comandi sono solo di Utility e non verranno chiamati dall'Utente, ma solo da
						// altri Comandi
		this.setCodiceComando(-1);
		this.setDescrizioneComando("");
		this.setAdminOnly(false);
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

	public boolean isAdminOnly() {
		return adminOnly;
	}

	public void setAdminOnly(boolean adminOnly) {
		this.adminOnly = adminOnly;
	}
	
    public abstract void esegui(Console console);
	
}
