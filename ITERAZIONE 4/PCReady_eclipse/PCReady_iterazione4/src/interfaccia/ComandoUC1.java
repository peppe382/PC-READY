package interfaccia;

import java.util.HashMap;
import java.util.Map;

import dominio.*;
import handlers.ConfigurationHandler;

public class ComandoUC1 extends Comando {
	
	private static final String[] categorie = {"CPU","PSU","GPU","Storage","RAM","Motherboard","Case", "Annulla operazione", "Elimina un componente inserito"};
	private Map<Integer, String> mappaCorrispondenzaCategorie; 
	private ConfigurationHandler handlerConfigurazione;

	public ComandoUC1() {
		super(1, "Crea una configurazione", false);
		int i = 1;
		this.mappaCorrispondenzaCategorie = new HashMap<Integer, String>();
		for (String cat : categorie) {
			this.mappaCorrispondenzaCategorie.put(i,cat);
			i++;
		}
	}

	@Override
	public void esegui(Console console) {
		//Genero l'handler per il caso d'uso, che a sua volta genera la configurazione.
		console.getSistema().setHandlerConfigurazioni();
		this.handlerConfigurazione = console.getSistema().getHandlerConfigurazioni();
		//Genero una variabile per il loop
		Boolean fine = true;
		String cat = null;
		while (fine) {
			console.print(categorieList());
			console.print("Seleziona una categoria inserendo il suo codice intero: \n"); 
			Integer selezione = console.getInt();
			if (selezione != null) {
				if (selezione == 9) { //Eliminazione di un componente esistente
					console.print("Ecco i componenti della configurazione attuale: \n");
					console.print(this.handlerConfigurazione.getConf().toString());
					console.print("Inserisci l'id del componente da eliminare: \n");
					console.print(this.handlerConfigurazione.eliminaComponenteConfigurazione(console.getInt()));
				}
				if (selezione != 8) {
					if (selezione != 9) {
						cat = this.mappaCorrispondenzaCategorie.get(selezione.intValue());
						console.print("Seleziona una componente, tra quelle che vengono mostrate a video, inserendo il suo codice numerico:\n");
						Map<Integer, Componente> mappaComponenti = this.handlerConfigurazione.selezionaCategoria(cat);
						for (Integer key : mappaComponenti.keySet()) {
							console.print("\n ---Codice: "+key +"  Componente: "+ mappaComponenti.get(key) +"--- \n");
						}
						console.print("---INSERISCI CODICE COMPONENTE--- \n");
						Componente componenteAttuale = this.handlerConfigurazione.selezionaComponente(console.getInt());
						if (componenteAttuale != null) {
							console.print("\n\n ECCO I DETTAGLI DEL COMPONENTE SELEZIONATO"+componenteAttuale.toString());
							if (console.getYesNo("\n Ti soddisfa il componente selezionato? Inserisci Si o No: ")) {
								console.print(this.handlerConfigurazione.confermaComponente());
								/*Vengono mostrati a video eventuali messaggi di incompatibilit� previsti
								 dalla clase Configuration Handler...
								 */
							}else console.print("Non inserisco il componente...");
							
							if (!console.getYesNo("\nDesideri continuare con l'inserimento componenti? ")) { //Nel caso di valore true o non valido continuo con l'inserimento delle componenti
								console.print("---TERMINA ASSEMBLAGGIO: ESECUZIONE DEI CONTROLLI--- \n");
								if (this.handlerConfigurazione.terminaAssemblaggio() == true) {
									fine = false;
									console.print("---ASSEMBLAGGIO ANDATO A BUON FINE: RIEPILOGO--- \n");
									console.print(this.handlerConfigurazione.getStringaComunicazioni());
									this.handlerConfigurazione.confermaConfigurazione();
									console.print("---SALVATAGGIO IN CATALOGO IN CORSO--- \n");
									console.getSistema().getHandlerComponenti().salvaCatalogo();
								}
								else {
									console.print("---ASSEMBLAGGIO NON RIUSCITO--- \n");
									console.print(this.handlerConfigurazione.getStringaComunicazioni());
									console.print("---RIPROVA INSERENDO UN NUOVO COMPONENTE--- \n");
								}
								
							}
							
						}else console.print("Hai inserito un codice non valido");
						
					}
					
				}else fine = false;
				
			}else console.print("Hai inserito un codice non valido");
		}
		
	}
	
	private String categorieList() {
		int i=1;
		String str = "";
		str += "\n Elenco delle categorie e operazioni disponibili:\n";
		str += "--------------------------------\n";
		for(String cat : categorie) {
			str += "Codice: "+i +" Categoria: "+cat +"\n";
			i++;
		}
		str += "--------------------------------\n";
		return str;

	}

}
