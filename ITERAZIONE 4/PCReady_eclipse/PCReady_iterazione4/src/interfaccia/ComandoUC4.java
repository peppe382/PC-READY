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
		
		String categoria = console.getString("Benvenuto! Dimmi la categoria del componente di cui vuoi aggiungere una copia: ");
		int codice = console.getInt("Adesso dimmi il suo codice: ");
		
		Componente comp = console.getSistema().getHandlerComponenti().selezionaComponente(codice, categoria);
		
		int num_copie = console.getInt("Quante copie vuoi inserire? ");
		
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
