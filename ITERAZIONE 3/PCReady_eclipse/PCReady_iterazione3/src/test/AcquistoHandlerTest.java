package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dominio.*;
import dominio.componenti.*;
import handlers.*;

class AcquistoHandlerTest {

	private static Catalogo catalogo;
	private static AcquistoHandler acquistoH;
	private static Cliente clienteCorrente;
	
	@BeforeAll
	static void setupAll() {
		PCReady sistema = PCReady.getInstance();
		clienteCorrente = new Cliente("Gabriello", "Costy", "gabrielloPazzerello@gmail.com", "passwordInChiaro");
		sistema.setHandlerConfigurazioni();
		acquistoH = new AcquistoHandler(sistema.getHandlerConfigurazioni().getCatalogo(), clienteCorrente);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Aggiungi al carrello test")
	void aggiuntaTest(){
		acquistoH.iniziaAcquisto();
		acquistoH.selezionaCategoria("CPU");
		System.out.print(acquistoH.selezionaProdotto(1));
		System.out.print(acquistoH.aggiungiInCarrello());
		
		acquistoH.selezionaCategoria("CPU");
		System.out.print(acquistoH.selezionaProdotto(1));
		System.out.print(acquistoH.aggiungiInCarrello());
		
		acquistoH.selezionaCategoria("CPU");
		System.out.print(acquistoH.selezionaProdotto(1));
		System.out.print(acquistoH.aggiungiInCarrello());
	}

}
