package data;

import org.json.*;

import java.io.*;
import java.nio.file.*;

import dominio.*;

/**
 * @author Bartolomeo Caruso
 * @author Gabriele Costanzo
 * @author Giuseppe Fallica
 *
 * @version 0.1
 */

public class Parser {
	
	private String jsonText;
	private JSONObject object;

	
	public Parser(String filename) {
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
				}
			}
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
