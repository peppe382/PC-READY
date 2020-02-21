package dominio;

import java.util.*;

import data.Parser;
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

    //------------------------------------------------------------------------------------------
    // COSTRUTTORI e SINGLETON
    
    /**
     * Costruttore singleton protected. Si potra ottenere l'unica istanza singleton
     * usando la funzione "getIstance()"  **/
    
    protected PCReady() {
    	this.handlerComponenti = GestisciComponentiHandler.getInstance();
    	this.mappaClienti = Parser.caricaClienti();
    	this.mappaAmministratori = Parser.caricaAdmin();
    	this.mappaOrdini = Parser.caricaOrdini(this.mappaClienti, this.handlerComponenti.getCatalogo());
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
        Parser.salvaUtenti(this.mappaClienti, this.mappaAmministratori);
        Parser.salvaOrdini(this.mappaOrdini);
    }

    //------------------------------------------------------------------------------------------
    // GETTERS e SETTERS
	
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
	
	public void setHandlerAcquisto(Cliente cliente) {
		this.handlerAcquisto = new AcquistoHandler(getHandlerComponenti().getCatalogo(), cliente);
	}
	
	public AcquistoHandler getHandlerAcquisto() {
		return this.handlerAcquisto;
	}
	public Amministratore getAmministratore() {
		return amministratore;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public List<Ordine> getListaOrdiniCliente(String emailCliente){
		if (this.mappaOrdini.containsKey(emailCliente)) {
			List<Ordine> listaOrdini = mappaOrdini.get(emailCliente);
			return listaOrdini;
		 }
		else {
			List<Ordine> listaOrdini = new ArrayList<Ordine>();
			return listaOrdini;
		}
	}
	
	//------------------------------------------------------------------------------------------
    // FUNZIONI di PROGETTO

	// Prova a registrare un nuovo Cliente
    public String richiediRegistrazione(String nome,String cognome,String email, String password, String confermaPassword) {
    	boolean clienteAttuale = false;
    	
    	try {
    		if (clienteAttuale = this.mappaClienti.containsKey(email)) {
    			clienteAttuale = true;
    		}
		}catch(Exception e) {
			e.printStackTrace();
		}
    		
    	if(!clienteAttuale){
    		if(password.equals(confermaPassword)){
    			Cliente cliente = new Cliente(nome,cognome,email,password);
    			this.cliente = cliente;
    			this.mappaClienti.put(cliente.getEmail(), cliente);
    			Parser.salvaUtenti(this.mappaClienti, this.mappaAmministratori);
    			return "Nome: "+cliente.getNome()+"\nCognome: "+cliente.getCognome()+"\nE-mail: "+cliente.getEmail();
    		}else return "Le password non coincidono";
    	}
    	else return "Email gia utilizzata";
    }
    
    // Prova a effettuare il login per un Cliente pre-esistente
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
        				return "L'amministratore "+amministratore.getEmail()+" ha effettuato il login"; 
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
        				return "Il cliente "+cliente.getEmail()+" ha effettuato il login"; 
        			}
    	    	}
    	    	else return "Email non registrata";
    	}
		return "Email non registrata";
    }	
	
	// Salva un nuovo Ordine nella mappa di Sistema
	public void salvaOrdine(Ordine ordine, String emailCliente) {
		boolean clienteInMappaOrdini = false;
		try {
			clienteInMappaOrdini = this.mappaOrdini.containsKey(emailCliente);
			List<Ordine> nuovaLista;
			
			if (clienteInMappaOrdini) nuovaLista = new ArrayList<Ordine>(this.mappaOrdini.get(emailCliente));
			else nuovaLista = new ArrayList<Ordine>();
			
			nuovaLista.add(ordine);
			
			this.mappaOrdini.put(emailCliente, nuovaLista);
			Parser.salvaOrdini(this.mappaOrdini);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Modifica un Ordine pre-esistente
	public String modificaOrdine(int id, String indirizzo, String citta, int CAP, String email) {
		
		List<Ordine> listaOrdini = getListaOrdiniCliente(email);
		Ordine ordineCorrente = null;
		
		for(Ordine ordine : listaOrdini) {
			if(ordine.getId() == id){
				ordineCorrente = ordine;
				listaOrdini.remove(ordine);
			} else {
				return "Non sono stati trovati ordini corrispondenti al codice indicato \n";
			}
			ordineCorrente.aggiornaAttributiSpedizione(indirizzo, citta, CAP);
			
			listaOrdini.add(ordineCorrente);
			mappaOrdini.put(email, listaOrdini);
			Parser.salvaOrdini(this.mappaOrdini);
		}
		
		return ordineCorrente.toString();
	}
	
}
    

