package data;

import org.json.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import dominio.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public class Parser {
	
	private String dataFile;
	private String jsonText;
	private JSONObject object;

	
	public Parser(String filename) {
		this.dataFile = filename;
		String content;
		try {
			content = new String ( Files.readAllBytes( Paths.get(filename) ) );
		}
		catch(IOException e) {
			content = new String("");
			e.printStackTrace();
		}
		this.setJsonText(content);
		
		this.setObject(new JSONObject(content));
	}
	
	
	
	public void initialParsing() {
		PCReady sistema = PCReady.getIstance();
		
		JSONArray categorie = this.getObject().getJSONArray("categorie");
		for(int i = 0; i < categorie.length(); i++) {
			JSONObject currCat = categorie.getJSONObject(i);
			Categoria cat = this.processCategoria(categorie.getJSONObject(i));
			JSONArray catComps = currCat.getJSONArray("mComp");
			for(int j = 0; j < catComps.length(); j++) {
				JSONObject currComp = catComps.getJSONObject(j);
				Componente comp = this.processComponente(currComp);
				JSONArray compCopie = currComp.getJSONArray("copie");
				for(int k = 0; k < compCopie.length(); k++) {
					JSONObject currCopia = compCopie.getJSONObject(k);
					CopiaComponente copia = this.processCopia(currCopia);
					comp.aggiungiInListaCopie(copia);
				}
				sistema.aggiungiComponente(comp);
				cat.aggiungiComponente(comp);
			}
			sistema.aggiungiCategoria(cat);
		}
		
		JSONArray configurazioni = this.getObject().getJSONArray("configurazioni");
		for(int l = 0; l < configurazioni.length(); l++) {
			JSONObject currConf = configurazioni.getJSONObject(l);
			Configurazione conf = this.processConfigurazione(currConf);
			JSONArray compIds = currConf.getJSONArray("componenti");
			for(int m = 0; m < compIds.length(); m++) {
				int id = compIds.getInt(m);
				Componente comp = sistema.selezionaComponente(id);
				conf.addComponente(comp);
			}
			sistema.aggiungiConfigurazione(conf);
		}
	}
	
	public static void saveAll(String filename) {
		PCReady sistema = PCReady.getIstance();
		
		JSONObject main = new JSONObject();
		
		JSONArray categorie = new JSONArray();
		Map<Integer, Categoria> mCat = sistema.ottieniMappaCategorie();
		for(Map.Entry<Integer, Categoria> entry : mCat.entrySet()) categorie.put(jsonCategoria(entry.getValue()));
		main.put("categorie", categorie);
		
		JSONArray configurazioni = new JSONArray();
		List<Configurazione> confList = sistema.ottieniListaConfigurazioni();
		for(int i = 0; i < confList.size(); i++) configurazioni.put(jsonConfigurazione(confList.get(i)));
		main.put("configurazioni", configurazioni);
		
		try {
			FileWriter file = new FileWriter(filename);
			file.write(main.toString());
			file.close();
		} catch(Exception e) {
			System.out.println("Errore nel salvare su file: " + filename);
			System.out.println("---------------------------------------------------");
			System.out.println(main.toString());
			e.printStackTrace();
		}
	}
	
// -------------------------------------------------------------------
	
	public Categoria processCategoria(JSONObject cat) {
		int id = cat.getInt("id");
		String nome = cat.getString("nome");
		return new Categoria(id, nome);
	}

	public Componente processComponente(JSONObject comp) {
		int id = comp.getInt("id");
		String nome = comp.getString("nome");
		double prezzo = comp.getDouble("prezzo");
		int consumo_energetico = comp.getInt("consumo_energetico");
		String descrizione = comp.getString("descrizione");
		return new Componente(id, nome, prezzo, consumo_energetico, descrizione);
	}
	
	public CopiaComponente processCopia(JSONObject copia) {
		int codice = copia.getInt("codice");
		return new CopiaComponente(codice);
	}
	
	public Configurazione processConfigurazione(JSONObject conf) {
		int id = conf.getInt("id");
		return new Configurazione(id);
	}
	
// -------------------------------------------------------------------
	
	public static JSONObject jsonCategoria(Categoria cat) {
		JSONObject core = new JSONObject();
		core.put("id", cat.getId());
		core.put("nome", cat.getNome());
		JSONArray componenti = new JSONArray();
		Map<Integer, Componente> mComp = cat.getMappaComponenti();
		for(Map.Entry<Integer, Componente> entry : mComp.entrySet()) componenti.put(jsonComponente(entry.getValue()));
		core.put("mComp", componenti);
		return core;
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
		List<Componente> compList = c.getComponenti();
		for(int i = 0; i < compList.size(); i++) componenti.put(compList.get(i).getId());
		core.put("componenti", componenti);
		
		return core;
	}

// -------------------------------------------------------------------

	public String getJsonText() {
		return jsonText;
	}



	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}



	public JSONObject getObject() {
		return object;
	}



	public void setObject(JSONObject object) {
		this.object = object;
	}
	
}
