package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import dominio.*;

class CategoriaTest {
	
	private static Categoria categoria;
	private static Componente componente;
	
	@BeforeAll
	static void setupAll() {
		categoria = new Categoria("CPU");
		componente = new Componente("MSI PRO CARBON", 10, 57.99, "Motherboard Micro ATX");
	}

	@org.junit.jupiter.api.Test
	void testAggiungiComponente() {
		categoria.aggiungiComponente(componente);
		assertEquals(categoria.getComponente(componente.getId()), componente);
	}
	

}
