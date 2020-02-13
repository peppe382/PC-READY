package interfaccia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.*;
import handlers.*;

public class ComandoUC5 extends Comando {
	
	private static final String[] categorie = {"CPU","PSU","GPU","Storage","RAM","Motherboard","Case"};
	private static final String[] pagamenti = {"Mastercard","Visa","Prepagata generica", "Pay-Pal"};
	private Map<Integer, String> mappaCorrispondenzaCategorie; 
	private Map<Integer, String> mappaCorrispondenzaPagamenti; 
	private AcquistoHandler handlerAcquisto;
	
	public ComandoUC5() {
		super(5, "Effettua acquisti", true);
		int i = 1;
		int j = 1;
		this.mappaCorrispondenzaCategorie = new HashMap<Integer, String>();
		for (String cat : categorie) {
			this.mappaCorrispondenzaCategorie.put(i,cat);
			i++;
		}
		for (String cat : pagamenti) {
			this.mappaCorrispondenzaCategorie.put(i,cat);
			j++;
		}
	}

	@Override
	public void esegui(Console console) {
		
		//Genero l'handler per il caso d'uso, che a sua volta genera la configurazione.
				console.getSistema().setHandlerAcquisto(console.getClienteCorrente());
				this.handlerAcquisto = console.getSistema().getHandlerAcquisto();
				//Genero una variabile per il loop
				Boolean fine = true;
				String cat = null;
				while (fine) {
					console.print(categorieList());
					console.print("Seleziona una categoria inserendo il suo codice intero: \n"); 
					Integer selezione = console.getInt();
					if (selezione != null) {
						cat = this.mappaCorrispondenzaCategorie.get(selezione.intValue());
						console.print("Seleziona una componente, tra quelle che vengono mostrate a video, inserendo il suo codice numerico:\n");
						Map<Integer, Componente> mappaComponenti = this.handlerAcquisto.selezionaCategoria(cat);
						for (Integer key : mappaComponenti.keySet()) {
							console.print("\n ---Codice: "+key +"  Componente: "+ mappaComponenti.get(key) +"--- \n");
						}
						console.print("---INSERISCI CODICE COMPONENTE--- \n");
						Componente componenteAttuale = this.handlerAcquisto.selezionaProdotto(console.getInt());
						if (componenteAttuale != null) {
							console.print("\n\n ECCO I DETTAGLI DEL COMPONENTE SELEZIONATO"+componenteAttuale.toString());
							console.print("\n Ti soddisfa il componente selezionato? Inserisci Si o No \n");
							if (console.getYesNo()) {
								console.print(this.handlerAcquisto.aggiungiInCarrello());
								/*Vengono mostrati a video eventuali messaggi di incompatibilita previsti
								 dalla clase Configuration Handler...
								 */
							}else console.print("Non inserisco il componente...");
							console.print("Desideri continuare con l'inserimento componenti? \n");
							if (!console.getYesNo()) { //Nel caso di valore true o non valido continuo con l'inserimento delle componenti
								console.print("---TERMINA ACQUISTO: INIZIALIZZAZIONE ORDINE--- \n");
								console.print("Inserisci l'indirizzo di spedizione \n");
								String indirizzo = console.getString();
								console.print("Inserisci la citta' \n");
								String citta = console.getString();
								console.print("Inserisci il CAP \n");
								int CAP = 0;
								CAP = console.getInt();
								if (indirizzo!=null && citta!=null && CAP!=0) {
									handlerAcquisto.terminaAcquisto(indirizzo, citta, CAP);
									
									console.print(pagamentiList());
									console.print("Seleziona il metodo di pagamento inserendo il suo codice \n");
									Integer selezionePagamento = 0;
									do {
										selezionePagamento = console.getInt();
									}while (this.mappaCorrispondenzaPagamenti.containsKey(selezionePagamento));
									
									console.print("Inserisci il numero della tua carta \n");
									Integer numeroCarta = console.getInt();
									
									console.print("Inserisci l'indirizzo di spedizione \n");
									Integer cvv = console.getInt();
									
									console.print(handlerAcquisto.selezionaModalitaDiPagamento(this.mappaCorrispondenzaPagamenti.get(selezionePagamento), numeroCarta, cvv));
									handlerAcquisto.rimuoviCopieComponente();
									console.print("Acquisto terminato \n");
									fine = false;
								}
							}else console.print("Continua la scelta dei prodotti! \n");
							
						}else console.print("Hai inserito un codice non valido \n");
						
					}else console.print("Hai inserito un codice non valido \n");
				}
	}
	
	
	private String categorieList() {
		int i=1;
		String str = "";
		str += "\n Elenco delle categorie disponibili:\n";
		str += "--------------------------------\n";
		for(String cat : categorie) {
			str += "Codice: "+i +" Categoria: "+cat +"\n";
			i++;
		}
		str += "--------------------------------\n";
		return str;
	}
	
	private String pagamentiList() {
		int i=1;
		String str = "";
		str += "\n Elenco delle modalitá di pagamento disponibili:\n";
		str += "--------------------------------\n";
		for(String pag : pagamenti) {
			str += "Codice: "+i +" Metodo di pagamento: "+pag +"\n";
			i++;
		}
		str += "--------------------------------\n";
		return str;
	}
}
