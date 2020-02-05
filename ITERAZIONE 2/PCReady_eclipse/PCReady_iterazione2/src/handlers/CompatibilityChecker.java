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
	
	// OVERRIDE CONTROLLO_COMPONENTE
	
	public boolean controlloComponente(GPU gpu, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(CPU cpu, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(PSU psu, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(Storage storage, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(RAM ram, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(Motherboard mboard, List<Componente> list) {
		return true;
	}
	
	public boolean controlloComponente(Case caseComp, List<Componente> list) {
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
