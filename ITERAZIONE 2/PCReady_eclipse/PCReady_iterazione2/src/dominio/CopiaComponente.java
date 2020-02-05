package dominio;

import java.util.concurrent.atomic.AtomicLong;

public class CopiaComponente {
	
	private int codice;
	private static final AtomicLong counter = new AtomicLong(0);
	
	public CopiaComponente(int codice) {
		this.setCodice(codice);
	}
	
	public CopiaComponente() {
		this.setCodice();
	}
	
	public int getCodice() {
		return this.codice;
	}
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	public void setCodice() {
		this.codice = (int) counter.incrementAndGet();
	}
}
