package interfaccia;

import java.util.List;

import dominio.Componente;
import dominio.CopiaComponente;

public class ComandoUC8 extends Comando {
	
	public ComandoUC8() {
		super(8, "Rimuovi un componente dal catalogo", true);
	}

	@Override
	public void esegui(Console console) {
		
		boolean continua = true;
		
		while (continua) {
			if (console.getSistema().getHandlerComponenti().rimuoviComponente(console.getInt("Inserisci l'id del componente che desideri eliminare dal catalogo: "))) {
				console.print("Rimozione avvenuta con successo \n");
			}else console.print("Operazione non riuscita \n");
			if (!console.getYesNo("Continuare con una nuova rimozione? Inserisci Si o No: ")) {
				continua = false;
			}
		}
	}
	
}
