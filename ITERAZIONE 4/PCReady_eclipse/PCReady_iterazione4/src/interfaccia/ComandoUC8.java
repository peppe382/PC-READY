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
		
		console.print("Inserisci l'id del componente che desideri eliminare dal catalogo : ");
		if (console.getSistema().getHandlerComponenti().rimuoviComponente(console.getInt())) {
			console.print("Rimozione avvenuta con successo \n");
		}else console.print("Operazione non riuscita \n");
	}
	
}
