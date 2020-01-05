package dominio;

import java.util.*;

import data.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class PCReady {
	
	private static PCReady singleton;
	
	private String versione = "0.1";

	private Componente c; // Componente corrente
	private Componente componenteSelezionato;
	private Configurazione conf; // Configurazione corrente
	
	private Map<Integer, Categoria> mCat;
	private Map<Integer, Componente> mappaComponentiSistema;
	private List<Configurazione> listaConfigurazioni;
	
	protected PCReady() {
		this.mCat = new HashMap<Integer, Categoria>();
		this.mappaComponentiSistema = new HashMap<Integer, Componente>();
		this.listaConfigurazioni = new LinkedList<Configurazione>();
	}
	
	public static synchronized PCReady getIstance() {
		if(singleton == null) {
			singleton = new PCReady();
			singleton.caricaSistema();
		}
		return singleton;
	}
	
	protected void caricaSistema() {
		// riempie il sistema con i dati conosciuti tramite la lettura di un
		// file JSON
		Parser par = new Parser("data/main_data.json");
		par.initialParsing();
		System.out.println("PC Ready correttamente caricato.\n");
		System.out.println(this.toString());
		System.out.println("-------------------------------------------------------------");
	}
	
	
	// Supporto
	
	public void aggiungiComponente(Componente comp) {
		this.mappaComponentiSistema.put(comp.getId(), comp);
	}
	
	public Componente selezionaComponente(int id) {
		return this.mappaComponentiSistema.get(id);
	}
	
	public void aggiungiCategoria(Categoria cat) {
		this.mCat.put(cat.getId(), cat);
	}
	
	public Categoria selezionaCategoria(int id) {
		return this.mCat.get(id);
	}
	
	public void aggiungiConfigurazione(Configurazione conf) {
		this.listaConfigurazioni.add(conf);
	}
	
	public String toString() {
		String str = "";
		str += "VERSIONE: "+this.versione+"\n\n";
		str += "COMPONENTE CORRENTE:\n"+(this.c == null ? "----" : this.c.toString())+"\n\n";
		str += "COMPONENTE SELEZIONATO:\n"+(this.componenteSelezionato == null ? "----" : this.componenteSelezionato.toString())+"\n\n";
		str += "CONFIGURAZIONE CORRENTE:\n"+(this.conf == null ? "----" : this.conf.toString())+"\n\n";
		str += "-------------------------------------------------\n\n";
		str += "CATEGORIE:\n";
		for(Map.Entry<Integer, Categoria> entry: mCat.entrySet()) {
			str += entry.getValue().toString();
			str += "\n\n";
		}
		str += "-------------------------------------------------\n\n";
		str += "COMPONENTI:\n";
		for(Map.Entry<Integer, Componente> entry: mappaComponentiSistema.entrySet()) {
			str += entry.getValue().toString();
			str += "\n\n";
		}
		str += "-------------------------------------------------\n\n";
		str += "CONFIGURAZIONI:\n";
		for(int i = 0; i < listaConfigurazioni.size(); i++) {
			str += listaConfigurazioni.get(i).toString();
			str += "\n\n";
		}
		return str;
	}
	
}
