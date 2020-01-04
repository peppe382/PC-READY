package dominio;

import java.util.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */
public class Componente {

	private int id;
	private String nome;
	private double prezzo;
	private int consumo_energetico;
	private String descrizione;
	
	private List<CopiaComponente> listaCopie;
	
	public Componente(int id, String nome, double prezzo, int consumo_energetico, String descrizione) {
		this.setId(id);
		this.setNome(nome);
		this.setPrezzo(prezzo);
		this.setConsumo_energetico(consumo_energetico);
		this.setDescrizione(descrizione);
		
		this.setListaCopie(new LinkedList<CopiaComponente>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getConsumo_energetico() {
		return consumo_energetico;
	}

	public void setConsumo_energetico(int consumo_energetico) {
		this.consumo_energetico = consumo_energetico;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<CopiaComponente> getListaCopie() {
		return listaCopie;
	}

	public void setListaCopie(List<CopiaComponente> listaCopie) {
		this.listaCopie = listaCopie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
