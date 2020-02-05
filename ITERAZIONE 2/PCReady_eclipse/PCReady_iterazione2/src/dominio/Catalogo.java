package dominio;

import java.util.*;

import dominio.componenti.Configurazione;

public class Catalogo {

	private Map<String,ArrayList<Componente>> mappaComponenti;
	
	
	
	//  Costruttori
	
	public Catalogo(Map<String, ArrayList<Componente>> mappaComponenti) {
		this.mappaComponenti = mappaComponenti;
	}
	
	public Catalogo() {
		this.mappaComponenti = new HashMap<String, ArrayList<Componente>>();
		//Necessito una logica di inserimento delle componenti gi� esistenti: vedi Parser
		//Tutte le chiavi della mappa devono essere inizializzate...
	}
	
	
	
	//  Getters e setters

	public Map<String, ArrayList<Componente>> getMappaComponenti() {
		return mappaComponenti;
	}

	public void setMappaComponenti(Map<String, ArrayList<Componente>> mappaComponenti) {
		this.mappaComponenti = mappaComponenti;
	}
	
	
	
	//  Funzioni di progetto
	
	public Map<Integer, Componente> ottieniComponentiByCategoria(String idCategoria){
		Map<Integer, Componente> mappa = new HashMap<Integer, Componente>();
		try { //Evito eventuali errori per l'identificativo della categoria
			for (Componente elemento : this.mappaComponenti.get(idCategoria)) {
				mappa.put(elemento.getId(), elemento);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mappa;
	}
	
	
	public void aggiungiInCatalogo(Configurazione configurazione) {
		//La funzione � universale sia per un Bundle sia per una Configurazione
		this.mappaComponenti.get(configurazione.getCategoria()).add(configurazione);
	}
	
	
	public void aggiungiInCatalogo(Componente componente) {
		this.mappaComponenti.get(componente.getCategoria()).add(componente);
	}
	
	
	public Componente getComponente(int id, String categoria) {
		try {
			for (Componente elemento : this.mappaComponenti.get(categoria)) {
				if (elemento.getId() == id) {
					return elemento;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
