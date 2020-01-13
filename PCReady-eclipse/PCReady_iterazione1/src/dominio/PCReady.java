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
	
	
	/********** COSTRUTTORI e FUNZ. SINGLETON *********/
	
	/**
	 * Costruttore singleton protected. Si potra ottenere l'unica istanza singleton
	 * usando la funzione "getIstance()"
	 */
	protected PCReady() {
		this.mCat = new HashMap<Integer, Categoria>();
		this.mappaComponentiSistema = new HashMap<Integer, Componente>();
		this.listaConfigurazioni = new LinkedList<Configurazione>();
	}
	
	/**
	 * Si assicura che non esistano più istanze di PCReady
	 * @return l'istanza singleton di PCReady
	 */
	public static synchronized PCReady getIstance() {
		if(singleton == null) {
			singleton = new PCReady();
			singleton.caricaSistema();
		}
		return singleton;
	}
	
	/**
	 * Da chiamare solo all'avvio, riempie il sistema con le informazioni salvate su JSON
	 */
	protected void caricaSistema() {
		// riempie il sistema con i dati conosciuti tramite la lettura di un
		// file JSON
		Parser par = new Parser("data/test_data.json");
		par.initialParsing();
		System.out.println("PC Ready correttamente caricato.\n");
		System.out.println(this.toString());
		System.out.println("-------------------------------------------------------------");
	}
	
	/**
	 * Aggiorna il JSON con tutte le nuove informazioni generate
	 */
	public void salvaSistema() {
		Parser.saveAll("data/test_data.json");
	}
	
	
	/********** FUNZIONI di PROGETTO **********/
	
	/**
	 * Inizia una nuova Configurazione, utente o Bundle
	 */
	public void creaConfigurazione() {
		this.conf = Configurazione.generaConfigurazione();
	}
	
	/**
	 * Seleziona una Categoria
	 * @param id: id della Categoria
	 * @return la Categoria cercata se esistente, null altrimenti
	 */
	public Categoria getCategoriaById(int id) {
		Categoria cat = null;
		try {
			cat = this.mCat.get(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	/**
	 * Seleziona un Componente
	 * @param id: id del Componente
	 * @return il Componente cercato se esistente, null altrimenti
	 */
	public Componente getComponente(int id) {
		Componente comp = null;
		try {
			comp = this.mappaComponentiSistema.get(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comp;
	}
	
	
	public String confermaComponente() {
		
	}
	
	public boolean terminaAssemblaggio() {
		
	}
	
	public void aggiungiConfigurazione(Configurazione conf) {
		this.listaConfigurazioni.add(conf);
	}
	
	public void aggiungiComponenteInConfigurazione() {
		this.conf.addComponente(this.componenteSelezionato);
	}
	
	public void confermaConfigurazione() {
		
	}
	
	public void riepilogaComponenti() {
		
	}
	
	public String infoConfigurazione(double sconto, String nome, String descrizione) {
		
	}
	
	public Componente creaComponente(String nome, int codiceCategoria, double consumo, String descrizione) {
		
	}
	
	public String creaCopie(int numero) {
		
	}
	
	public void aggiungiInLista(Componente c) {
		
	}
	
	public void aggiungiInCategoria(Componente c) {
		
	}
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
	public void aggiungiComponente(Componente comp) {
		this.mappaComponentiSistema.put(comp.getId(), comp);
	}
	
	public void aggiungiCategoria(Categoria cat) {
		this.mCat.put(cat.getId(), cat);
	}
	
	public Map<Integer, Categoria> ottieniMappaCategorie(){
		return this.mCat;
	}
	
	public List<Configurazione> ottieniListaConfigurazioni(){
		return this.listaConfigurazioni;
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
