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
	
	private List<CopiaComponente> listaCopie; // Elenco di tutte le copie disponibili del componente
	
	
	
	//  Costruttori
	
	public Componente(String nome, double prezzo, int consumo_energetico, String descrizione, String categoria) {
		this.setId();
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setConsumo_energetico(consumo_energetico);
		this.setDescrizione(descrizione);
		this.setCategoria(categoria);
		this.setListaCopie(new LinkedList<CopiaComponente>());
	}
	
	public Componente(int id, String nome, double prezzo, int consumo_energetico, String descrizione, String categoria) {
		this.id = id;
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setConsumo_energetico(consumo_energetico);
		this.setDescrizione(descrizione);
		this.setCategoria(categoria);
		this.setListaCopie(new LinkedList<CopiaComponente>());
	}
	
	public Componente() {  //Valori di default
		this.setId();
		this.nome = "default";
		this.prezzo = 0.00;
		this.consumo_energetico = 0;
		this.descrizione = "default";
		this.setListaCopie(new LinkedList<CopiaComponente>());
	}



	//Getters e Setters
	
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
	
	//  toString
	public String toString() {
		String str = "";
		str += "#"+this.getId()+" : "+this.getNome()+"\n";
		str += "CATEGORIA: "+this.getCategoria()+"\n";
		str += "EUR "+this.getPrezzo()+", consumo: "+this.getConsumo_energetico()+"W\n";
		str += "DESC: "+this.getDescrizione()+"\n\n";
		str += "Copie disponibili: "+this.getListaCopie().size();
		return str;
	}
	
	
	//  Funzioni di progetto
	
	public String aggiungiCopie(int numero) {
		String str = "";
		for (int i=0; i < numero; i++) {
			CopiaComponente copia = new CopiaComponente(); 
			this.listaCopie.add(copia);
			str += "COPIA #" +i+"  CODICE: "+copia.getCodice()+"\n";
		}
		return str;
	}
	
	
	public void aggiungiCopia(CopiaComponente c) {
		this.listaCopie.add(c);
	}
	
	
	public CopiaComponente controllaDisponibilitaCopie(int numeroDoppioni) {
		
		if (this.listaCopie.size() > numeroDoppioni) {
			if (!this.categoria.equals("Configurazione") || !this.categoria.equals("Bundle")) {
				//Logica: prendo la prima copia componente disponibile!
				return this.listaCopie.get(numeroDoppioni); 
			}	
		}
		return null;
		
	}
	
	
	public void rimozioneCopie(List<CopiaComponente> listaCopie) {
		for (CopiaComponente elemento : listaCopie) {
			if (this.listaCopie.contains(elemento)) {
				System.out.println("RIMUOVO: "+elemento);
				this.listaCopie.remove(elemento);
			}
		}
	}
	
	
}
