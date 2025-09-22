package gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GuiPanel {
	
	private ArrayList<GuiButton> buttons;
	
	/**
	 * Inicijalizacija GuiPanel-a sa praznom listom dugmadi.
	 */
	public GuiPanel() {
		buttons=new ArrayList<GuiButton>();
	}
	
	/**
	 * Metoda koja ažurira dugmad.
	 */
	public void update() {
		for(GuiButton button: buttons) {
			button.update();
		}
	}
	
	/**
	 * Iscrtava dugmad u panelu.
	 */
	public void render(Graphics2D g) {
		for(GuiButton button: buttons) {
			button.render(g);
		}	
	}
	
	public void add(GuiButton button) {
		buttons.add(button);
	}
	
	public void remove(GuiButton button) {
		buttons.remove(button);
	}
	
	public void mousePressed(MouseEvent e) {
		for(GuiButton button: buttons) {
			button.mousePressed(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		for(GuiButton button: buttons) {
			button.mouseReleased(e);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		for(GuiButton button: buttons) {
			button.mouseDragged(e);
		}
	}	
	public void mouseMoved(MouseEvent e) {
		for(GuiButton button: buttons) {
			button.mouseMoved(e);
		}
	}

}

