package dominio.componenti;

public class Bundle extends Configurazione{

	private double sconto;
	
	//------------------------------------------------------------------------------------------
	// COSTRUTTORI
	
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
	
	//------------------------------------------------------------------------------------------
	// GETTERS e SETTERS
	
	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}
	
	public String toString() {
		String str = super.toString();
		str += "SCONTO "+getSconto();
		return str;
	}
	
	//------------------------------------------------------------------------------------------
	// FUNZIONI di PROGETTO
	
	// Setta le info aggiuntive rispetto alla Configurazione
	public void infoBundle(String nome, String descrizione, double sconto) {
		super.setDescrizione(descrizione);
		super.setNome(nome);
		this.setSconto(sconto);
	}
}
