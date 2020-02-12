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
	
	/********** COSTRUTTORI e FUNZ. SINGLETON *********/
	
	/**
	 * Costruttore principale, non-singleton, di Console
	 */
	public Console() {
		this.sistema = PCReady.getInstance();
	}
	
	// -------------------------------------------------------------------
	
	
	// Funzioni di Utility
	public void print(String message) {
		System.out.println(message);
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
		if(val=="si"||val=="sì"||val=="s"||val=="yes"||val=="y") return true;
		else if(val=="no"||val=="n") return false;
		else return null;
	}
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
}
