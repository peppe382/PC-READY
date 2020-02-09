package interfaccia;

public class CreaConfigurazione extends Comando{

	private String nomeConf;
	
	public CreaConfigurazione(Console console, String stringa) {
		super(console);
		this.nomeConf = stringa;
	}

	public void esegui(Console console) {
		console.getSistema().getHandlerConfigurazioni().creaConfigurazione(this.conf);
	}
	
}
