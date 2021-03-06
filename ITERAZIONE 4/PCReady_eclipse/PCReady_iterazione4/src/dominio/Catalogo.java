package dominio;

import java.util.*;

import dominio.componenti.*;


public class Catalogo {

	private Map<String,ArrayList<Componente>> mappaComponenti;
	
	//------------------------------------------------------------------------------------------
	
	//COSTRUTTORI
	
	public Catalogo(Map<String, ArrayList<Componente>> mappaComponenti) {
		this.mappaComponenti = mappaComponenti;
	}
	
	public Catalogo() {
		this.mappaComponenti = new HashMap<String, ArrayList<Componente>>();
		//Necessito una logica di inserimento delle componenti gi� esistenti: vedi Parser
		//Tutte le chiavi della mappa devono essere inizializzate...
	}
	
	//------------------------------------------------------------------------------------------
	
	// GETTERS e SETTERS

	public Map<String, ArrayList<Componente>> getMappaComponenti() {
		return mappaComponenti;
	}

	public void setMappaComponenti(Map<String, ArrayList<Componente>> mappaComponenti) {
		this.mappaComponenti = mappaComponenti;
	}
	
	public void aggiungiCategoria(String cat) {
	      try{
	        this.mappaComponenti.put(cat, new ArrayList<Componente>());
	      }catch(Exception e){
	        e.printStackTrace();
	      }
	}
	
	//------------------------------------------------------------------------------------------
	
	//FUNZIONI di PROGETTO
	
	/**
	 * Ottieni tutti i Componenti di una stessa Categoria, con il relativo id di Componente
	 * @param idCategoria: il nome della Categoria richiesta
	 * @return una Mappa IdNumerico-Componente per mostrare tutti i Componenti di una stessa Categoria
	 */
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
	
	/**
	 * Aggiungi un nuovo Componente al Catalogo
	 * @param componente: il Componente da aggiungere
	 */
	public void aggiungiInCatalogo(Componente componente) {
		try {
			
			ArrayList <Componente> arrayComponenti = new ArrayList<Componente>();
			
			if(this.mappaComponenti.containsKey(componente.getCategoria())) arrayComponenti = new ArrayList<Componente>(this.mappaComponenti.get(componente.getCategoria()));
			else arrayComponenti = new ArrayList<Componente>();
			arrayComponenti.add(componente);
			
			this.mappaComponenti.put(componente.getCategoria(), arrayComponenti);
			
		}catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Conferma la Configurazione in corso aggiungendola al Catalogo
	 * @param conf: la Configurazione da aggiungere
	 */
	public void salvaConfigurazione(Configurazione conf) {
		this.aggiungiInCatalogo((Componente) conf);
	}
	
	/**
	 * Ottieni il Componente, dati id e Categoria
	 */
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
	
	/**
	 * Ottieni il Componente, dato solo l'id
	 */
	public Componente getComponente(int id) {
		try {
			for(Map.Entry<String, ArrayList<Componente>> entry : this.mappaComponenti.entrySet()) {
				ArrayList<Componente> tempList = entry.getValue();
				for(Componente c : tempList) {
					if(c.getId() == id) return c;
				}
			}
			return null;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Rimuovi un Componente, dato il suo id
	 */
	public boolean rimuoviDaMappa(int id) {
		try {
			for(String key : this.mappaComponenti.keySet()) {
				ArrayList<Componente> tempList = this.mappaComponenti.get(key);
				for(Componente c : tempList) {
					if(c.getId() == id) {
						tempList.remove(c);
						this.mappaComponenti.put(key, tempList);
						return true;
					}
				}
			}
			return false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Rimuove una Configurazione dal Catalogo
	 */
	public void rimuoviConfigurazioni(int id) {
		for (Componente comp : this.mappaComponenti.get("Configurazione")) {
			Configurazione conf = (Configurazione) comp;
			boolean contiene = false;
			for (Componente elemento : conf.getListaComponenti()){
				if (elemento.getId() == id) {
					contiene = true;
				}
			}
			if (contiene == true) {
				ArrayList<Componente> lista = new ArrayList<Componente>(this.mappaComponenti.get("Configurazione"));
				lista.remove(comp);
				this.mappaComponenti.put("Configurazione", lista);
			}
		}
		for (Componente comp : this.mappaComponenti.get("Bundle")) {
			Bundle bund = (Bundle) comp;
			boolean contiene = false;
			for (Componente elemento : bund.getListaComponenti()){
				if (elemento.getId() == id) contiene = true;
			}
			if (contiene == true) {
				ArrayList<Componente> lista = new ArrayList<Componente>(this.mappaComponenti.get("Bundle"));
				lista.remove(comp);
				this.mappaComponenti.put("Bundle", lista);
			}
		}
	}
	

	@Override
	public String toString() {
		String str = "---CATALOGO--- \n";
		for (String key : this.mappaComponenti.keySet()) {
			for (Componente elemento : this.mappaComponenti.get(key)) {
				str += "--- \n" +elemento.toString()+ "\n ---";
			}
		}
		return str;
	}
	
	
}