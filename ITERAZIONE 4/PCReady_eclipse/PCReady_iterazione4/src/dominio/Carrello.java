package dominio;

import java.util.*;

import dominio.componenti.*;

public class Carrello {
	
	private double prezzoTotale;
	private int numeroPezzi;
	private Map<Componente,List<CopiaComponente>> mappaComponentiCarrello;
	
	
	//Costruttori
	public Carrello() {
		// TODO Auto-generated constructor stub
		this.numeroPezzi = 0;
		this.prezzoTotale = 0;
		this.mappaComponentiCarrello = new HashMap<Componente,List<CopiaComponente>>();
	}
	
	
	//Getters e setters
	public Map<Componente,List<CopiaComponente>> getMappaComponenti(){
		return mappaComponentiCarrello;
	}
	
	
	public double getPrezzoTotale() {
		return prezzoTotale;
	}


	public int getNumeroPezzi() {
		return numeroPezzi;
	}


	public Map<Componente, List<CopiaComponente>> getMappaComponentiCarrello() {
		return mappaComponentiCarrello;
	}


	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}


	public void setNumeroPezzi(int numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}


	public void setMappaComponentiCarrello(Map<Componente, List<CopiaComponente>> mappaComponentiCarrello) {
		this.mappaComponentiCarrello = mappaComponentiCarrello;
	}


	//Funzioni di progetto
	public int controllaDoppioni(Componente comp) {
		int doppioni = 0;
		
		for(Componente key : this.mappaComponentiCarrello.keySet()) {
			if (!key.getCategoria().equals("Configurazione")) {
				if (key.getId() == comp.getId()) {
					doppioni += this.mappaComponentiCarrello.get(key).size();
				}
			}
			else {
				//Se ho a che fare con una configurazione devo controllare la sua lista componenti
				Configurazione confAttuale = (Configurazione)key;
				for (Componente elemento : confAttuale.getListaComponenti()) {
					if (elemento.getId() == comp.getId()) {
						doppioni ++;
					}
				}
			}
		}
		
		return doppioni;
	}
	
	
	public double aggiungiComponente(Componente componente, List<CopiaComponente> listaCopie) {
		List<CopiaComponente> listaAttuale = new ArrayList<CopiaComponente>();
		try {
			if (this.mappaComponentiCarrello.containsKey(componente)) {
				for (CopiaComponente elemento : this.mappaComponentiCarrello.get(componente)){
					listaAttuale.add(elemento);
				}
				for (CopiaComponente elemento : listaCopie) {
					listaAttuale.add(elemento);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if (listaAttuale.size() == 0) { //Ovvero se non é presente almeno un elemento di quel tipo
			this.mappaComponentiCarrello.put(componente, listaCopie);
		} else this.mappaComponentiCarrello.put(componente, listaAttuale);
		
		if (componente.getCategoria().equals("Bundle")) {
			Bundle bundle = (Bundle) componente;
			double prezzoScontato = componente.getPrezzo()*(bundle.getSconto()*100);
			this.prezzoTotale += prezzoScontato;
		}
		else this.prezzoTotale += componente.getPrezzo();
		this.numeroPezzi += 1;
		return this.prezzoTotale;
	}
	
	

}
