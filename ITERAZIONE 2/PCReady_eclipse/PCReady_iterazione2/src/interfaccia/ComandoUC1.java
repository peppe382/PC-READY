package interfaccia;

import java.util.HashMap;
import java.util.Map;

import dominio.*;
import handlers.ConfigurationHandler;

public class ComandoUC1 extends Comando {
	
	private static final String[] categorie = {"CPU","PSU","GPU","Storage","RAM","Motherboard","Case"};
	private Map<Integer, String> mappaCorrispondenzaCategorie; 
	private ConfigurationHandler handlerConfigurazione;

	public ComandoUC1() {
		super(1, "Crea una configurazione");
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
		console.getSistema().setHandlerComponenti();
		this.handlerConfigurazione = console.getSistema().getHandlerConfigurazioni();
		System.out.println(console.getSistema().getHandlerComponenti().getCatalogo().getComponente(1));
		//Genero una variabile per il loop
		Boolean fine = true;
		String cat = null;
		while (fine) {
			console.print(categorieList());
			console.print("Seleziona una categoria inserendo il suo codice intero: \n"); 
			Integer selezione = console.getInt();
			if (selezione != null) {
				cat = this.mappaCorrispondenzaCategorie.get(selezione.intValue());
				console.print("Seleziona una componente, tra quelle che vengono mostrate a video, inserendo il suo codice numerico: \n");
				Map<Integer, Componente> mappaComponenti = this.handlerConfigurazione.selezionaCategoria(cat);
				for (Integer key : mappaComponenti.keySet()) {
					console.print("---Codice: "+key +"Componente: "+ mappaComponenti.get(key) +"--- \n");
				}
				console.print("---INSERISCI CODICE COMPONENTE---");
				Componente componenteAttuale = this.handlerConfigurazione.selezionaComponente(console.getInt());
				if (componenteAttuale != null) {
					console.print("\n\n ECCO I DETTAGLI DEL COMPONENTE SELEZIONATO"+componenteAttuale.toString());
					console.print("Ti soddisfa il componente selezionato? Inserisci Si o No");
					if (console.getYesNo() == true) {
						console.print(this.handlerConfigurazione.confermaComponente());
						/*Vengono mostrati a video eventuali messaggi di incompatibilitá previsti
						 dalla clase Configuration Handler...
						 */
					}else console.print("Non inserisco il componente...");
					
					console.print("Desideri continuare con l'inserimento componenti?");
					if (console.getYesNo() == false) { //Nel caso di valore true o non valido continuo con l'inserimento delle componenti
						console.print("---TERMINA ASSEMBLAGGIO: ESECUZIONE DEI CONTROLLI---");
						if (this.handlerConfigurazione.terminaAssemblaggio() == true) {
							fine = false;
							console.print("---ASSEMBLAGGIO ANDATO A BUON FINE: RIEPILOGO---");
							console.print(this.handlerConfigurazione.getStringaComunicazioni());
							this.handlerConfigurazione.confermaConfigurazione();
						}
						else {
							console.print("---ASSEMBLAGGIO NON RIUSCITO---");
							console.print(this.handlerConfigurazione.getStringaComunicazioni());
							console.print("---RIPROVA INSERENDO UN NUOVO COMPONENTE---");
						}
						
					}
					
				}else console.print("Hai inserito un codice non valido");
				
			}else console.print("Hai inserito un codice non valido");
		}
		
	}
	
	private String categorieList() {
		int i=1;
		String str = "";
		str += "\n Elenco delle categorie disponibili:\n";
		str += "--------------------------------\n";
		for(String cat : categorie) {
			str += "Codice: "+i +"Categoria: "+cat +"\n";
			i++;
		}
		str += "--------------------------------\n";
		return str;

	}

}
