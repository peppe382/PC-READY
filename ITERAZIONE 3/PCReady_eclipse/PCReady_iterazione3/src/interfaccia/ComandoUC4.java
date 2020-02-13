package interfaccia;

import java.util.List;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoUC4 extends Comando {

	public ComandoUC4() {
		super(4, "Aggiungi una copia di un componente", true);
	}

	@Override
	public void esegui(Console console) {
		
		console.print("Benvenuto! Dimmi il codice del componente di cui vuoi aggiungere una copia: ");
		int codice = console.getInt();
		console.print("Adesso dimmi la categoria a cui appartiene: ");
		String categoria = console.getString();
		
		Componente comp = console.getSistema().getHandlerComponenti().selezionaComponente(codice, categoria);
		
		console.print("Quante copie vuoi inserire: ");
		int num_copie = console.getInt();
		
		console.getSistema().getHandlerComponenti().setComponenteCorrente(comp);
		console.getSistema().getHandlerComponenti().creaCopie(num_copie);
		
		List<CopiaComponente> lista_copie = comp.getListaCopie();
		int counter = 1;
		for(CopiaComponente cop : lista_copie) {
			console.print("Codice della copia numero " + counter +": " + cop.getCodice()+"\n");
			counter++;
		}
		
		console.getSistema().getHandlerComponenti().salvaCatalogo();
		
	}

}
