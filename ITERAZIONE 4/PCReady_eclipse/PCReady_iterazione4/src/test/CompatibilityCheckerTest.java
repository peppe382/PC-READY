package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import dominio.*;
import dominio.componenti.*;
import handlers.ConfigurationHandler;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CompatibilityCheckerTest {

	private static Catalogo catalogo;
	private static ConfigurationHandler handler;
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
		CPU componente9 = new CPU("Intel Core i7500H", 300.90, 80, "Processore intel quad-core 3.7GHz"," FCLGA1151");
		Motherboard componente10 = new Motherboard("ASUS ROG X2M", 61.49, 0, "Motherboard ASUS socket FCLGA1151 , 8 porte usb 3.0", "FCLGA1151", "ATX", "DDR3");
		GPU componente11 = new GPU("Fake GPU", 970.99, 200, "Il meglio del meglio", 6);
		catalogo = new Catalogo();
		catalogo.aggiungiInCatalogo(componente1);
		catalogo.aggiungiInCatalogo(componente2);
		catalogo.aggiungiInCatalogo(componente3);
		catalogo.aggiungiInCatalogo(componente4);
		catalogo.aggiungiInCatalogo(componente5);
		catalogo.aggiungiInCatalogo(componente6);
		catalogo.aggiungiInCatalogo(componente7);
		catalogo.aggiungiInCatalogo(componente8);
		catalogo.aggiungiInCatalogo(componente9);
		catalogo.aggiungiInCatalogo(componente10);
		catalogo.aggiungiInCatalogo(componente11);
		handler = new ConfigurationHandler(catalogo);
	}
	
	
	@org.junit.jupiter.api.Test
	@DisplayName("CPU Incompatibile Test")
	void testA() {
		handler.selezionaCategoria("Motherboard");
		handler.selezionaComponente(6);
		handler.confermaComponente();
		
		handler.selezionaCategoria("CPU");
		handler.selezionaComponente(9);
		System.out.println("------------");
		System.out.println("\n"+handler.confermaComponente()+"\n");
		System.out.println("------------");
		
		handler.selezionaCategoria("CPU");
		Componente componente = handler.selezionaComponente(1);
		handler.confermaComponente();
		
		Componente componenteConfronto = handler.getConf().getListaComponenti().get(1);
		
		assertEquals(componente, componenteConfronto);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("MOTHERBOARD Incompatibile Test")
	void testB() {
		handler.selezionaCategoria("Motherboard");
		handler.getConf().rimuoviComponenteInConfigurazione(handler.selezionaComponente(6));
		
		handler.selezionaCategoria("Motherboard");
		handler.selezionaComponente(10);
		System.out.println("------------");
		System.out.println("\n"+handler.confermaComponente()+"\n");
		System.out.println("------------");
		
		handler.selezionaCategoria("Motherboard");
		Componente componente = handler.selezionaComponente(6);
		handler.confermaComponente();
		
		Componente componenteConfronto = handler.getConf().getListaComponenti().get(1);
		
		assertEquals(componente, componenteConfronto);
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Gpu Incompatibile Test")
	void testC(){
		
		handler.selezionaCategoria("Case");
		handler.selezionaComponente(4);
		handler.confermaComponente();
		
		
		handler.selezionaCategoria("GPU");
		handler.selezionaComponente(2);
		handler.confermaComponente();
		handler.selezionaComponente(11);
		handler.confermaComponente();
		
		int contatore = 0;
		
		for (Componente elemento : handler.getConf().getListaComponenti()) {
			if (elemento.getCategoria() == "GPU") {
				contatore ++;
			}
		}
		
		assertTrue(contatore == 1);
		
		
	}
}
