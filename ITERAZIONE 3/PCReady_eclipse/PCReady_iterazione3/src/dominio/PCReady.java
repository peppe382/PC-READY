package dominio;

import java.util.*;

import handlers.*;

public class PCReady {


    private static PCReady singleton;
    
    private String versione = "0.1";
    private Amministratore amministratore;
    private Cliente cliente;
    private Map<String, Cliente> mappaClienti;
    private Map<String, Amministratore> mappaAmministratori;
    private ConfigurationHandler handlerConfigurazioni;
    private GestisciComponentiHandler handlerComponenti;
    private AcquistoHandler handlerAcquisto;
	private Cliente clienteCorrente;
    private Map<String, List<Ordine>> mappaOrdini;

    
    
    /** COSTRUTTORI e FUNZ. SINGLETON 
    
     * Costruttore singleton protected. Si potra ottenere l'unica istanza singleton
     * usando la funzione "getIstance()"  **/
    
    protected PCReady() {
    	this.mappaClienti = new HashMap<String, Cliente>();   //Gabriele prendila dal parser
    	this.mappaAmministratori = new HashMap<String, Amministratore>();; //Gabriele prendila dal parser
    	this.mappaOrdini = null; //Gabriele prendila dal parser
        this.handlerComponenti = GestisciComponentiHandler.getInstance();
        this.handlerConfigurazioni = null;
        this.handlerAcquisto = null;
    }
    
    public static synchronized PCReady getInstance() {
        if(singleton == null) {
            singleton = new PCReady();
        }
        return singleton;
    }
    
    
    /**
     * Arresta il Sistema dopo aver salvato i dati permanenti su database
     **/
     
    public void spegniSistema() {
        this.getHandlerComponenti().salvaCatalogo();
    }

    
    /********** GETTERS E SETTERS  **********/
    
	
    public ConfigurationHandler getHandlerConfigurazioni() {
        return this.handlerConfigurazioni;
    }

    public GestisciComponentiHandler getHandlerComponenti() {
        return this.handlerComponenti;
    }



    public void setHandlerConfigurazioni() {
        this.handlerConfigurazioni = new ConfigurationHandler(getHandlerComponenti().getCatalogo());
    }
    
    
    
    public void setHandlerConfigurazioni(String comando_bundle) {
        if (comando_bundle.equals("Bundle")){        //In futuro tale if controller� se un admin loggato sta chiamando il comando...
            this.handlerConfigurazioni = new ConfigurationHandler(getHandlerComponenti().getCatalogo(), comando_bundle);
        }
    }

    public void setMappaAmministratori(Map<String, Amministratore> mappaAmministratori) {
		this.mappaAmministratori = mappaAmministratori;
	}
    
    public void setMappaClienti(Map<String, Cliente> mappaClienti) {
		this.mappaClienti = mappaClienti;
	}
    
    public Map<String, Cliente> getMappaClienti() {
		return mappaClienti;
	}
    
    public Map<String, Amministratore> getMappaAmministratori() {
		return mappaAmministratori;
	}
    
	public void setHandlerComponenti() {
		this.handlerComponenti = GestisciComponentiHandler.getInstance();
	}
	
	public void setHandlerAcquisto() {
		this.handlerAcquisto = new AcquistoHandler(getHandlerComponenti().getCatalogo(), this.clienteCorrente);
	}
    



    public String richiediRegistrazione(String nome,String cognome,String email, String password, String confermaPassword) {
    	boolean clienteAttuale = false;
    	
    	try {
    		clienteAttuale = this.mappaClienti.containsKey(email);
		}catch(Exception e) {
			e.printStackTrace();
		}
    		
    	if(!clienteAttuale){
    		if(password.equals(confermaPassword)){
    			Cliente cliente = new Cliente(nome,cognome,email,password);
    			this.mappaClienti.put(cliente.getEmail(), cliente);
    			return "Cliente: "+cliente.getNome()+" "+ cliente.getCognome()+" creato con successo";
    		}else return "Le password non coincidono";
    	}
    	else return "Email gia utilizzata";
    }
    
    
    
    
    
    public String effettuaLogin(String tipologia,String email,String password){
    	switch(tipologia){
    		case "Amministratore":
    			
    	    	boolean amministratoreAttuale = false;
    	    	
    	    	try {
    	    		amministratoreAttuale = this.mappaAmministratori.containsKey(email);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    	    	
    	    	if(amministratoreAttuale){
    	    		if(mappaAmministratori.get(email).getPassword().equals(password)) {
        				this.amministratore = mappaAmministratori.get(email);
        				return "L'amministratore "+amministratore.getNome()+" ha effettuato il login"; 
        			}
    	    	}
    	    	else return "Email non registrata";
    			
    		case "Cliente":
    			
    			boolean clienteAttuale = false;
    	    	
    	    	try {
    	    		clienteAttuale = this.mappaClienti.containsKey(email);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    	    	
    	    	if(clienteAttuale){
    	    		if(mappaClienti.get(email).getPassword().equals(password)) {
        				this.cliente = mappaClienti.get(email);
        				return "Il cliente "+cliente.getNome()+" ha effettuato il login"; 
        			}
    	    	}
    	    	else return "Email non registrata";
    	}
		return "Email non registrata";
    }	
	
	//Funzioni di progetto
	public void salvaOrdine(Ordine ordine, String emailCliente) {
		boolean clienteInMappaOrdini = false;
		try {
			 if (!this.mappaOrdini.containsKey(emailCliente)) {
				 clienteInMappaOrdini = true;
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		List<Ordine> nuovaLista = new ArrayList<Ordine>();
		nuovaLista.add(ordine);
		if (clienteInMappaOrdini) {
			for (Ordine elemento : this.mappaOrdini.get(emailCliente)) {
				nuovaLista.add(elemento);
			}
			this.mappaOrdini.put(emailCliente, nuovaLista);
		}
		else this.mappaOrdini.put(emailCliente, nuovaLista);
	}
}
    

