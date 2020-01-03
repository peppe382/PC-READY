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
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	
}
