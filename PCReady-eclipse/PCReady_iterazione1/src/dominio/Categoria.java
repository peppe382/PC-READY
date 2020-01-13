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

	private int id; // id univoco della Categoria
	private String nome; // nome da mostrare all'utente

	private Map<Integer, Componente> mComp; // mappa <idComponente, Componente> di tutti i Componente appartenenti alla Categoria
	
	
	/********** COSTRUTTORI *********/
	
	/**
	 * Costruttore di default per Categorie pre-esistenti
	 * @param id
	 * @param nome
	 */
	public Categoria(int id, String nome) {
		this.setId(id);
		this.setNome(nome);

		this.mComp = new HashMap<Integer, Componente>();

	}
	
	/**
	 * Costruttore per Categorie con id auto-generato
	 * @param nome
	 */
	public Categoria(String nome) {
		this.id = (int) Counter.getNextNumber(); //Id univoco
		this.nome = nome;

		this.mComp = new HashMap<Integer, Componente>();

	}
	
	
	/********** FUNZIONI di PROGETTO **********/

	/**
	 * Ottiene la mappa contenente tutti i Componente.
	 * (Ridondanza col getter di default)
	 *
	 * @return la mappa delle Componente mComp
	 */
	public Map<Integer,Componente> ottieniMappaComponenti() { 
		return this.getMappaComponenti();
	}

	/**
	 * Ottiene il Componente corrispondente in mComp all'id fornito
	 * @param idComponente
	 * @return il Componente corrispondente, oppure null se non è presente
	 */
	public Componente getComponente (int idComponente) {
		Componente componente_richiesto = null;
		try{
			componente_richiesto = this.mComp.get(idComponente);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return componente_richiesto;
	}
	
	/**
	 * Aggiunge un Componente alla mappa Componente della Categoria
	 * @param comp: il Componente generato da aggiungere
	 */
	public void aggiungiComponente(Componente comp) {
		this.mComp.put(comp.getId(), comp);
	}
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
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
	
	public Map<Integer,Componente> getMappaComponenti(){
		return this.mComp;
	}
	
	public void setMappaComponenti(HashMap<Integer,Componente> mComp) {
		this.mComp = mComp;
	}
	
	public String toString() {
		String str = "";
		str += "#"+this.getId()+" : "+this.getNome()+"\n";
		str += "Componenti presenti: --------------\n";
		for(Map.Entry<Integer, Componente> entry: mComp.entrySet()) {
			str += entry.getValue().toString();
			str += "\n\n";
		}
		return str;
	}
}
