package dominio;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class Cliente {

	private int id;
	private String nome, cognome, email, password;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
