package dominio;

import java.util.concurrent.atomic.AtomicLong;

public class Cliente {

	private int id;
	private String nome, cognome, email, password;
	private static final AtomicLong counter = new AtomicLong(0);
	
	public Cliente(int id, String nome, String cognome, String email, String password) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
	
	public Cliente(String nome, String cognome, String email, String password) {
		setId();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
	

	public int getId() {
		return id;
	}
	
	public void setId() {
		this.id = (int) counter.incrementAndGet();
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
