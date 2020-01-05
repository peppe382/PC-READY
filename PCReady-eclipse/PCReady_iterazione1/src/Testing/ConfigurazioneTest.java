package Testing;

import java.util.*;

import dominio.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class ConfigurazioneTest {

	private static Configurazione configurazione;
	private Componente componente = new Componente(12,"AMD",12.20,33,"Suca");
	
	
	@BeforeAll
	static void setupAll() {
		configurazione = new Configurazione();
	}
	
	@Test
	void testAddComponente(){
		configurazione.addComponente(componente);
		List<Componente> listaComponenti = configurazione.getComponenti();
		assertNotNull(listaComponenti);  //va a buon fine se il contenuto non è vuoto
		System.out.println("Componenti: "+listaComponenti.get(0).getNome());
	
	}
	
	
	@Test
	void testAggiornaAttributi() {
		int consumo = configurazione.getConsumo();
		double prezzo = configurazione.getPrezzo();
		configurazione.aggiornaAttributi(componente);
		assertNotSame(consumo, configurazione.getConsumo());  //va a buon fine se le condizioni non sono uguali
		assertNotSame(prezzo, configurazione.getPrezzo());
		System.out.println("Vecchio consumo: "+ consumo);
		System.out.println("Nuovo consumo: "+ configurazione.getConsumo());
		System.out.println("Vecchio prezzo: "+ prezzo);
		System.out.println("Nuovo prezzo: "+ configurazione.getPrezzo());
	}
	
	@Test
	void testControllaConfigurazione() {
		assertTrue(configurazione.controllaConfigurazione()); //va a buon fine se la condizione è true
	}
	
	@Test
	void testGeneraBundle() {
		
		Bundle bundle = configurazione.generaBundle("bundle1","descrizione1" , 12);
		assertNotNull(bundle);
		System.out.println("Nome bundle: "+ bundle.getNome());
	}
	
	
	
	
	
	
	

}
