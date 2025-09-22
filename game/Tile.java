package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	
	/**
	 * Konstante koje se koriste za kreiranje/korištenje pločice.
	 */
	public static final int WIDTH=100;
	public static final int HEIGHT=100;
	public static final int SLIDE_SPEED=30;
	public static final int ARC_WIDTH=15;
	public static final int ARC_HEIGHT=15;
	
	/**
	 * Varijabla koja čuva vrijednost pločice.
	 */
	private int value;
	/**
	 * Varijabla slika pločice koja je tipa BufferedImage.
	 */
	private BufferedImage tileImage;
	/**
	 * Pozadinska boja pločice.
	 */
	private Color background;
	/**
	 * Boja teksta (vrijednost) pločice. 
	 */
	private Color text;
	/**
	 * Font teksta na pločici.
	 */
	private Font font;
	/**
	 * Pozicija na koju će se pločica pomjeriti.
	 */
	private Point slideTo;
	/**
	 * X koordinata pločice.
	 */
	private int x;
	/**
	 * Y koordinata pločice
	 */
	private int y;
	
	/**
	 * Varijabla koja označava može li se pločica spojiti s drugom.
	 */
	private boolean canCombine=true;
	
	
	/**
	 *Konstruktor za pločicu koji prima vrijednost pločice, i koordinate na igračkoj ploči gdje će se nalaziti data pločica.
	 *U konstruktoru se na kraju poziva privatna metoda drawImage koja iscrtava datu pločicu na igračkoj ploči.
	 */
	public Tile(int value, int x, int y) {
		this.value=value;
		this.x=x;
		this.y=y;
		slideTo=new Point(x,y);
		tileImage=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		drawImage();
	}
	
	
	/**
	 * Metoda za crtanje pločica na igračkoj ploči
	 */
	private void drawImage() {

		Graphics2D g=(Graphics2D)tileImage.getGraphics();

		if(value==2) {
			background=new Color(0xe9e9e9);
			text=new Color(0x000000);
		}
		else if(value==4) {
			background=new Color(0xe6daab);
			text=new Color(0x000000);
		}
		else if(value==8) {
			background=new Color(0xf79d3d);
			text=new Color(0xffffff);
		}
		else if(value==16) {
			background=new Color(0xf28007);
			text=new Color(0xffffff);
		}
		else if(value==32) {
			background=new Color(0xf55e3b);
			text=new Color(0xffffff);
		}
		else if(value==64) {
			background=new Color(0xff0000);
			text=new Color(0xffffff);
		}
		else if(value==128) {
			background=new Color(0xe9de84);
			text=new Color(0xffffff);
		}
		else if(value==256) {
			background=new Color(0xf6e873);
			text=new Color(0xffffff);
		}
		else if(value==512) {
			background=new Color(0xf5e455);
			text=new Color(0xffffff);
		}
		else if(value==1024) {
			background=new Color(0xf7e12c);
			text=new Color(0xffffff);
		}
		else if(value==2048) {
			background=new Color(0xffe400);
			text=new Color(0xffffff);
		}
		else {
			background=Color.black;
			text=Color.white;
		}
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		g.setColor(background);
		g.fillRoundRect(0,0,WIDTH,HEIGHT,ARC_WIDTH,ARC_HEIGHT);
		
		g.setColor(text);
		
		if(value<=64) {
			font=Game.main.deriveFont(36f);
		}
		else {
			font=Game.main;
		}
		g.setFont(font);
		
		int drawX=WIDTH/2-DrawUtils.getMessageWidth(""+value, font, g)/2;
		int drawY=HEIGHT/2+DrawUtils.getMessageHeight(""+value, font, g)/2;
		g.drawString(""+value,drawX,drawY);
		g.dispose();
	}
	
	public void render(Graphics2D g) {
		g.drawImage(tileImage,x,y,null);
	}
	
	/**
	 * Geter za vrijednost pločice.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Seter za vrijednost pločice.
	 * Ponovo crta sliku nakon postavljanja nove vrijednosti.
	 */
	public void setValue(int value) {
		this.value=value;
		drawImage();
	}
	
	/**
	 * Metoda za canCombine koja provjerava da li se pločica može spojiti ili ne.
	 */
	public boolean canCombine() {
		return canCombine;
	}
	
	/**
	 * Seter za canCombine.
	 */
	public void setCanCombine(boolean canCombine) {
		this.canCombine = canCombine;
	}
	
	/**
	 * Geter za slideTo.
	 */
	public Point getSlideTo() {
		return slideTo;
	}
	
	/**
	 * Seter za slideTo.
	 */
	public void setSlideTo(Point slideTo) {
		this.slideTo = slideTo;
	}
	
	/**
	 * Geter za x koordinatu.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Seter za x koordinatu.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Geter za y koordinatu.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Seter za y koordinatu.
	 */
	public void setY(int y) {
		this.y = y;
	}


	public void update() {
		
	}
}
