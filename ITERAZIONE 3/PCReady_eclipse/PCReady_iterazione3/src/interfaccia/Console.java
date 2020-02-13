package interfaccia;

import java.util.*;

import dominio.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public class Console {
	
	private PCReady sistema; // il sistema su cui deve operare la Console
	private static Scanner in = new Scanner(System.in);
	private boolean on = true;
	private boolean admin = true;
	private Amministratore amministratoreCorrente;
	private Cliente clienteCorrente;
	
	/********** COSTRUTTORI e FUNZ. SINGLETON *********/
	
	/**
	 * Costruttore principale, non-singleton, di Console
	 */
	public Console() {
		this.setSistema();
	}
	
	public void accesso() {
		
		Comando comandoLogin = new ComandoLogin();
		comandoLogin.esegui(this);
				
	}
	
	public void esegui() {
		if(this.isOn()) {
			while(on)
				if(admin == true) {
					this.print(ElencoComandi.stringAmministratore());
					Integer codice = this.getInt();
					try {
						ElencoComandi.getComandoAmministratore(codice).esegui(this);
					}catch(Exception e) {
						e.printStackTrace();
					}
					this.print("Codice inserito non valido!\n\n\n");
					} else{
						this.print(ElencoComandi.stringAmministratore());
						Integer codice = this.getInt();
						try {
							ElencoComandi.getComandoAmministratore(codice).esegui(this);
						}catch(Exception e) {
							e.printStackTrace();
					}
						this.print("Codice inserito non valido!\n\n\n");
				}
			}
		}
	
	public boolean isOn() {
		return this.on;
	}
	
	public void spegni() {
		this.on = false;
		//Salva su sistema PC Ready prima di sfanculare.
	}
	
	// -------------------------------------------------------------------
	
	
	// Funzioni di Utility
	public void print(String message) {
		System.out.print(message);
	}
	
	
	public Integer getInt() {
		try {
		    int val = Integer.parseInt(in.nextLine());
		    if(val < 0) return null;
			else return val;
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	public Double getDouble() {
		try {
			double val = Double.parseDouble(in.nextLine());
			if(val < 0) return null;
			else return val;
		}catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	public String getString() {
		try {
			String val = Console.in.nextLine();
			System.out.println(val);
			if(val.length()<1) return null;
			else return val;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean getYesNo() {
		String val = null;
		val = this.getString().toLowerCase();
		if(val.equals("si")||val.equals("sì")||val.equals("s")||val.equals("yes")||val.contentEquals("y")){
			return true;
		}
		else if(val.equals("no")||val.equals("n")) return false;
		if (val == "si") return true;
		else return null;
	}

	
	/********** GETTERS & SETTERS + TO-STRING **********/
	public PCReady getSistema() {
		return sistema;
	}
	
	public void setErrore(boolean errore) {
		this.errore = errore;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setSistema() {
		this.sistema = PCReady.getInstance();
	}
	
	public void setAmministratoreCorrente(Amministratore amministratoreCorrente) {
		this.amministratoreCorrente = amministratoreCorrente;
	}
	
	public void setClienteCorrente(Cliente clienteCorrente) {
		this.clienteCorrente = clienteCorrente;
	}
	
	public Amministratore getAmministratoreCorrente() {
		return amministratoreCorrente;
	}
	
	public Cliente getClienteCorrente() {
		return clienteCorrente;
	}
}
