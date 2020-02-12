package dominio;

import java.util.Map;

public class Carrello {
	
	private int prezzoTotale;
	private int numeroPezzi;
	private Map<Integer,Componente> mappaComponentiCarrello;
	
	Componente componenteCorrente;
	public Carrello() {
		// TODO Auto-generated constructor stub
		this.numeroPezzi = 0;
		this.prezzoTotale = 0;
	}
	
	public Carrello(Componente componente) {
		// TODO Auto-generated constructor stub
		this.componenteCorrente = componente;
	}
	
	public void aggiungiComponente(Componente componente, CopiaComponente copia) {
		mappaComponentiCarrello.put(copia.getCodice(), componente);
		this.prezzoTotale += componente.getPrezzo();
		this.numeroPezzi += 1;
	}
	
	public Map<Integer,Componente> getMappaComponenti(){
		return mappaComponentiCarrello;
	}
	

}
