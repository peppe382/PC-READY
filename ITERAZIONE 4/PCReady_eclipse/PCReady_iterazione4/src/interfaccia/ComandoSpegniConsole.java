package interfaccia;

public class ComandoSpegniConsole extends Comando {

	public ComandoSpegniConsole() {
		super(0, "Spegni la Console", false);
	}

	@Override
	public void esegui(Console console) {
		console.print("Grazie per aver usato PC Ready!\n");
		console.getSistema().spegniSistema();
		console.spegni();
	}

}
