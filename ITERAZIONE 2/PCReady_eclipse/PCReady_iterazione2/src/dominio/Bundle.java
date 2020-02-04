package dominio;

public class Bundle extends Configurazione{

	private double sconto;
	

	//Costruttori 
	
	public Bundle() {
		super();
		super.setCategoria("Configurazione");
		this.sconto = 0.00;
	}
	
	
	
	//  Getters e Setters
	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}
	
	
	
	//  Funzioni di progetto
	
	public void infoBundle(String nome, String descrizione, double sconto) {
		super.setDescrizione(descrizione);
		super.setNome(nome);
		this.setSconto(sconto);
	}
}
