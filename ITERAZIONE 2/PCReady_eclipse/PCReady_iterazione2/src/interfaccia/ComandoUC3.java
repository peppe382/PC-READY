package interfaccia;

import java.util.List;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoUC3 extends Comando {
	
	public ComandoUC3() {
		super(3, "Inserisci nuovo componente");
	}

	@Override
	public void esegui(Console console) {
		
		console.print("Benvenuto! Dimmi il nome del componente da aggiungere:");
		String nome = console.getString();
		console.print("Dimmi la categoria del componente da aggiungere:");
		String categoria = console.getString();
		console.print("Dimmi il Consumo del componente da aggiungere:");
		int consumo_energetico = console.getInt();
		console.print("Dimmi il prezzo del componente da aggiungere:");
		double prezzo = console.getDouble();
		console.print("Dimmi il descrizione del componente da aggiungere:");
		String descrizione = console.getString();
		
		switch(categoria) {
			case "GPU":
				console.print("Quanti slot occupa la tua GPU:");
				int slotGPU = console.getInt();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, slotGPU);
			case "PSU":
				console.print("Quanta potenza eroga il PSU:");
				int potenza = console.getInt();
				console.print("A che tipologia appartiene:");
				String tipologiaPSU = console.getString();
				console.print("Che forma ha:");
				String formFactor = console.getString();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, potenza, tipologiaPSU, formFactor);
			case "CPU":
				console.print("Socket della CPU:");
				String socket = console.getString();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, socket);
			case "Storage":
				console.print("Quanta memoria possiede lo storage:");
				String memoria = console.getString();
				console.print("Quale è la dimensione (2.5 o 3.5):");
				double dimensione = console.getDouble();
				console.print("Quale è la velocità:");
				int velocità = console.getInt();
				console.print("A che tipologia appartiene:");
				String tipologiaStorage = console.getString();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, memoria, dimensione, velocità, tipologiaStorage);
			case "RAM":
				console.print("A Quale tipologia appartiene la RAM:");
				String tipologiaRAM = console.getString();
				console.print("Quale è la sua frequenza:");
				int frequenza = console.getInt();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, tipologiaRAM, frequenza);
			case "Case":
				console.print("Quanle è il formFactor della PSU da poter inserire nel Case:");
				String formFactorPSU = console.getString();
				console.print("Quale è quello della Motherboard:");
				String formFactorMotherboard = console.getString();
				console.print("Quati slot possiede:");
				int slotCase = console.getInt();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, formFactorPSU, slotCase, formFactorMotherboard);
			case "Motherboard":
				console.print("Quanle è la socket della Motherboard:");
				String socketMotherboard = console.getString();
				console.print("Quale è il suo FormFactor:");
				String formFactorM = console.getString();
				console.print("Quale è la tipologia di RAM compatibile:");
				String tipologiaR = console.getString();
				console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione, socketMotherboard, formFactorM, tipologiaR);
		}		
		console.print("Quante copie del tuo componente vuoi aggiungere:");
		int num_copie = console.getInt();
		console.getSistema().getHandlerComponenti().creaCopie(num_copie);
		
		Componente comp = console.getSistema().getHandlerComponenti().getComponenteCorrente();
		List<CopiaComponente> lista_copie = comp.getListaCopie();
		int counter = 1;
		for(CopiaComponente cop : lista_copie) {
			console.print("Codice della copia " + counter +": " + cop.getCodice()+"\n");
			counter++;
		}
		
	}
}

