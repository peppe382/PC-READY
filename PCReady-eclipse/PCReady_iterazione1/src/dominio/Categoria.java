package dominio;

import java.util.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class Categoria {

	private int id;
	private String nome;
	
	private List<Componente> lComp;
	
	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
		this.lComp = new LinkedList<Componente>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Componente> getCompList() {
		return lComp;
	}

	public void setCompList(List<Componente> lComp) {
		this.lComp = lComp;
	}
	
}
