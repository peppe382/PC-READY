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
	
	/********** COSTRUTTORI e FUNZ. SINGLETON *********/
	
	/**
	 * Costruttore principale, non-singleton, di Console
	 */
	public Console() {
		this.setSistema();
	}
	
	public void esegui() {
		if(this.isOn()) {
			while(on) {
				this.print(ElencoComandi.string());
				Integer codice = this.getInt();
				try {
					ElencoComandi.getComando(codice).esegui(this);
				}catch(Exception e) {
					e.printStackTrace();
					
					this.print("Codice inserito non valido!\n\n\n");
				}
			}
		}
	}
	
	public boolean isOn() {
		return this.on;
	}
	
	public void spegni() {
		this.on = false;
	}
	
	// -------------------------------------------------------------------
	
	
	// Funzioni di Utility
	public void print(String message) {
		System.out.print(message);
	}
	
	
	public Integer getInt() {
		try {
			int val = Console.in.nextInt();
			if(val < 0) return null;
			else return val;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Double getDouble() {
		try {
			double val = Console.in.nextDouble();
			if(val < 0) return null;
			else return val;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getString() {
		try {
			String val = Console.in.next();
			if(val.length()<1) return null;
			else return val;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean getYesNo() {
		String val = this.getString().toLowerCase();
		System.out.println("STO LEGGENDO LA MINCHIA DI STRINGA: "+val);
		if(val == "si"||val == "sì"||val=="s"||val=="yes"||val=="y") {
			System.out.println("TRUE");
			return true;
		}
		else if(val=="no"||val=="n") return false;
		if (val == "si") return true;
		else return null;
	}

	
	/********** GETTERS & SETTERS + TO-STRING **********/
	public PCReady getSistema() {
		return sistema;
	}

	public void setSistema() {
		this.sistema = PCReady.getInstance();
	}
}
