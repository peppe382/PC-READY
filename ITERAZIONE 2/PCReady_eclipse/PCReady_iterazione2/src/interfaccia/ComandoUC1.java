package interfaccia;

public class ComandoUC1 extends Comando {

	public ComandoUC1() {
		super(1, "Crea una configurazione");
	}

	@Override
	public void esegui(Console console) {
		console.print("Benvenuto! Dimmi il nome della configurazione:");
		String stringa = console.getString();
		new CreaConfigurazione(console, stringa);
	}

}
