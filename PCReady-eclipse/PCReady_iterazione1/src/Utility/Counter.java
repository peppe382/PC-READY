package Utility;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
	//Questa classe mi consente di ottenere degli identificativi interi a 64 bit unici.
	
    private static final AtomicLong counter = new AtomicLong(0);

    public static long getNextNumber(){
        return counter.incrementAndGet();
    }
}
