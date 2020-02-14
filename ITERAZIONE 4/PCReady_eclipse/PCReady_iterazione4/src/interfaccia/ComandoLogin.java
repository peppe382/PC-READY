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
		console.print("Benvenuto!\nEffettua il login: \n");
		while(successo == false) {
			console.print("\n1) Effettua l'accesso come Amministratore\n2) Effettua l'accesso come Utente\n3) Registrati\n");
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
						console.print("L'amministratore ha effettuato l'accesso\n");
						successo = true;
					}else {
						console.print("L'amministratore non è registrato\n");
					}
				break;
			case 2:
				console.print("Email: \n");
				email = console.getString();
				console.print("Password: \n");
				password = console.getString();
				controllo = console.getSistema().effettuaLogin("Cliente", email, password);
				if(controllo.equals("Il cliente "+ email +" ha effettuato il login")) {
					console.setClienteCorrente(console.getSistema().getCliente());
					console.setAdmin(false);
					console.print("L'utente ha effettuato l'accesso \n");
					successo = true;
				}else {
					console.print("L'utente non è registrato \n");
				}
				break;
			case 3:
					ComandoRegistrazione comandoRegistrazione = new ComandoRegistrazione();
					comandoRegistrazione.esegui(console);
					successo = comandoRegistrazione.getRisultato();
					break;
			default:
				console.print("Opzione non valida \n");
			}

		}
		
		
		
		

	}

}
