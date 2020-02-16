package interfaccia;

import java.util.List;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoUC3 extends Comando {
	
	public ComandoUC3() {
		super(3, "Inserisci nuovo componente", true);
	}

	@Override
	public void esegui(Console console) {
		
		console.print("Benvenuto!");
		String nome = console.getString("Dimmi il nome del componente da aggiungere:\n");
		String categoria = console.getString("Dimmi la categoria del componente da aggiungere:\n");
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
				double dimensione = console.getDouble("Quale é la dimensione (2.5 o 3.5):\n");
				int velocita = console.getInt("Quale é la velocità:\n");
				String tipologiaStorage = console.getString("A che tipologia appartiene:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, memoria, dimensione, velocita, tipologiaStorage);
				break;
			case "RAM":
				String tipologiaRAM = console.getString("A Quale tipologia appartiene la RAM:\n");
				int frequenza = console.getInt("Quale é la sua frequenza:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, tipologiaRAM, frequenza);
				break;
			case "Case":
				String formFactorPSU = console.getString("Quale é il formFactor della PSU da poter inserire nel Case:\n");
				String formFactorMotherboard = console.getString("Quale é quello della Motherboard:\n");
				int slotCase = console.getInt("Quanti slot possiede:\n");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, formFactorPSU, slotCase, formFactorMotherboard);
				break;
			case "Motherboard":
				String socketMotherboard = console.getString("Quale é la socket della Motherboard:\n");
				String formFactorM = console.getString("Quale é il suo FormFactor:\n");
				String tipologiaR = console.getString("Quale é la tipologia di RAM compatibile: ");
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, socketMotherboard, formFactorM, tipologiaR);
				break;
		}
		int num_copie = console.getInt("Quante copie del tuo componente vuoi aggiungere? ");
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
}

