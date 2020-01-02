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
}
