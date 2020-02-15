package interfaccia;

import java.util.List;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoUC9 extends Comando {
	
	public ComandoUC9() {
		super(9, "Inserisci una promozione", true);
	}

	@Override
	public void esegui(Console console) {
		
		boolean continua = true;
		
		while (continua) {
			console.print("Inserisci l'id del componente che desideri mettere in promozione : ");
			Componente comp = console.getSistema().getHandlerComponenti().selezionaComponente(console.getInt());
			if (comp != null) {
				console.print("Inserisci il tasso di sconto della promozione : ");
				console.print(console.getSistema().getHandlerComponenti().setPromozione(console.getDouble()));
			}else console.print("Il componente non esiste in catalogo: non eseguo l'operazione \n");
			console.print("Vuoi inserire una nuova promozione? Inserisci Si o No \n");
			if (!console.getYesNo()) {
				continua = false;
			}
		}
		
	}
	
}
