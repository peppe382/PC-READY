package interfaccia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.*;
import handlers.*;

public class ComandoUC5 extends Comando {
	

	private static final String[] categorie = {"CPU","PSU","GPU","Storage","RAM","Motherboard","Case","Configurazione","Bundle" ,"Annulla operazione", "Elimina un prodotto inserito"};
	private static final String[] pagamenti = {"Mastercard","Visa","Prepagata generica", "Pay-Pal"};
	private Map<Integer, String> mappaCorrispondenzaCategorie; 
	private Map<Integer, String> mappaCorrispondenzaPagamenti; 
	private AcquistoHandler handlerAcquisto;
	
	public ComandoUC5() {
		super(5, "Effettua acquisti", true);
		int i = 1;
		int j = 1;
		this.mappaCorrispondenzaCategorie = new HashMap<Integer, String>();
		this.mappaCorrispondenzaPagamenti = new HashMap<Integer, String>();
		for (String cat : categorie) {
			this.mappaCorrispondenzaCategorie.put(i,cat);
			i++;
		}
		for (String cat : pagamenti) {
			this.mappaCorrispondenzaPagamenti.put(j,cat);
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
					Integer selezione = console.getInt("Seleziona una categoria inserendo il suo codice intero: ");
					if (selezione != null) {
						if (selezione == 11) { //Eliminazione di un componente esistente
							console.print("Ecco i componenti della configurazione attuale: \n");
							console.print(this.handlerAcquisto.getCarrello().getMappaComponentiCarrello().toString());
							console.print("\nInserisci l'id del componente da eliminare: \n");
							console.print(this.handlerAcquisto.eliminaComponenteAcquisto(console.getInt()));
						}
						if (selezione != 10) {
							if (selezione != 11) {
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
									if (console.getYesNo("\n Ti soddisfa il componente selezionato? Inserisci Si o No:")) {
										console.print(this.handlerAcquisto.aggiungiInCarrello());
										/*Vengono mostrati a video eventuali messaggi di incompatibilita previsti
										 dalla clase Configuration Handler...
										 */
									}else console.print("Non inserisco il componente... \n");
									if (!console.getYesNo("\nDesideri continuare con l'inserimento componenti? ")) { //Nel caso di valore true o non valido continuo con l'inserimento delle componenti
										if (!this.handlerAcquisto.isEmptyCarrello()) {
											console.print("---TERMINA ACQUISTO: INIZIALIZZAZIONE ORDINE--- \n");
											String indirizzo = console.getString("Inserisci l'indirizzo di spedizione:\n");
											String citta = console.getString("Inserisci la citta':\n");
											int CAP = console.getInt("Inserisci il CAP:\n");
											if (indirizzo!=null && citta!=null && CAP!=0) {
												handlerAcquisto.terminaAcquisto(indirizzo, citta, CAP);
												
												Integer selezionePagamento = -1;
												do {
													console.print(pagamentiList());
													selezionePagamento = console.getInt("Seleziona il metodo di pagamento inserendo il suo codice: ");
												}while (!this.mappaCorrispondenzaPagamenti.containsKey(selezionePagamento));
												
												Integer numeroCarta = console.getInt("Inserisci il numero della tua carta: ");
												
												Integer cvv = console.getInt("Inserisci il CVV: ");
												
												console.print(handlerAcquisto.selezionaModalitaDiPagamento(this.mappaCorrispondenzaPagamenti.get(selezionePagamento), numeroCarta, cvv));
												handlerAcquisto.rimuoviCopieComponente();
												console.print("Acquisto terminato \n");
												fine = false;
											}
											
										}else console.print("Non puoi inserire un ordine vuoto! \n");
										
									}else console.print("Continua la scelta dei prodotti! \n");
									
								}else console.print("Hai inserito un codice non valido \n");
								
							}
							
						}else fine = false;
						
					}else console.print("Hai inserito un codice non valido \n");
				}
	}
	
	
	private String categorieList() {
		int i=1;
		String str = "";
		str += "\n Elenco delle categorie e comandi disponibili:\n";
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
		str += "\n Elenco delle modalit� di pagamento disponibili:\n";
		str += "--------------------------------\n";
		for(String pag : pagamenti) {
			str += "Codice: "+i +" Metodo di pagamento: "+pag +"\n";
			i++;
		}
		str += "--------------------------------\n";
		return str;
	}
}
