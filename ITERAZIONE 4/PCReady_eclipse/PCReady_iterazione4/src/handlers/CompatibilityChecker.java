package handlers;

import java.util.*;

import dominio.*;
import dominio.componenti.*;

public class CompatibilityChecker {
	
	private Configurazione conf;
	
	public CompatibilityChecker(Configurazione conf) {
		this.setConf(conf);
	}

	
	// FUNZIONI di PROGETTO
	
	public Componente trovaAlternativa(Map<Integer, Componente> mappaRicerca){
		//Questa funzione ha lo scopo di trovare il primo componente compatibile per la sostituzione
		for (Integer key : mappaRicerca.keySet()) {
			if (controlloComponente(mappaRicerca.get(key))){
				return mappaRicerca.get(key);
			}
		}
		return null;
	}
	
	public Componente trovaAlimentatore(Map<Integer, Componente> mappaAlimentatori) {
		//Per prima cosa calcolo il consumo della nostra configurazione
		int consumoEnergetico = 0;
		Componente alimentatore = null;
		for(Componente comp : this.getConf().getListaComponenti()) {
			String cat = comp.getCategoria();
			if(cat=="PSU") {
				consumoEnergetico += 0; //É un PSU e non consuma...
				alimentatore = comp; 
			}else consumoEnergetico += comp.getConsumo_energetico();
		}
		this.getConf().rimuoviComponenteInConfigurazione(alimentatore);//Rimuovi il PSU
		//Dunque trovo il primo alimentatore compatibile per la sostituzione
		for (Integer key : mappaAlimentatori.keySet()) {
			PSU psu = (PSU) mappaAlimentatori.get(key);
			if (psu.getPotenzaErogata() > consumoEnergetico) {
				if (controlloComponente(psu, this.getConf().getListaComponenti())){ //Il form factor deve essere compatibile
					return psu;
				}
			}
		}
		return null;
	}

	
	public boolean controlloComponente(Componente comp) {
		List<Componente> tempList = this.getConf().getListaComponenti();
		try {
			switch(comp.getCategoria()) {
				case "GPU":
					GPU gpu = (GPU) comp;
					return controlloComponente(gpu, tempList);
				case "CPU":
					CPU cpu = (CPU) comp;
					return controlloComponente(cpu, tempList);
				case "PSU":
					PSU psu = (PSU) comp;
					return controlloComponente(psu, tempList);
				case "Storage":
					Storage storage = (Storage) comp;
					return controlloComponente(storage, tempList);
				case "RAM":
					RAM ram = (RAM) comp;
					return controlloComponente(ram, tempList);
				case "Motherboard":
					Motherboard mboard = (Motherboard) comp;
					return controlloComponente(mboard, tempList);
				case "Case":
					Case caseComp = (Case) comp;
					return controlloComponente(caseComp, tempList);
				default:
					return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String controllaPresenzaComponenti() {
		HashMap<String, Integer> presenze = new HashMap<String, Integer>();
		String[] categorie = {"CPU", "GPU", "RAM", "Case", "Motherboard", "Storage", "PSU"};
		List<Componente> list = this.getConf().getListaComponenti();
		
		for(Componente comp : list) {
			String cat = comp.getCategoria();
			if(presenze.get(cat) != null) presenze.put(cat, presenze.get(cat)+1);
			else presenze.put(cat, 1);
		}
		
		for(String key : categorie) {
			/*Non devono esserci zero componenti, ma almeno uno di ogni tipo.
			I controlli specifici impediscono l'inserimento oltre il limite numerico 
			superiore di componenti di data categoria: controllo "maggiore di" non necessario */
			if(presenze.get(key) == null) return key;
		}
		return null;
	}
	
	
	public boolean controllaConsumoEnergetico() {
		int consumoLibero = 0;
		List<Componente> list = this.getConf().getListaComponenti();
		
		for(Componente comp : list) {
			String cat = comp.getCategoria();
			if(cat=="PSU") {
				PSU p = (PSU) comp;
				consumoLibero += p.getPotenzaErogata();
			}else consumoLibero -= comp.getConsumo_energetico();
		}
		
		return (consumoLibero >= 0);
	}
	
	// OVERRIDE CONTROLLO_COMPONENTE
	
	public boolean controlloComponente(GPU gpu, List<Componente> list) {
		int availableSlots = 0;
		boolean thereIsAnyCase = false;
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "Case":
					thereIsAnyCase = true;
					Case c = (Case) comp;
					availableSlots += c.getSlot();
					break;
				case "GPU":
					GPU g = (GPU) comp;
					availableSlots -= g.getSlot();
					break;
			}
		}
		if (thereIsAnyCase == true) {
			return (availableSlots >= gpu.getSlot());
		}
		else return true;
		
	}
	
	public boolean controlloComponente(CPU cpu, List<Componente> list) {
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "Motherboard":
					Motherboard m = (Motherboard) comp;
					if(!m.getSocket().equals(cpu.getSocket())) return false;
					break;
				case "CPU":
					return false;
			}
		}
		return true;
	}
	
	public boolean controlloComponente(PSU psu, List<Componente> list) {
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "Case":
					Case c = (Case) comp;
					if(!c.getFormFactorPSU().equals(psu.getFormFactor())) return false;
					break;
				case "PSU":
					return false;
			}
		}
		return true;
	}
	
	public boolean controlloComponente(Storage storage, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(RAM ram, List<Componente> list) {
		for(Componente comp : list) {
			if(comp.getCategoria()=="Motherboard") {
				Motherboard m = (Motherboard) comp;
				if(!m.getTipologiaRAM().equals(ram.getTipologia())) return false;
			}
		}
		return true;
	}
	
	public boolean controlloComponente(Motherboard mboard, List<Componente> list) {
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "CPU":
					CPU c = (CPU) comp;
					if(!c.getSocket().equals(mboard.getSocket())) return false;
					break;
				case "RAM":
					RAM r = (RAM) comp;
					if(!r.getTipologia().equals(mboard.getTipologiaRAM())) return false;
					break;
				case "Case":
					Case ca = (Case) comp;
					if(!ca.getFormFactorMotherboard().equals(mboard.getFormFactor())) return false;
					break;
				case "Motherboard":  //Aggiunto
					return false;
			}
		}
		return true;
	}
	
	public boolean controlloComponente(Case caseComp, List<Componente> list) {
		int availableSlots = caseComp.getSlot();
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "Motherboard":
					Motherboard m = (Motherboard) comp;
					if(!caseComp.getFormFactorMotherboard().equals(m.getFormFactor())) return false;
					break;
				case "PSU":
					PSU p = (PSU) comp;
					if(!caseComp.getFormFactorPSU().equals(p.getFormFactor())) return false;
					break;
				case "GPU":
					GPU g = (GPU) comp;
					availableSlots -= g.getSlot();
					if(availableSlots < 0) return false;
					break;
				case "Case" :  //Aggiunto
					return false;
			}
		}
		return true;
	}
	
	// GETTER & SETTER
	public Configurazione getConf() {
		return conf;
	}

	public void setConf(Configurazione conf) {
		this.conf = conf;
	}
	
	
	
}
