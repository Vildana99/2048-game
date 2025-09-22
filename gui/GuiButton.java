package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import game.DrawUtils;
import game.Game;

public class GuiButton {
	
	private State currentState=State.RELEASED;
	private Rectangle box;
	private ArrayList<ActionListener> actionListeners;
	private String text="";
	
	private Color released;
	private Color hover;
	private Color pressed;
	
	private Font font=Game.main.deriveFont(22f);
	
	/**
	 * Konstruktor za GuiButton klasu.
	 */
	public GuiButton(int x, int y, int width, int height) {
		box=new Rectangle(x,y,width,height);
		actionListeners=new ArrayList<ActionListener>();
		released=new Color(234,209,150);
		hover=new Color(191,49,49);
		pressed=new Color(125,10,10);
	}
	
	public void update() {
		
	}
	
	/**
	 * Metoda koja se koristi za crtanje dugmeta.
	 */
	public void render(Graphics2D g) {
		if(currentState==State.RELEASED) {
			g.setColor(released);
			g.fill(box);
		}
		else if(currentState==State.PRESSED) {
			g.setColor(pressed);
			g.fill(box);
		}
		else{
			g.setColor(hover);
			g.fill(box);
		}
		g.setColor(new Color(243,237,200));
		g.setFont(font);
		//top left x position, bottom y pos.
		g.drawString(text, box.x+box.width/2-DrawUtils.getMessageWidth(text, font, g)/2, 
				box.y+box.height/2+DrawUtils.getMessageHeight(text, font, g)/2);
	}
	

	/**
	 * Ova metoda dodaje ActionListener objekt u listu actionListeners.
	 */
	public void addActionListener(ActionListener listener){
		actionListeners.add(listener);
	}
	
	/**
	 * Metoda koja se koristi kada je korisnik kliknuo tipku miša, te se stanje postavlja na PRESSED.
	 */
	public void mousePressed(MouseEvent e) {
		if(box.contains(e.getPoint())) {
			currentState=State.PRESSED;
		}
	}
	
	/**
	 * Metoda koja se koristi kada korisnik otpusti tipku miša.
	 */
	public void mouseReleased(MouseEvent e) {	
		if(box.contains(e.getPoint())) {
			for(ActionListener a: actionListeners) {
				a.actionPerformed(null);
			}
		}
		currentState=State.RELEASED;
	}
	
	/**
	 * Metoda za praćenje događaja kada se miš povlači dok se tipka miša drži pritisnutom.
	 */
	public void mouseDragged(MouseEvent e) {
		if(box.contains(e.getPoint())) {
			currentState=State.PRESSED;
		}
		else {
			currentState=State.RELEASED;
		}
	}
	
	/**
	 * Metoda koja se poziva kada je miš pomaknut.
	 * Ako se nalazi unutar box-a tj. dugmeta, postavlja se trenutno stanje na hover, inače je released.
	 */
	public void mouseMoved(MouseEvent e) {
		if(box.contains(e.getPoint())) {
			currentState=State.HOVER;
		}
		else {
			currentState=State.RELEASED;
		}
	}
	
	public int getX() {
		return box.x;
	}
	
	public int getY() {
		return box.y;
	}
	
	public int getWidth() {
		return box.width;
	}
	
	public int getHeight() {
		return box.height;
	}
	
	public void setText(String text) {
		this.text=text;
	}
	
	
	/**
	 * Metoda za definiranje 3 moguća stanja za miš (element pušten, pokazivač miša iznad elementa, element pritisnut).
	 */
	private enum State{
		RELEASED,
		HOVER,
		PRESSED
	}

}

