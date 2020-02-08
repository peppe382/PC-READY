package data;

import org.json.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import dominio.*;
import dominio.componenti.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public class Parser {
	
	public static final String database = "data/catalogo.json";
	
	// Funzioni handler
	public static Catalogo createCatalogo() {
		JSONObject catalogo = Parser.getObjectFromFile(Parser.database);
		return Parser.parseCatalogo(catalogo);
	}
	
	public static void salvaCatalogo(Catalogo catalogo) {
		JSONObject jsonString = new JSONObject();
		for(Map.Entry<String, ArrayList<Componente>> entry: catalogo.getMappaComponenti().entrySet()) {
			String categoria = entry.getKey();
			ArrayList<Componente> list = entry.getValue();
			
			JSONArray tempArray = new JSONArray();
			for(Componente comp : list) {
				JSONObject jsonObj = Parser.generalJson(comp);
				tempArray.put(jsonObj);
			}
			jsonString.put(categoria, tempArray);
		}
		Parser.writeToFile(Parser.database, jsonString.toString());
	}
	
	// -------------------------------------------------------------------
		// Funzioni interne
		
		/**
		 * Caricamento del Catalogo da Database
		 * 
		 */
		public static Catalogo parseCatalogo(JSONObject jsonCatalogo) {
			Map<String, ArrayList<Componente>> catalogo = new HashMap<String, ArrayList<Componente>>();
			
			Iterator<String> keys = jsonCatalogo.keys();
			while(keys.hasNext()) {
				String cat = keys.next();
				if(cat=="Configurazione" || cat=="Bundle") continue;
				ArrayList<Componente> tempList = new ArrayList<Componente>();
				JSONArray compList = jsonCatalogo.getJSONArray(cat);
				for(int i = 0; i < compList.length(); i++) {
					JSONObject comp = compList.getJSONObject(i);
					Componente c = Parser.processComponente(comp, cat);
					Componente temp;
					switch(cat) {
						case "CPU":
							String socket = comp.getString("socket");
							CPU cpu = new CPU(c, socket);
							temp = cpu;
							break;
						case "GPU":
							int slot = comp.getInt("slot");
							GPU gpu = new GPU(c, slot);
							temp = gpu;
							break;
						case "RAM":
							String tipologia = comp.getString("tipologia");
							int frequenza = comp.getInt("frequenza");
							RAM ram = new RAM(c, tipologia, frequenza);
							temp = ram;
							break;
						case "Storage":
							String memoria = comp.getString("memoria");
							double dimensioni = comp.getDouble("dimensioni");
							int velocita = comp.getInt("velocita");
							String tipologiaDisco = comp.getString("tipologia");
							Storage str = new Storage(c, memoria, dimensioni, velocita, tipologiaDisco);
							temp = str;
							break;
						case "PSU":
							int potenzaErogata = comp.getInt("potenzaErogata");
							String tipologiaPSU = comp.getString("tipologia");
							String formFactor = comp.getString("formFactor");
							PSU psu = new PSU(c, potenzaErogata, tipologiaPSU, formFactor);
							temp = psu;
							break;
						case "Motherboard":
					        String socketMboard = comp.getString("socket");
					        String formFactorMboard = comp.getString("formFactor");
					        String tipologiaRAM = comp.getString("tipologiaRAM");
					        Motherboard motherboard = new Motherboard(c,socketMboard,formFactorMboard,tipologiaRAM);
					        temp = motherboard;
					        break;
						case "Case":
						    String formFactorMotherboard = comp.getString("formFactorMotherboard");
						    String formFactorPSU = comp.getString("formFactorPSU");
						    int slotCase = comp.getInt("slot");
						    Case caseComp = new Case(c,formFactorMotherboard,formFactorPSU,slotCase);
						    temp = caseComp;
						    break;
						default:
							temp = c;
					}
					JSONArray listaCopie = comp.getJSONArray("copie");
					for(int num = 0; num < listaCopie.length(); num++) {
						CopiaComponente copia = Parser.processCopia(listaCopie.getJSONObject(num));
						temp.aggiungiCopia(copia);
					}
					tempList.add(temp);
				}
				catalogo.put(cat, tempList);
			}
			
			Catalogo objCatalogo = new Catalogo(catalogo);
			
			// Configurazioni
			JSONArray configurazioni = jsonCatalogo.getJSONArray("Configurazione");
			objCatalogo.aggiungiCategoria("Configurazione");
			for(int j = 0; j < configurazioni.length(); j++) {
				JSONObject config = configurazioni.getJSONObject(j);
				Configurazione conf = Parser.processConfigurazione(config, objCatalogo);
				Componente temp = (Componente) conf;
				objCatalogo.aggiungiInCatalogo(temp);
			}
			
			// Configurazioni
			JSONArray bundle = jsonCatalogo.getJSONArray("Bundle");
			objCatalogo.aggiungiCategoria("Bundle");
			for(int j = 0; j < bundle.length(); j++) {
				JSONObject jsonB = bundle.getJSONObject(j);
				Bundle bund = Parser.processBundle(jsonB, objCatalogo);
				Componente temp = (Componente) bund;
				objCatalogo.aggiungiInCatalogo(temp);
			}
			
			return objCatalogo;
		}
	
	// -------------------------------------------------------------------

		// Lettura singoli Componenti
		public static Componente processComponente(JSONObject comp, String categoria) {
			int id = comp.getInt("id");
			String nome = comp.getString("nome");
			double prezzo = comp.getDouble("prezzo");
			int consumo_energetico = comp.getInt("consumo_energetico");
			String descrizione = comp.getString("descrizione");
			return new Componente(id, nome, prezzo, consumo_energetico, descrizione, categoria);
		}
		
		public static CopiaComponente processCopia(JSONObject copia) {
			int codice = copia.getInt("codice");
			return new CopiaComponente(codice);
		}
		
		public static Configurazione processConfigurazione(JSONObject conf, Catalogo cat) {
			int id = conf.getInt("id");
			Configurazione c = new Configurazione(id);
			JSONArray listaComponenti = conf.getJSONArray("componenti");
			
			double prezzo = 0;
			int consumo = 0;
			
			for(int i = 0; i < listaComponenti.length(); i++) {
				int idComp = listaComponenti.getInt(i);
				Componente comp = cat.getComponente(idComp);
				
				prezzo += comp.getPrezzo();
				consumo += comp.getConsumo_energetico();
				
				c.aggiungiComponenteInConfigurazione(comp);
			}
			return c;
		}
		
		public static Bundle processBundle(JSONObject bund, Catalogo cat) {
			Configurazione c = Parser.processConfigurazione(bund, cat);
			double sconto = bund.getDouble("sconto");	
			
			Bundle b = new Bundle(c, sconto);
			return b;
		}
		
		// -------------------------------------------------------------------
		
		// Scrittura singoli Componenti
		public static JSONObject generalJson(Componente c) {
			String cat = c.getCategoria();
			if(cat=="Configurazione") return jsonConfigurazione((Configurazione)c);
			else if(cat=="Bundle") return jsonBundle((Bundle)c);
			JSONObject tempJson = jsonComponente(c);
			switch(cat) {
				case "CPU":
					CPU cpu = (CPU) c;
					tempJson.put("socket", cpu.getSocket());
					break;
				case "GPU":
					GPU gpu = (GPU) c;
					tempJson.put("slot", gpu.getSlot());
					break;
				case "RAM":
					RAM ram = (RAM) c;
					tempJson.put("tipologia", ram.getTipologia());
					tempJson.put("frequenza", ram.getFrequenza());
					break;
				case "Storage":
					Storage str = (Storage) c;
					tempJson.put("memoria", str.getMemoria());
					tempJson.put("dimensioni", str.getDimensioni());
					tempJson.put("velocita", str.getVelocita());
					tempJson.put("tipologia", str.getTipologia());
					break;
				case "PSU":
					PSU psu = (PSU) c;
					tempJson.put("potenzaErogata", psu.getPotenzaErogata());
					tempJson.put("tipologia", psu.getTipologia());
					tempJson.put("formFactor", psu.getFormFactor());
					break;
				case "Motherboard":
					Motherboard mbrd = (Motherboard) c;
					tempJson.put("socket", mbrd.getSocket());
					tempJson.put("formFactor", mbrd.getFormFactor());
					tempJson.put("tipologiaRAM", mbrd.getTipologiaRAM());
					break;
				case "Case":
					Case cs = (Case) c;
					tempJson.put("slot", cs.getSlot());
					tempJson.put("formatFactorPSU", cs.getFormFactorPSU());
					tempJson.put("formatFactorMotherboard", cs.getFormFactorMotherboard());
					break;
				default:
					break;
			}
			return tempJson;
		}
		
		public static JSONObject jsonComponente(Componente c) {
			JSONObject core = new JSONObject();
			core.put("id", c.getId());
			core.put("nome", c.getNome());
			core.put("prezzo", c.getPrezzo());
			core.put("consumo_energetico", c.getConsumo_energetico());
			core.put("descrizione", c.getDescrizione());
			JSONArray copie = new JSONArray();
			List<CopiaComponente> copyList = c.getListaCopie();
			for(int i = 0; i < copyList.size(); i++) copie.put(jsonCopia(copyList.get(i)));
			core.put("copie", copie);
			return core;
		}
				
		public static JSONObject jsonCopia(CopiaComponente c) {
			JSONObject core = new JSONObject();
			core.put("codice", c.getCodice());
			return core;
		}
				
		public static JSONObject jsonConfigurazione(Configurazione c) {
			JSONObject core = new JSONObject();
			core.put("id", c.getId());
			JSONArray componenti = new JSONArray();
			List<Componente> compList = c.getListaComponenti();
			for(int i = 0; i < compList.size(); i++) componenti.put(compList.get(i).getId());
			core.put("componenti", componenti);
					
			return core;
		}
		
		public static JSONObject jsonBundle(Bundle b) {
			JSONObject core = jsonConfigurazione((Configurazione)b);
			core.put("sconto", b.getSconto());
			return core;
		}
		
	// -------------------------------------------------------------------
			
		// Utility scrittura su file
			public static String getFileContent(String filename) {
				String content;
				try {
					content = new String ( Files.readAllBytes( Paths.get(filename) ) );
				}
				catch(IOException e) {
					content = new String("");
					e.printStackTrace();
				}
				return content;
			}
			
			public static JSONArray getArrayFromFile(String filename) {
				return new JSONArray(getFileContent(filename));
			}
			
			public static JSONObject getObjectFromFile(String filename) {
				return new JSONObject(getFileContent(filename));
			}
			
			public static void writeToFile(String filename, String content) {
				try {
					FileWriter file = new FileWriter(filename);
					file.write(content);
					file.close();
				} catch(Exception e) {
					System.out.println("Errore nel salvare su file: " + filename);
					System.out.println("---------------------------------------------------");
					System.out.println(content);
					e.printStackTrace();
				}
			}
			
}