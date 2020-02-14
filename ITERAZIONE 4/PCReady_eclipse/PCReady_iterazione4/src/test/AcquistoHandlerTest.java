package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dominio.*;
import dominio.componenti.*;
import handlers.*;

class AcquistoHandlerTest {

	/*AVVISO
	 * TUTTI I PRINT PRESENTI SONO INSERITI PER UNA PURA CONFERMA VISIVA DELLA LOGICA DELLE
	 * OPERAZIONI CHE AVVENGONO NEL CORSO DELL'ESECUZIONE,
	 * E NON SONO PER NESSUN MOTIVO ALCUNO SOSTITUTIVI AGLI ASSERT INSERITI COME CONDIZIONE DI 
	 * SUCCESSO/INSUCCESSO DEL TEST
	 */
	
	
	private static Catalogo catalogo;
	private static AcquistoHandler acquistoH;
	private static Cliente clienteCorrente;
	private static ConfigurationHandler handlerC;
	private static PCReady sistema;
	@BeforeAll
	static void setupAll() {
		sistema = PCReady.getInstance();
		clienteCorrente = new Cliente("Gabriello", "Costy", "gabrielloPazzerello@gmail.com", "passwordInChiaro");
		sistema.setHandlerConfigurazioni();
		acquistoH = new AcquistoHandler(sistema.getHandlerConfigurazioni().getCatalogo(), clienteCorrente);
		handlerC = new ConfigurationHandler(sistema.getHandlerConfigurazioni().getCatalogo());
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Aggiungi al carrello test")
	void aggiuntaTest(){
		acquistoH.iniziaAcquisto();
		acquistoH.selezionaCategoria("CPU");
		acquistoH.selezionaProdotto(1);
		acquistoH.aggiungiInCarrello();
		
		acquistoH.selezionaCategoria("CPU");  //Controllo funzione doppione
		acquistoH.selezionaProdotto(1);
		acquistoH.aggiungiInCarrello();
		
		acquistoH.selezionaCategoria("CPU");  //Controllo terza copia componente non presente
		acquistoH.selezionaProdotto(1);
		acquistoH.aggiungiInCarrello();
		
		assertTrue(acquistoH.getCarrello().getMappaComponentiCarrello().get(acquistoH.getComponenteCorrente()).size() == 2);
	}
	
	
	@org.junit.jupiter.api.Test
	@DisplayName("Aggiungi configurazione test")
	void aggiuntaConfigurazioneTest(){
		//Aggiungo una configurazione al catalogo
		handlerC.selezionaCategoria("CPU");
		handlerC.selezionaComponente(1);
		handlerC.confermaComponente();
		
		handlerC.selezionaCategoria("GPU");
		handlerC.selezionaComponente(7);
		handlerC.confermaComponente();
		
		handlerC.selezionaCategoria("RAM");
		handlerC.selezionaComponente(2);
		handlerC.confermaComponente();
		
		handlerC.selezionaCategoria("Case");
		handlerC.selezionaComponente(4);
		handlerC.confermaComponente();
		
		handlerC.selezionaCategoria("PSU");
		handlerC.selezionaComponente(5);
		handlerC.confermaComponente();
		
		handlerC.selezionaCategoria("Motherboard");
		handlerC.selezionaComponente(6);
		handlerC.confermaComponente();
		
		handlerC.selezionaCategoria("Storage");
		handlerC.selezionaComponente(3);
		handlerC.confermaComponente();
		handlerC.terminaAssemblaggio();
		handlerC.confermaConfigurazione();
		//System.out.println(handlerC.getConf().getId());
		
		acquistoH.selezionaCategoria("Configurazione");
		acquistoH.selezionaProdotto(3);
		acquistoH.aggiungiInCarrello();
		
		acquistoH.selezionaCategoria("CPU");
		acquistoH.selezionaProdotto(1);
		acquistoH.aggiungiInCarrello();
		
		acquistoH.selezionaCategoria("CPU");
		acquistoH.selezionaProdotto(1);
		acquistoH.aggiungiInCarrello();
		
		int verifica = 0;
		//Controllo che vi siano un totale di due prodotti, di cui uno sia una configurazione
		for (Componente elemento  : acquistoH.getCarrello().getMappaComponentiCarrello().keySet()){
			if (elemento.getCategoria().equals("CPU") || elemento.getCategoria().equals("Configurazione")){
				verifica++;
			}
		}
		
		assertTrue(verifica == 2);
		
		System.out.println(acquistoH.getCarrello().getPrezzoTotale());
		
	}
	
	@org.junit.jupiter.api.Test
	@DisplayName("Ordine test")
	void ordineTest(){
		acquistoH.terminaAcquisto("Via delle pere N4", "Ragusa", 97100);
		//System.out.println(acquistoH.selezionaModalitaDiPagamento("VISA", 2112435786, 456));
		acquistoH.rimuoviCopieComponente();
		
		assertEquals(acquistoH.getOrdineCorrente().getCitta(), "Ragusa");
	}
	
	
	@org.junit.jupiter.api.Test
	@DisplayName("Ottieni Ordine test")
	void ottieniOrdineClienteTest(){
		acquistoH.setClienteCorrente(new Cliente("pippo","franco","pippo@franco.it","francopippo"));
		String ordineCorrente = acquistoH.ottieniOrdineCliente();
		//Prendo la stessa lista dell' utente pippo@franco.it direttamente dal PCReady essendo sicuro che vada a buon fine e paragono le due
		List<Ordine> lista = sistema.getListaOrdiniCliente("pippo@franco.it");
		String riepilogoOrdineCliente = "Ordini dell'utente pippo@franco.it: \n\n";
		for(Ordine ordine : lista) {
			 riepilogoOrdineCliente += ordine.toString();
		}
		assertEquals(ordineCorrente, riepilogoOrdineCliente);
	}

	
	@org.junit.jupiter.api.Test
	@DisplayName("Aggiorna ordine Ordine test")
	void aggiornaInformazioniTest(){
		String ordineAttuale = "";
		String ordineModificato = "";
		List<Ordine> lista1 = sistema.getListaOrdiniCliente("pippo@franco.it");
		for(Ordine ordine : lista1) {
			 if(ordine.getId() == 0) {
				ordineAttuale = ordine.toString();
				System.out.println(ordineAttuale);
			 }
		}
		acquistoH.aggiornaInformazioni(0, "Via pippo 23", "Catania", 8888);
		List<Ordine> lista2 = sistema.getListaOrdiniCliente("pippo@franco.it");
		for(Ordine ordine : lista2) {
			 if(ordine.getId() == 0) {
				ordineModificato = ordine.toString();
				System.out.println("\n\n\n-------------\n\n\n"+ordineModificato);
			 }
		}
		assertNotEquals(ordineAttuale, ordineModificato);
	}

	
}
