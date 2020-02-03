import dominio.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class PCReadyMainAppText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PCReady sistema = PCReady.getInstance();
		System.out.println("Salvo...");
		sistema.salvaSistema();
		System.out.println("Sistema salvato correttamente!");
	}

}
