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
		
		console.getSistema().getHandlerComponenti().creaComponente(nome, categoria, consumo_energetico, prezzo, descrizione);
		
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

