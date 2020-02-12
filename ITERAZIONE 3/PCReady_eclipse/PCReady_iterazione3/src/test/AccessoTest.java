package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Amministratore;
import dominio.Cliente;
import dominio.PCReady;

class AccessoTest {
	
	private static PCReady sistema;
	private static Map<String, Cliente> mappaClienti;
	private static Map<String, Amministratore> mappaAmministratori;
	
    @BeforeAll
    static void setupAll() {
    	mappaClienti = new HashMap<String, Cliente>();
    	mappaAmministratori = new HashMap<String, Amministratore>();
    	mappaClienti.put("peppe382@gmail.com", new Cliente("Giuseppe", "Fallica", "peppe382@gmail.com", "password"));
    	mappaAmministratori.put("bartolomeo@gmail.com", new Amministratore("Bartolomeo","Caruso","mussolini@gmail.com","password"));
    	sistema = PCReady.getInstance();
    	sistema.setMappaAmministratori(mappaAmministratori);
    	sistema.setMappaClienti(mappaClienti);
    }
	
	@Test
	void effettuaLogintest() {
		String controllo1 = sistema.effettuaLogin("Amministratore", "bartolomeo@gmail.com", "password");
		System.out.println(controllo1);
		assertNotEquals("Email non registrata", controllo1);
		
		String controllo2 = sistema.effettuaLogin("Amministratore", "peppe382@gmail.com", "password");
		System.out.println(controllo2+"\n");
		assertEquals("Email non registrata", controllo2);
		
	}
	
	@Test
	void richiediRegistrazionetest() {
		System.out.println(sistema.richiediRegistrazione("Pippo", "Franco", "pippoFranco@gmail.com", "password", "password"));
		 Map<String, Cliente> mappaClientiAttuale = sistema.getMappaClienti();
		 assertNotNull(mappaClientiAttuale.get("pippoFranco@gmail.com"));
		 String controllo3 = sistema.richiediRegistrazione("Pippo", "Franco", "pippoFranco@gmail.com", "password", "password");
		 System.out.println(controllo3);
		 assertEquals("Email gia utilizzata", controllo3);
		}

}
