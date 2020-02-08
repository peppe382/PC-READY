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
	
	public void aggiungiCategoria(String cat) {
        try{
          this.mappaComponenti.put(cat, new ArrayList<Componente>());
        }catch(Exception e){
          e.printStackTrace();
        }
  }

  public void aggiungiInCatalogo(Componente componente) {
      try {
          ArrayList <Componente> arrayComponenti = new ArrayList<Componente>();
          arrayComponenti.add(componente);
          ArrayList <Componente> arrayMappa = this.mappaComponenti.get(componente.getCategoria());
          if (arrayMappa == null) {
              aggiungiCategoria(componente.getCategoria());
              this.mappaComponenti.put(componente.getCategoria(), arrayComponenti);
          }
          else {
              for (Componente elemento : arrayMappa) {
                  arrayComponenti.add(elemento);
              }
              this.mappaComponenti.put(componente.getCategoria(), arrayComponenti);
          }
      }catch(Exception e){
          e.printStackTrace();
      }
  }
	
	
	public void salvaConfigurazione(Configurazione conf) {
		this.aggiungiInCatalogo((Componente) conf);
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
}
