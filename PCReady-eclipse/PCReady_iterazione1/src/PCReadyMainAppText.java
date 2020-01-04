import data.*;


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
		Parser par = new Parser("data/main_data.json");
		System.out.println(par.getJsonText());
	}

}
