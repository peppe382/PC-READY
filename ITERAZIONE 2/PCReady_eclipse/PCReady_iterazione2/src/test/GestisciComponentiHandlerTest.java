package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Catalogo;
import dominio.Componente;
import handlers.GestisciComponentiHandler;

class GestisciComponentiHandlerTest {
	private static GestisciComponentiHandler handler;
	private static Componente componenteCorrente;
	private static Catalogo catalogoCorrente;
		// TODO Auto-generated constructor stub
	
	@BeforeAll
	static void setupAll() {
		handler = new GestisciComponentiHandler();
		catalogoCorrente = handler.getCatalogo();
		componenteCorrente = new Componente("Corsaire RAM DDR4 4GB Stick", 41.99, 5, "RAM DDR4 3000MHz", "RAM");
	}
	
	@Test
	void creaComponenteTest() {
		handler.creaComponente(componenteCorrente.getNome(), componenteCorrente.getCategoria(), componenteCorrente.getConsumo_energetico(), componenteCorrente.getPrezzo(), componenteCorrente.getDescrizione());
		assertNotNull(handler.selezionaComponente(componenteCorrente.getId(), componenteCorrente.getCategoria()));
	}
	
	@Test
	void creaCopieTest() {
		int num_copie = 3;
		handler.setComponenteCorrente(componenteCorrente);
		int dim_lista = componenteCorrente.getListaCopie().size();
		handler.creaCopie(num_copie);
		assertNotEquals(handler.getComponenteCorrente().getListaCopie().size(), dim_lista);
		
	}
	
	@Test
	void selezionaComponenteTest() {
		
		//handler.creaComponente(componenteCorrente.getNome(), componenteCorrente.getCategoria(), componenteCorrente.getConsumo_energetico(), componenteCorrente.getPrezzo(), componenteCorrente.getDescrizione());
		Catalogo catalogo1 = new Catalogo();
		catalogo1.aggiungiInCatalogo(componenteCorrente);
		handler.setCatalogo(catalogo1);
		Componente c = handler.selezionaComponente(componenteCorrente.getId(), componenteCorrente.getCategoria());
		assertNotNull(c);
			
	}


}
