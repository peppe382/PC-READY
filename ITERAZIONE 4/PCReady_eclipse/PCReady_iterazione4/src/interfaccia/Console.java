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
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public Console() {
		this.setSistema();
	}
	
	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO
	
	public void accesso() {
		
		Comando comandoLogin = new ComandoLogin();
		comandoLogin.esegui(this);
		esegui();
				
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
						this.print("Codice inserito non valido!\n\n\n");
					}
					} else{
						this.print(ElencoComandi.stringCliente());
						Integer codice = this.getInt();
						try {
							ElencoComandi.getComandoCliente(codice).esegui(this);
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
	// FUNZIONI di UTILITY
	
	public void print(String message) {
		System.out.print(message);
	}
	
	
	public Integer getInt() {
		boolean reading = true;
		int val = -1;
		while(reading) {
			this.print("Inserire un numero intero: ");
			try {
			    val = Integer.parseInt(in.nextLine());
			    if(val < 0) this.print("Il numero inserito deve essere positivo!\n");
			    else reading = false;
			} catch (Exception e) {
			    this.print("Il numero inserito non risulta valido!\n");
			}
		}
		return val;
	}
	
	public Integer getInt(String message) {
		boolean reading = true;
		int val = -1;
		while(reading) {
			this.print(message);
			try {
			    val = Integer.parseInt(in.nextLine());
			    if(val < 0) this.print("Il numero inserito deve essere positivo!\n");
			    else reading = false;
			} catch (Exception e) {
			    this.print("Il numero inserito non risulta valido!\n");
			}
		}
		return val;
	}
	
	public Double getDouble() {
		boolean reading = true;
		double val = -1;
		while(reading) {
			this.print("Inserire un numero decimale: ");
			try {
			    val = Double.parseDouble(in.nextLine());
			    if(val < 0) this.print("Il numero inserito deve essere positivo!\n");
			    else reading = false;
			} catch (Exception e) {
			    this.print("Il numero inserito non risulta valido!\n");
			}
		}
		return val;
	}
	
	public Double getDouble(String message) {
		boolean reading = true;
		double val = -1;
		while(reading) {
			this.print(message);
			try {
			    val = Double.parseDouble(in.nextLine());
			    if(val < 0) this.print("Il numero inserito deve essere positivo!\n");
			    else reading = false;
			} catch (Exception e) {
			    this.print("Il numero inserito non risulta valido!\n");
			}
		}
		return val;
	}
	
	public String getString() {
		boolean reading = true;
		String val = null;
		while(reading) {
			this.print("Immettere una stringa di testo: ");
			try {
				val = Console.in.nextLine();
				if(val.length()<1) this.print("La stringa non ha lunghezza valida!\n");
				else reading = false;
			}catch(Exception e) {
				this.print("La stringa immessa non risulta valida!\n");
			}
		}
		return val;
	}
	
	public String getString(String message) {
		boolean reading = true;
		String val = null;
		while(reading) {
			this.print(message);
			try {
				val = Console.in.nextLine();
				if(val.length()<1) this.print("La stringa non ha lunghezza valida!\n");
				else reading = false;
			}catch(Exception e) {
				this.print("La stringa immessa non risulta valida!\n");
			}
		}
		return val;
	}
	
	public Boolean getYesNo() {
		String val = null;
		Boolean returnValue = null;
		boolean reading = true;
		while(reading) {
			reading = false;
			val = this.getString("Rispondere alla domanda con Si o No: ").toLowerCase();
			if(val.equals("si")||val.equals("sì")||val.equals("s")||val.equals("yes")||val.contentEquals("y")) returnValue = true;
			else if(val.equals("no")||val.equals("n")) returnValue = false;
			else {
				this.print("Per favore, rispondere solo con Si o No!\n");
				reading = true;
			}
		}
		return returnValue;
	}
	
	public Boolean getYesNo(String message) {
		String val = null;
		Boolean returnValue = null;
		boolean reading = true;
		while(reading) {
			reading = false;
			val = this.getString(message).toLowerCase();
			if(val.equals("si")||val.equals("sì")||val.equals("s")||val.equals("yes")||val.contentEquals("y")) returnValue = true;
			else if(val.equals("no")||val.equals("n")) returnValue = false;
			else {
				this.print("Per favore, rispondere solo con Si o No!\n");
				reading = true;
			}
		}
		return returnValue;
	}

	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
	public PCReady getSistema() {
		return sistema;
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
