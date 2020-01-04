package dominio;

import java.util.*;
import java.util.stream.Collectors;

import Utility.Counter;

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
	
	private HashMap<Integer,Componente> mComp;
	
	public Categoria(String nome) {
		this.id = (int) Counter.getNextNumber(); //Id univoco
		this.nome = nome;
		this.mComp = new HashMap<Integer,Componente>();
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

	public HashMap<Integer,Componente> ottieniMappaComponenti() { //Il nome é stato adeguato al diagramma delle classi di progetto
		return this.mComp;
	}

	public void setCompList(HashMap<Integer,Componente> mComp) {
		this.mComp = mComp;
	}
	
	//
	//Funzioni previste dal diagramma delle classi di progetto//
	//
	
	public Map<Integer,Categoria> getMappaCategorie() {
		Map<Integer,Categoria> mappa = new HashMap<Integer,Categoria>();
		//Logica di inserimento: da un file JSON si prelevano tutte le categorie con i relativi ID
		return mappa;
	}
	
	public Componente getComponente (int idComponente) {
		Componente componente_richiesto = null;
		componente_richiesto = this.mComp.get(idComponente);
		return componente_richiesto;
	}
	
	public void aggiungiComponente(Componente c) {
		this.mComp.put(c.getId(), c);
	}
	
	
	
}
