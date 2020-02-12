package interfaccia;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public class ElencoComandi {
	public static final Comando[] elenco = {};
	
	public String toString() {
		String str = "";
		str += "Elenco dei comandi disponibili:\n";
		str += "--------------------------------\n";
		for(Comando cmd : elenco) str += cmd.getCodiceComando() + ": " + cmd.getDescrizioneComando() + "\n";
		str += "--------------------------------\n";
		return str;
	}
}
