package interfaccia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoUC3 extends Comando {
	
	private static final String[] categorie = {"CPU","PSU","GPU","Storage","RAM","Motherboard","Case"};
	private Map<Integer, String> mappaCorrispondenzaCategorie; 
	
	public ComandoUC3() {
		super(3, "Inserisci nuovo componente", true);
		int i = 1;
		this.mappaCorrispondenzaCategorie = new HashMap<Integer, String>();
		for (String cat : categorie) {
			this.mappaCorrispondenzaCategorie.put(i,cat);
			i++;
		}
	}

	@Override
	public void esegui(Console console) {
		
		console.print("Benvenuto! \n");
		String nome = console.getString("Dimmi il nome del componente da aggiungere:\n");
		console.print(categorieList());
		console.print("Seleziona una categoria inserendo il suo codice intero: \n"); 
		String categoria = this.mappaCorrispondenzaCategorie.get(console.getInt());
		int consumo_energetico = console.getInt("Dimmi il Consumo del componente da aggiungere:\n");
		double prezzo = console.getDouble("Dimmi il prezzo del componente da aggiungere:\n");
		String descrizione = console.getString("Dimmi la descrizione del componente da aggiungere:\n");
		
		switch(categoria) {
			case "GPU":
				int slotGPU = console.getInt("Quanti slot occupa la tua GPU:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, slotGPU);
				break;
			case "PSU":
				int potenza = console.getInt("Quanta potenza eroga il PSU:\n");
				String tipologiaPSU = console.getString("A che tipologia appartiene:\n");
				String formFactor = console.getString("Che forma ha:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, potenza, tipologiaPSU, formFactor);
				break;
			case "CPU":
				String socket = console.getString("Socket della CPU:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, socket);
				break;
			case "Storage":
				String memoria = console.getString("Quanta memoria possiede lo storage:\n");
				double dimensione = 0;
				do {
				dimensione = console.getDouble("Inserisci la dimensione (2.5 o 3.5):\n");
				}while (dimensione != 2.5 && dimensione != 3.5);
				int velocita = console.getInt("Inserisci la velocita' di lettura del device :\n");
				String tipologiaStorage = console.getString("A che tipologia appartiene:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, memoria, dimensione, velocita, tipologiaStorage);
				break;
			case "RAM":
				String tipologiaRAM = console.getString("A quale tipologia appartiene la RAM:\n");
				int frequenza = console.getInt("Inserisci la frequenza:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, tipologiaRAM, frequenza);
				break;
			case "Case":
				String formFactorPSU = console.getString("Inserisci il formFactor della PSU che supporta il case:\n");
				String formFactorMotherboard = console.getString("Inserisci il formFactor della Motherboard che supporta il case:\n");
				int slotCase = console.getInt("Inserisci quanti slot laterali possiede il case:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, formFactorPSU, slotCase, formFactorMotherboard);
				break;
			case "Motherboard":
				String socketMotherboard = console.getString("Inserisci la socket della Motherboard:\n");
				String formFactorM = console.getString("Inserisci il FormFactor:\n");
				String tipologiaR = console.getString("Inserisci la tipologia di RAM compatibile con la Motherboard: ");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, socketMotherboard, formFactorM, tipologiaR);
				break;
		}
		int num_copie = console.getInt("Quante copie del tuo componente vuoi aggiungere? ");
		//Controllo se l'inserimento é andato a buon fine, se il tutto é ok procedo inserendo le copie
		System.out.println(console.getSistema().getHandlerComponenti().getComponenteCorrente());
		console.getSistema().getHandlerComponenti().creaCopie(num_copie);
	
		Componente comp = console.getSistema().getHandlerComponenti().getComponenteCorrente();
		List<CopiaComponente> lista_copie = comp.getListaCopie();
		int counter = 1;
		for(CopiaComponente cop : lista_copie) {
			console.print("Codice della copia numero " + counter +": " + cop.getCodice()+"\n");
			counter++;
		}
		console.getSistema().getHandlerComponenti().salvaCatalogo();
	}
	
	
	private String categorieList() {
		int i=1;
		String str = "";
		str += "\nElenco delle categorie e operazioni disponibili:\n";
		str += "--------------------------------\n";
		for(String cat : categorie) {
			str += "Codice: "+i +" Categoria: "+cat +"\n";
			i++;
		}
		str += "--------------------------------\n";
		return str;

	}
}

