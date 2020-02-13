package interfaccia;

import java.util.*;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoLogin extends Comando {

	public ComandoLogin() {
	}
	
	@Override
	public void esegui(Console console) {
		
		String email;
		String password;
		String controllo;
		boolean successo = false;
		while(successo == false) {
			console.print("Benvenuto!\nEffettua il login: \n\n");
			console.print("1) Effettua l'accesso come Amministratore\n2) Effettua l'accesso come Utente\n3) Registrati\n");
			int tipologia = console.getInt();
			
			switch(tipologia) {
			case 1:
				console.print("Email: \n");
				email = console.getString();
				console.print("Password: \n");
				password = console.getString();
				controllo = console.getSistema().effettuaLogin("Amministratore", email, password);
					if(controllo.equals("L'amministratore "+email+" ha effettuato il login")) {
						console.setAmministratoreCorrente(console.getSistema().getAmministratore());
						console.setAdmin(true);
						console.print("L'amministratore ha effettuato l'accesso");
						successo = true;
					}else {
						console.print("L'amministratore non è registrato\n");
						console.setErrore(true);
					}
					break;
			case 2:
				console.print("1Email: \n");
				email = console.getString();
				console.print("1Password: \n");
				password = console.getString();
				controllo = console.getSistema().effettuaLogin("Cliente", email, password);
				if(controllo.equals("Il cliente "+ email +" ha effettuato il login")) {
					console.setClienteCorrente(console.getSistema().getCliente());
					console.setAdmin(false);
					console.print("L'utente ha effettuato l'accesso");
					successo = true;
				}else {
					console.print("L'utente non è registrato");
					console.setErrore(true);
				}
				break;
			case 3:
					Comando comandoRegistrazione = new ComandoRegistrazione();
					comandoRegistrazione.esegui(console);
					successo = true;
					break;
			default:
				console.print("Opzione non valida");
		}

		}
		
		
		
		

	}

}
