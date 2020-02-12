package handlers;

import java.util.Map;

import dominio.Carrello;
import dominio.Catalogo;
import dominio.Cliente;
import dominio.Componente;
import dominio.CopiaComponente;
import dominio.Ordine;

public class AcquistoHandler {

	private Cliente clienteCorrente;
	private Carrello carrello;
	private Catalogo catalogo;
	private Ordine ordineCorrente;
	private Componente componenteCorrente;
	private Map<Integer, Componente> mappaCorrente;

	public AcquistoHandler(Catalogo catalogo) {
		// TODO Auto-generated constructor stub
		this.catalogo = catalogo;
	}
	
	public void iniziaAcquisto() {
		this.carrello = new Carrello();
	}
	
	public Map<Integer, Componente> selezionaCategoria(String idCategoria) {
		try {
			return this.mappaCorrente = this.catalogo.ottieniComponentiByCategoria(idCategoria);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Componente selezionaProdotto(int idProdotto) {
		try {
			this.componenteCorrente  = this.mappaCorrente.get(idProdotto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.componenteCorrente ;
	}
	
	//fai aggiungere String codice
	public CopiaComponente controllaDisponibilitaCopie(int codice) {

		Componente comp = catalogo.getComponente(codice);
		if(comp.getListaCopie().size() > 0){
			return comp.getListaCopie().get(comp.getListaCopie().size()); //se c'è ritorna l'ultima copia
		}
		else return null;
	}
	
	
}
