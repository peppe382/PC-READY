package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dominio.Categoria;
import dominio.Componente;
import dominio.CopiaComponente;

class ComponenteTest {
	
	private static Componente componente;

	@BeforeAll
	static void setupAll() {
		componente = new Componente("MSI PRO CARBON", 10, 57.99, "Motherboard Micro ATX");
	}

	@org.junit.jupiter.api.Test
	void testAggiungiCopie() {
		componente.aggiungiCopie(4);
		for (CopiaComponente elemento : componente.getListaCopie()) {
			assertNotNull(elemento);
			System.out.println(elemento.getCodice());
		}
	}
}
