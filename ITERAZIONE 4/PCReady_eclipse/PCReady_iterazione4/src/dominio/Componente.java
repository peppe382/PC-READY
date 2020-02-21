package dominio;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import dominio.componenti.*;

public class Componente {

	private String categoria;
	private int id; // id univoco del Componente
	private String nome; // nome da mostrare all'utente
	private double prezzo; // prezzo in Euro
	private int consumo_energetico; // Consumo in Watt
	private String descrizione; // Descrizione completa
	private static final AtomicLong counter = new AtomicLong(0);
	private double promozione;
	
	private List<CopiaComponente> listaCopie; // Elenco di tutte le copie disponibili del componente
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
	public Componente(String nome, double prezzo, int consumo_energetico, String descrizione, String categoria) {
		this.setId();
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setConsumo_energetico(consumo_energetico);
		this.setDescrizione(descrizione);
		this.setCategoria(categoria);
		this.setListaCopie(new LinkedList<CopiaComponente>());
		this.promozione = 0.00;
	}
	
	public Componente(int id, String nome, double prezzo, int consumo_energetico, String descrizione, String categoria) {
		this.id = id;
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setConsumo_energetico(consumo_energetico);
		this.setDescrizione(descrizione);
		this.setCategoria(categoria);
		this.setListaCopie(new LinkedList<CopiaComponente>());
		this.promozione = 0.00;
	}
	
	public Componente() {  //Valori di default
		this.setId();
		this.nome = "default";
		this.prezzo = 0.00;
		this.consumo_energetico = 0;
		this.descrizione = "default";
		this.setListaCopie(new LinkedList<CopiaComponente>());
		this.promozione = 0.00;
	}

	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public int getConsumo_energetico() {
		return consumo_energetico;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public List<CopiaComponente> getListaCopie() {
		return listaCopie;
	}

	public void setId() {
		this.id = (int) counter.incrementAndGet();
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public void setConsumo_energetico(int consumo_energetico) {
		this.consumo_energetico = consumo_energetico;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setListaCopie(List<CopiaComponente> listaCopie) {
		this.listaCopie = listaCopie;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public double getPromozione() {
		return promozione;
	}
	
	public String impostaPromozione(double promozione) {
		if (promozione > 0) {
			this.promozione = promozione;
			return "PREZZO COMPONENTE CON PROMOZIONE APPLICATA: "+(getPrezzo()-((getPromozione()*getPrezzo())/100))+"\n";
		}return "TASSO DI PROMOZIONE NON MODIFICATO \n";
	}
	

	//  toString
	public String toString() {
		String str = "";
		str += "#"+this.getId()+" : "+this.getNome()+"\n";
		str += "CATEGORIA: "+this.getCategoria()+"\n";
		str += "EUR "+this.getPrezzo()+", consumo: "+this.getConsumo_energetico()+"W\n";
		str += "DESC: "+this.getDescrizione()+"\n";
		str += "Copie disponibili: "+this.getListaCopie().size()+"\n";
		str += "TASSO DI PROMOZIONE: "+this.getPromozione()+"\n";
		return str;
	}
	
	public static void setCounter(long id) {
		Componente.counter.set(id);
	}
	
	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO
	
	/**
	 * Aggiungi un numero prefissato di Copie al Componente
	 * @param numero
	 * @return i Codici delle Copie generate
	 */
	public String aggiungiCopie(int numero) {
		String str = "";
		for (int i=0; i < numero; i++) {
			CopiaComponente copia = new CopiaComponente(); 
			this.listaCopie.add(copia);
			str += "COPIA #" +i+"  CODICE: "+copia.getCodice()+"\n";
		}
		return str;
	}
	
	/**
	 * Assegna una Copia appena creata ad un Componente esistente
	 * @param c: la Copia da aggiungere
	 */
	public void aggiungiCopia(CopiaComponente c) {
		this.listaCopie.add(c);
	}
	
	/**
	 * Controlla l'esistenza di almeno N Copie del Componente
	 */
	public CopiaComponente controllaDisponibilitaCopie(int numeroDoppioni) {
		
		if (this.listaCopie.size() > numeroDoppioni) {
			if (!this.categoria.equals("Configurazione") || !this.categoria.equals("Bundle")) {
				//Logica: prendo la prima copia componente disponibile!
				return this.listaCopie.get(numeroDoppioni); 
			}	
		}
		return null;
		
	}
	
	/**
	 * Rimuove Copie del Componente
	 */
	public void rimozioneCopie(List<CopiaComponente> listaCopie) {
		for (CopiaComponente elemento : listaCopie) {
			if (this.listaCopie.contains(elemento)) {
				this.listaCopie.remove(elemento);
			}
		}
	}
	
}
