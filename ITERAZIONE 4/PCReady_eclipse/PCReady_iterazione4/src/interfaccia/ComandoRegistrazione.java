package interfaccia;

import java.util.*;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoRegistrazione extends Comando {
	
	private boolean risultato;

	public ComandoRegistrazione() {
		this.risultato = false;
	}

	@Override
	public void esegui(Console console) {
		
		console.print("Benvenuto!\nEffettua la registrazione: \n\n");
		String nome = console.getString("Dimmi il tuo nome: ");
		String cognome = console.getString("Dimmi il tuo cognome: ");
		String email = console.getString("Dimmi la tua email: ");
		String password = console.getString("Dimmi la tua password: ");
		String c_password = console.getString("Conferma la tua password: ");
		
		String message = console.getSistema().richiediRegistrazione(nome, cognome, email, password, c_password);
		if (message.equals("Email gia utilizzata") || message.equals("Le password non coincidono")) {
			console.print(message);
			setRisultato(false);
		}
		else {
			console.setClienteCorrente(console.getSistema().getCliente());
			console.print("L'utente ha effettuato la registrazione ed ora risulta loggato\n");
			console.print(message + "\n");
			setRisultato(true);
			console.setAdmin(false);
		}
	}
	
	public boolean getRisultato() {
		return this.risultato;
	}
	
	public void setRisultato(boolean value) {
		this.risultato = value;
	}
}
