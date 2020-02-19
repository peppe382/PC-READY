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
			Componente comp = console.getSistema().getHandlerComponenti().selezionaComponente(console.getInt("Inserisci l'id del componente che desideri mettere in promozione: "));
			if (comp != null) {
				console.print(console.getSistema().getHandlerComponenti().setPromozione(console.getDouble("Inserisci il tasso di sconto della promozione: ")));
			}else console.print("Il componente non esiste in catalogo: non eseguo l'operazione \n");
			if (!console.getYesNo("Vuoi inserire una nuova promozione? Inserisci Si o No: ")) {
				continua = false;
			}
		}
		
	}
	
}
