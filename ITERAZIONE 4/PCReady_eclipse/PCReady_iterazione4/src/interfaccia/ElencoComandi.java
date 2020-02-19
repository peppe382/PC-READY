package interfaccia;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public final class ElencoComandi {
	public static final Comando[] elencoCliente = {new ComandoUC1(), new ComandoUC5(), new ComandoUC10() ,new ComandoSpegniConsole()};
	public static final Comando[] elencoAmministratore = {new ComandoUC2(), new ComandoUC3(), new ComandoUC4(), new ComandoUC8(), new ComandoUC9(), new ComandoSpegniConsole()};
	
	public static Comando getComandoCliente(int codice) {
		for(Comando cmd : ElencoComandi.elencoCliente) if(cmd.getCodiceComando() == codice) return cmd;
		return null;
	}
	
	public static Comando getComandoAmministratore(int codice) {
		for(Comando cmd : ElencoComandi.elencoAmministratore) if(cmd.getCodiceComando() == codice) return cmd;
		return null;
	}
	
	public static String stringCliente() {
		String str = "";
		str += "Elenco dei comandi disponibili:\n";
		str += "--------------------------------\n";
		for(Comando cmd : elencoCliente) str += cmd.getCodiceComando() + ": " + cmd.getDescrizioneComando() + "\n";
		str += "--------------------------------\n";
		return str;
	}
	
	public static String stringAmministratore() {
		String str = "";
		str += "Elenco dei comandi disponibili:\n";
		str += "--------------------------------\n";
		for(Comando cmd : elencoAmministratore) str += cmd.getCodiceComando() + ": " + cmd.getDescrizioneComando() + "\n";
		str += "--------------------------------\n";
		return str;
	}
	
	
}
