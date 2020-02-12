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
    private Map<Integer, List<Ordine>> mappaOrdini;

    
    
    /** COSTRUTTORI e FUNZ. SINGLETON 
    
     * Costruttore singleton protected. Si potra ottenere l'unica istanza singleton
     * usando la funzione "getIstance()"  **/
    
    protected PCReady() {
    	this.mappaClienti = null;   //Gabriele prendila dal parser
    	this.mappaAmministratori = null; //Gabriele prendila dal parser
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

	public void setHandlerComponenti() {
		this.handlerComponenti = GestisciComponentiHandler.getInstance();
	}
	
	public void setHandlerAcquisto() {
		this.handlerAcquisto = new AcquistoHandler(getHandlerComponenti().getCatalogo(), this.clienteCorrente);
	}
    



    public String richiediRegistrazione(String nome,String cognome,String email, String password, String confermaPassword) {
    	Cliente clienteAttuale = null;
    	
    	try {
    		clienteAttuale = mappaClienti.get(email);
		}catch(Exception e) {
			e.printStackTrace();
		}
    	
    	if(clienteAttuale == null){
    		if(password.equals(confermaPassword)){
    			Cliente cliente = new Cliente(nome,cognome,email,password);
    			mappaClienti.put(cliente.getEmail(),cliente);
    			return "Cliente:"+cliente.getNome()+" creato con successo";
    		}else return "Le password non coincidono";
    	}
    	else return "Email gia utilizzata";
    }
    
    public String effettuaLogin(String tipologia,String email,String password){
    	switch(tipologia){
    		case "Amministratore":
    			
    	    	Amministratore amministratoreAttuale = null;
    	    	
    	    	try {
    	    		amministratoreAttuale = mappaAmministratori.get(email);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    	    	
    	    	if(amministratoreAttuale != null){
    	    		if(amministratoreAttuale.getPassword().equals(password)) {
        				this.amministratore = amministratoreAttuale;
        				return "L'amministratore "+amministratoreAttuale.getNome()+" ha effettuato il login"; 
        			}
    	    	}
    	    	else return "Email gia registrata";
    			
    		case "Cliente":
    			
    			Cliente clienteAttuale = null;
    	    	
    	    	try {
    	    		clienteAttuale = mappaClienti.get(email);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    	    	
    	    	if(clienteAttuale != null){
    	    		if(clienteAttuale.getPassword().equals(password)) {
        				this.cliente = clienteAttuale;
        				return "Il cliente "+clienteAttuale.getNome()+" ha effettuato il login"; 
        			}
    	    	}
    	    	else return "Email gia registrata";
    	}
		return "Email gia registrata";
    }	
	
	//Funzioni di progetto
	public void salvaOrdine(Ordine ordine, int idCliente) {
		boolean clienteInMappaOrdini = false;
		try {
			 if (!this.mappaOrdini.containsKey(idCliente)) {
				 clienteInMappaOrdini = true;
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		List<Ordine> nuovaLista = new ArrayList<Ordine>();
		nuovaLista.add(ordine);
		if (clienteInMappaOrdini) {
			for (Ordine elemento : this.mappaOrdini.get(idCliente)) {
				nuovaLista.add(elemento);
			}
			this.mappaOrdini.put(idCliente, nuovaLista);
		}
		else this.mappaOrdini.put(idCliente, nuovaLista);
	}
}
    

