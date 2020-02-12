package dominio.componenti;

public class Bundle extends Configurazione{

	private double sconto;
	

	//Costruttori 
	
	public Bundle(String nome, double prezzo, int consumo_energetico, String descrizione) {
		super(nome, prezzo, consumo_energetico, descrizione);
		super.setCategoria("Bundle");
		this.sconto = 0.00;
	}
	
	
	public Bundle(Configurazione conf, double sconto) {
		super(conf.getNome(), conf.getPrezzo(), conf.getConsumo_energetico(), conf.getDescrizione(), conf.getListaComponenti());
		super.setCategoria("Bundle");
		
		double prezzoScontato = conf.getPrezzo() * (1 - sconto/100);
		
		this.setPrezzo(prezzoScontato);
		this.setSconto(sconto);
	}
	
	
	public Bundle() {
		super();
		super.setCategoria("Bundle");
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
	
//  toString
	public String toString() {
		String str = super.toString();
		str += "SCONTO "+getSconto();
		return str;
	}
}
