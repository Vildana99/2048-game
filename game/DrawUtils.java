package game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/**
 * Pomoćna klasa koja omogućuje dobivanje dimenzija teksta, korisno za pozicioniranje teksta.
 */
public class DrawUtils { 
	
	/**
	 * Konstruktor bez parametara koji služi za pristup statičkim metodama, a ne za stvaranje instanci ove klase.
	 */
	private DrawUtils() {}	

	/**
	 * Metoda koja vraća širinu poruke kada se iscrta koristeći dati font i grafički objekt g.
	 */
	public static int getMessageWidth(String message, Font font, Graphics2D g) { 
		g.setFont(font);
		Rectangle2D bounds=g.getFontMetrics().getStringBounds(message, g);
		return(int)bounds.getWidth();
	}
	
	/**
	 * Metoda koja vraća visinu poruke kada se iscrta koristeći dati font i grafički objekt g.
	 */
	public static int getMessageHeight(String message, Font font, Graphics2D g) {
		g.setFont(font);
		if(message.length()==0) return 0;
		TextLayout tl=new TextLayout(message,font,g.getFontRenderContext());
		return (int)tl.getBounds().getHeight();
	}	
}
