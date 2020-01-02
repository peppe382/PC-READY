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
	private double prezzo;
	private int consumo_energetico;
	private String descrizione;
	
	private List<CopiaComponente> listaCopie;

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
}
