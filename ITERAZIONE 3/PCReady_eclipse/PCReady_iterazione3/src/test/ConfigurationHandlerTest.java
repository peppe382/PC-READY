package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.*;
import dominio.*;
import dominio.componenti.*;
import handlers.*;

class ConfigurationHandlerTest {
	
	private static Catalogo catalogo;
	private static ConfigurationHandler handler;
	private static ConfigurationHandler handler2;
	private static Map<Integer, Componente> mappa;
	
	@BeforeAll
	static void setupAll() {
		CPU componente1 = new CPU("AMD Ryzen3 3200G", 100.90, 60, "Processore amd quad-core 3.4GHz","AM4");
		GPU componente2 = new GPU("AMD Sapphyre 580", 170.99, 100, "GPU AMD, 8Gb DDR5 dedicati, AMD Free-Sync", 2);
		RAM componente3 = new RAM("Corsaire RAM DDR4 4GB Stick", 41.99, 5, "RAM DDR4 3000MHz", "DDR4", 3000);
		Case componente4 = new Case("MSI Full Compact", 50.49, 0, "Case MSI stile microATX con 4 slot laterali, 4 porte usb 3.0", "microATX", "Slim", 4);
		PSU componente5 = new PSU("ThermalTake 550W", 59.99, "PSU ThermalTake Bronze 550W RGB light", 550, "Bronze", "Slim");
		Motherboard componente6 = new Motherboard("MSI Pro Carbon 420M", 61.49, 0, "Motherboard MSI socket AM4, 6 porte usb 3.0", "AM4", "microATX", "DDR4");
		Storage componente7 = new Storage("AMD Ryzen3 3200G", 100.90, 60, "Processore amd quad-core 3.4GHz", "1TB", 2.5, 500, "SSD");
		PSU componente8 = new PSU("Fake PSU 50W", 1345.99, "Il PSU che ti cambia la vita", 50, "Bronze", "Slim");
		catalogo = new Catalogo();
		catalogo.aggiungiInCatalogo(componente1);
		catalogo.aggiungiInCatalogo(componente2);
		catalogo.aggiungiInCatalogo(componente3);
		catalogo.aggiungiInCatalogo(componente4);
		catalogo.aggiungiInCatalogo(componente5);
		catalogo.aggiungiInCatalogo(componente6);
		catalogo.aggiungiInCatalogo(componente7);
		catalogo.aggiungiInCatalogo(componente8);
		handler = new ConfigurationHandler(catalogo);
		handler2 = new ConfigurationHandler(catalogo, "Bundle");
	}

	@org.junit.jupiter.api.Test
	@DisplayName("Seleziona Categoria Test")
	void testSelezionaCategoria() {
		mappa = handler.selezionaCategoria("RAM");
		assertNotNull(mappa);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Seleziona Componente Test")
	void testSelezionaComponente() {
		Componente componente = handler.selezionaComponente(3);
		System.out.println(componente);
		assertNotNull(componente);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Conferma Componente Test")
	void testConfermaComponente() {
		System.out.println(handler.confermaComponente());
		assertTrue(handler.getConf().getListaComponenti().get(0).getCategoria() == "RAM" );
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Conferma Termine Assemblaggio Test")
	void testTerminaAssemblaggio() {
		//Eseguo piú volte un codice simile ai test precedenti per generare tutto il necessario per la configurazione
		//Non inserisco la CPU per osservare il comportamento in caso di configurazione incompleta
		
		handler.selezionaCategoria("GPU");
		handler.selezionaComponente(2);
		handler.confermaComponente();
		
		handler.selezionaCategoria("RAM");
		handler.selezionaComponente(3);
		handler.confermaComponente();
		
		handler.selezionaCategoria("Case");
		handler.selezionaComponente(4);
		handler.confermaComponente();
		
		handler.selezionaCategoria("PSU");
		handler.selezionaComponente(5);
		handler.confermaComponente();
		
		handler.selezionaCategoria("Motherboard");
		handler.selezionaComponente(6);
		handler.confermaComponente();
		
		handler.selezionaCategoria("Storage");
		handler.selezionaComponente(7);
		handler.confermaComponente();
		System.out.println(handler.terminaAssemblaggio());
		Boolean condizione = true;
		for (Componente elemento : handler.getConf().getListaComponenti()) {
			if (elemento.getCategoria() == "CPU") {
				condizione = false;
			}
		}
		assertTrue(condizione);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Crea Bundle Test")
	void testBundle() {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		
		handler2.selezionaCategoria("CPU");
		handler2.selezionaComponente(1);
		handler2.confermaComponente();
		
		handler2.selezionaCategoria("GPU");
		handler2.selezionaComponente(2);
		handler2.confermaComponente();
		
		handler2.selezionaCategoria("RAM");
		handler2.selezionaComponente(3);
		handler2.confermaComponente();
		
		handler2.selezionaCategoria("Case");
		handler2.selezionaComponente(4);
		handler2.confermaComponente();
		
		handler2.selezionaCategoria("PSU");
		handler2.selezionaComponente(5);
		handler2.confermaComponente();
		
		handler2.selezionaCategoria("Motherboard");
		handler2.selezionaComponente(6);
		handler2.confermaComponente();
		
		handler2.selezionaCategoria("Storage");
		handler2.selezionaComponente(7);
		handler2.confermaComponente();
		
		
		handler2.infoConfigurazione(20.00, "WOW", "Bomba atomica");
		handler2.confermaConfigurazione();
		Boolean condizione = false;
		for (String key : handler2.getCatalogo().getMappaComponenti().keySet()) {
			for (Componente elemento : handler2.getCatalogo().getMappaComponenti().get(key)) {
				if (elemento.getCategoria().equals("Bundle")) {
					condizione = true;
					System.out.println(elemento);
				}
			}
		}
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		assertTrue(condizione);
	}
	
	
	@org.junit.jupiter.api.Test
	@DisplayName("Doppia Motherboard Test")
	void testDoppiaMotherboard() {
		System.out.println("---------------------------------------------");
		
		handler.selezionaCategoria("Motherboard");
		handler.selezionaComponente(6);
		System.out.println(handler.confermaComponente());
		
		handler.selezionaCategoria("CPU");
		handler.selezionaComponente(1);
		handler.confermaComponente();
		
		System.out.println(handler.terminaAssemblaggio());
		int condizione = 0;
		for (Componente elemento : handler.getConf().getListaComponenti()) {
			if (elemento.getCategoria() == "Motherboard") {
				condizione ++;
			}
		}
		assertTrue(condizione == 1);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Componente Incompatibile Test")
	void testIncompatibile() {
		handler.selezionaCategoria("PSU");
		handler.getConf().rimuoviComponenteInConfigurazione(handler.selezionaComponente(5));
		handler.selezionaComponente(8);
		System.out.println(handler.confermaComponente());
		System.out.println(handler.terminaAssemblaggio());
		Boolean condizione = true;
		for (Componente elemento : handler.getConf().getListaComponenti()) {
			if (elemento.getCategoria() == "PSU") {
				condizione = false;
			}
		}
		assertTrue(condizione);
		
	}
	
	
}
