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
	
	
	public boolean controllaPresenzaComponenti() {
		HashMap<String, Integer> presenze = new HashMap<String, Integer>();
		List<Componente> list = this.getConf().getListaComponenti();
		
		for(Componente comp : list) {
			String cat = comp.getCategoria();
			if(presenze.get(cat) != null) presenze.put(cat, presenze.get(cat)+1);
			else presenze.put(cat, 1);
		}
		
		for(String key : presenze.keySet()) {
			switch(key) {
				case "CPU":
				case "PSU":
				case "Motherboard":
				case "Case":
					if(presenze.get(key)>1) return false;
				case "GPU":
				case "Storage":
				case "RAM":
					if(presenze.get(key)<1) return false;
				default:
					break;
			}
		}
		return true;
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
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "Case":
					Case c = (Case) comp;
					availableSlots += c.getSlot();
					break;
				case "GPU":
					GPU g = (GPU) comp;
					availableSlots -= g.getSlot();
					break;
			}
		}
		return (availableSlots >= gpu.getSlot());
	}
	
	public boolean controlloComponente(CPU cpu, List<Componente> list) {
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "Motherboard":
					Motherboard m = (Motherboard) comp;
					if(m.getSocket() != cpu.getSocket()) return false;
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
					if(c.getFormFactorPSU() != psu.getFormFactor()) return false;
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
				if(m.getTipologiaRAM()!= ram.getTipologia()) return false;
			}
		}
		return true;
	}
	
	public boolean controlloComponente(Motherboard mboard, List<Componente> list) {
		for(Componente comp : list) {
			switch(comp.getCategoria()) {
				case "CPU":
					CPU c = (CPU) comp;
					if(c.getSocket() != mboard.getSocket()) return false;
					break;
				case "RAM":
					RAM r = (RAM) comp;
					if(r.getTipologia() != mboard.getTipologiaRAM()) return false;
					break;
				case "Case":
					Case ca = (Case) comp;
					if(ca.getFormFactorMotherboard() != mboard.getFormFactor()) return false;
					break;
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
					if(caseComp.getFormFactorMotherboard()!=m.getFormFactor()) return false;
					break;
				case "PSU":
					PSU p = (PSU) comp;
					if(caseComp.getFormFactorPSU()!=p.getFormFactor()) return false;
					break;
				case "GPU":
					GPU g = (GPU) comp;
					availableSlots -= g.getSlot();
					if(availableSlots < 0) return false;
					break;
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
