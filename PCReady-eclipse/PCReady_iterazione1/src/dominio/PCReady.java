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
	
	/* POSSIBILI GETTER e SETTER, MA IN REALTA' SONO FUNZIONI DI PROGETTO */
	
	public Componente selezionaComponente(int id) {
		Componente comp = null;
		try {
			comp = this.mappaComponentiSistema.get(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comp;
	}
	
	public Categoria selezionaCategoria(int id) {
		Categoria cat = null;
		try {
			cat = this.mCat.get(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	public void aggiungiConfigurazione(Configurazione conf) {
		this.listaConfigurazioni.add(conf);
	}
	
	/**
	 * Inizia una nuova Configurazione, utente o Bundle
	 */
	public void creaConfigurazione() {
		this.conf = new Configurazione();
	}
	
	public void confermaComponente() {
		this.aggiungiComponenteInConfigurazione(this.componenteSelezionato);
	}
	
	public void terminaAssemblaggio() {
		if(this.conf.controllaConfigurazione()) this.confermaConfigurazione();
	}
	
	public void confermaConfigurazione() {
		this.listaConfigurazioni.add(this.conf);
	}
	
	public void riepilogaConfigurazione() {
		String str = this.conf.toString();
	}
	
	public void infoConfigurazione(String nome, String descrizione, double sconto) {
		this.conf.generaBundle(nome, descrizione, sconto);
	}
	
	public Componente creaComponente(String nome, int codiceCategoria, int consumo, double prezzo, String descrizione) {
		this.c = new Componente(nome, consumo, prezzo, descrizione);
		this.aggiungiInMappa(this.c);
		this.aggiungiInCategoria(codiceCategoria, this.c);
		return this.c;
	}
	
	public void creaCopie(int numero) {
		this.c.aggiungiCopie(numero);
	}
	
	public void aggiungiInMappa(Componente c) {
		this.mappaComponentiSistema.put(c.getId(), c);
	}
	
	public void aggiungiInCategoria(int id, Componente c) {
		this.mCat.get(id).aggiungiComponente(c);
	}
	
	
	/********** GETTERS & SETTERS + TO-STRING **********/
	
	public void aggiungiComponente(Componente comp) {
		this.mappaComponentiSistema.put(comp.getId(), comp);
	}
	
	public void aggiungiComponenteInConfigurazione(Componente c) {
		this.conf.addComponente(c);
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
