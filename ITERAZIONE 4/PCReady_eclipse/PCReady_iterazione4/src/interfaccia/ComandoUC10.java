package interfaccia;

import java.util.HashMap;
import java.util.Map;

import dominio.*;
import handlers.AcquistoHandler;
import handlers.ConfigurationHandler;

public class ComandoUC10 extends Comando {
	
	private AcquistoHandler handlerAcquisto;

	public ComandoUC10() {
		super(10, "Modifica informazioni di spedizione di un ordine", false);
	}

	@Override
	public void esegui(Console console) {
		console.getSistema().setHandlerAcquisto(console.getClienteCorrente());
		this.handlerAcquisto = console.getSistema().getHandlerAcquisto();
		boolean continua = true;
		
		while (continua) {
			console.print("Recupero la lista degli ordini gia' eseguiti \n");
			String messaggio = this.handlerAcquisto.ottieniOrdineCliente();
			console.print(messaggio);
			if (!messaggio.equals("Non vi sono ordini da modificare \n")) {
				console.print("Inserisci l'id dell'ordine da modificare");
				int id = console.getInt();
				console.print("Inserisci un nuovo indirizzo");
				String indirizzo = console.getString();
				console.print("Inserisci una nuova citta'");
				String citta = console.getString();
				console.print("Inserisci un nuovo CAP");
				int CAP = console.getInt();
				
				console.print(this.handlerAcquisto.aggiornaInformazioni(id, indirizzo, citta, CAP));
				
				console.print("Continuare con una nuova modifica? Inserisci Si o No \n");
				if (!console.getYesNo()) {
					continua = false;
				}
			}else continua = false;
		}
		
	}

}
