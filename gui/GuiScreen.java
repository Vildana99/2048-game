package gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class GuiScreen {
	private static GuiScreen screen;
	private HashMap<String, GuiPanel> panels;
	private String currentPanel="";
	
	/**
	 * Privatni konstruktor koji inicijalizuje mapu panela.
	 */
	private GuiScreen() {
		panels=new HashMap<String,GuiPanel>();
	}
	
	/**
	 * Metoda koja vraća instancu klase GuiScreen.
	 */
	public static GuiScreen getInstance() {
		if(screen==null) {
			screen=new GuiScreen();
		}
		return screen;
	}
	
	/**
	 * Ažurira trenutni panel ako postoji.
	 */
	public void update() {
		if(panels.get(currentPanel)!=null) {
			panels.get(currentPanel).update();
		}
	}
	
	/**
	 * Iscrtava trenutni panel ako postoji.
	 */
	public void render(Graphics2D g) {
		if(panels.get(currentPanel)!=null) {
			panels.get(currentPanel).render(g);
		}
	}
	
	/**
	 * Dodaje novi panel u mapu panela.
	 */
	public void add(String panelName, GuiPanel panel) {
		panels.put(panelName, panel);
	}
	
	/**
	 * Postavlja trenutni panel na osnovu datog naziva panela.
	 */
	public void setCurrentPanel(String panel) {
		currentPanel=panel;
	}
	
	public void mousePressed(MouseEvent e) {
		if(panels.get(currentPanel)!=null) {
			panels.get(currentPanel).mousePressed(e);
		}	
	}
	
	public void mouseReleased(MouseEvent e) {
		if(panels.get(currentPanel)!=null) {
			panels.get(currentPanel).mouseReleased(e);
		}	
	}
	
	public void mouseDragged(MouseEvent e) {
		if(panels.get(currentPanel)!=null) {
			panels.get(currentPanel).mouseDragged(e);
		}
	}	
	public void mouseMoved(MouseEvent e) {
		if(panels.get(currentPanel)!=null) {
			panels.get(currentPanel).mouseMoved(e);
		}
	}
}
