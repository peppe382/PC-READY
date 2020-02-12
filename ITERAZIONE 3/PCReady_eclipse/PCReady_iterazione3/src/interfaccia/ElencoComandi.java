package interfaccia;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public final class ElencoComandi {
	public static final Comando[] elenco = {new ComandoUC1(), new ComandoUC2(), new ComandoUC3(), new ComandoUC4(), new ComandoSpegniConsole()};
	
	public static Comando getComando(int codice) {
		for(Comando cmd : ElencoComandi.elenco) if(cmd.getCodiceComando() == codice) return cmd;
		return null;
	}
	
	public static String string() {
		String str = "";
		str += "Elenco dei comandi disponibili:\n";
		str += "--------------------------------\n";
		for(Comando cmd : elenco) str += cmd.getCodiceComando() + ": " + cmd.getDescrizioneComando() + "\n";
		str += "--------------------------------\n";
		return str;
	}
	
	
}
