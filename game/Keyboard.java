package game;

import java.awt.event.KeyEvent;

/**
 *Klasa koja pruža statičke metode za praćenje stanja tastature. 
 */
public class Keyboard {
	
	/**
	 * Statičko polje koje predstavlja trenutno stanje tipke na tastaturi. 
	 * Ako je vrijednost 'true'-tpka pritisnuta, 'false'-tipka nije pritisnuta.
	 */
	public static boolean[] pressed=new boolean[256];
	/**
	 * Statičko polje koje predstavlja prethodno stanje tipke na tastaturi. 
	 */
	public static boolean[] prev=new boolean[256];
	
	/**
	 * Konstruktor bez parametara koji služi za pristup statičkim metodama, a ne za stvaranje instanci ove klase.
	 */
	private Keyboard() {}
	
	/**
	 * Metoda koja služi za ažuriranje stanja polja 'prev' tako da odražava stanje 'pressed' polja iz prethodnog ciklusa.
	 */
	public static void update() {
		for(int i=0;i<4;i++) {
			if(i==0) 
				prev[KeyEvent.VK_LEFT]=pressed[KeyEvent.VK_LEFT];
			if(i==1) 
				prev[KeyEvent.VK_RIGHT]=pressed[KeyEvent.VK_RIGHT];
			if(i==2) 
				prev[KeyEvent.VK_UP]=pressed[KeyEvent.VK_UP];
			if(i==3) 
				prev[KeyEvent.VK_DOWN]=pressed[KeyEvent.VK_DOWN];
		}
	}
	
	
	
	/**
	 * Metoda koja se poziva kada se pritisne tipka. 
	 * Postavlja odgovarajući element u 'pressed' polju na 'true' kako bi označila da je tipka pritisnuta.
	 */
	public static void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()]=true;
	}
		
	/**
	  * Metoda koja se poziva kada se pritisnuta tipka oslobodi. 
	 * Postavlja odgovarajući element u 'pressed' polju na 'false' kako bi označila da je tipka oslobođena tj. da više nije pritisnuta.
	 */
	public static void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()]=false;
	}
	
	/**
	 * Metoda koja služi za provjeru da li je tipka s odgovarajućim keyEevent-om pritisnuta, ali samo ako je bila pritisnuta u prethodnom ciklusu provjere
	 * Osigurava da se akcija izvrši samo jednom po pritisku tipke.
	 */
	public static boolean typed(int keyEvent) {
		return !pressed[keyEvent] && prev[keyEvent];
	}
}

