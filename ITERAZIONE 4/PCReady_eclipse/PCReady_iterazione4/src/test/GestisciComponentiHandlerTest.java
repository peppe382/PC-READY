package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dominio.*;
import dominio.componenti.*;
import handlers.GestisciComponentiHandler;

class GestisciComponentiHandlerTest {
    private static GestisciComponentiHandler handler;
    private static Componente componenteCorrente;
    private static RAM ram;
    private static Motherboard motherboard;
    private static Catalogo catalogoCorrente;
        // TODO Auto-generated constructor stub

    @BeforeAll
    static void setupAll() {
        handler = new GestisciComponentiHandler();
        catalogoCorrente = handler.getCatalogo();
        ram = new RAM("Corsaire RAM DDR4 4GB Stick", 41.99, 5, "RAM DDR4 3000MHz", "DDR4", 3000);
        motherboard = new Motherboard("MSI Pro Carbon 420M", 61.49, 0, "Motherboard MSI socket AM4, 6 porte usb 3.0", "AM4", "microATX", "DDR4");
    }

    /*@Test
    void creaComponenteTest() {
        handler.creaComponente(ram.getNome(), ram.getCategoria(), ram.getConsumo_energetico(), ram.getPrezzo(), ram.getDescrizione(), ram.getTipologia(), ram.getFrequenza());
        System.out.println(handler.getComponenteCorrente().getNome());
        assertNotNull(handler.selezionaComponente(ram.getId(), ram.getCategoria()));
    }*/

    @Test
    void creaCopieTest() {
        int num_copie = 3;
        handler.setComponenteCorrente(ram);
        int dim_lista = ram.getListaCopie().size();
        handler.creaCopie(num_copie);
        assertNotEquals(handler.getComponenteCorrente().getListaCopie().size(), dim_lista);

    }

    @Test
    void selezionaComponenteTest() {

        Catalogo catalogo1 = new Catalogo();
        catalogo1.aggiungiInCatalogo(ram);
        handler.setCatalogo(catalogo1);
        Componente c = handler.selezionaComponente(ram.getId(), ram.getCategoria());
        assertNotNull(c);

    }
    
    @Test
    @DisplayName("Set promozione Test")
    void setPromozioneTest() {

    	handler.setComponenteCorrente(new Componente("componente1", 33.99, 4, "ComponenteGPU", "GPU"));
    	double promoAttuale = handler.getComponenteCorrente().getPromozione();
    	System.out.println(promoAttuale);
    	handler.setPromozione(22);
    	double promoApplicata = handler.getComponenteCorrente().getPromozione();
    	System.out.println(promoApplicata);
    	assertNotEquals(promoAttuale, promoApplicata);
    }
    
    // ***NOTA*** CREA COMPONENTE VA IN ERRORE PERCHE CONTROLLA LA PRESENZA DEL COMPONENTE APPENA RIMOSSO    
    @Test
    @DisplayName("Rimuovi componente test")
    void rimuoviComponenteTest() {
    	ram = new RAM("Corsaire RAM DDR3 16GB", 65.99, 6, "RAM 3000MHz", "DDR4", 3000);
    	handler.creaComponente(ram.getNome(), ram.getCategoria(), ram.getConsumo_energetico(), ram.getPrezzo(), ram.getDescrizione(), ram.getTipologia(), ram.getFrequenza());
    	System.out.println(handler.getCatalogo());
        handler.rimuoviComponente(5); // metto uno perchè sappiamo che nel catalogo è presente un componente con id 1
        System.out.println(handler.getCatalogo());
        assertNull(handler.getCatalogo().getComponente(5));
    }


}
