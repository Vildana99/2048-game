package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.DrawUtils;
import game.Game;

/**
 * Panel za glavni meni igre "2048".
 * Sadrži dugmad za početak igre, prikaz najboljih rezultata i izlaz iz igre.
 */
public class MenuPanel extends GuiPanel{
	
	private Font fontTitle=Game.main.deriveFont(100f);	
	private String title="2048";
	
	private int widthButton=220;
	private int heightButton=60;
	
	private int spacing=90;
	
	/**
	 * Konstruktor klase MenuPanel.
	 * Postavlja dugmad za početak igre, prikaz najboljih rezultata i izlaz iz igre.
	 */
	public MenuPanel() {
		super();
		GuiButton buttonPlay=new GuiButton(Game.WIDTH/2-widthButton/2,220,widthButton, heightButton);
		GuiButton buttonScore=new GuiButton(Game.WIDTH/2-widthButton/2,buttonPlay.getY()+spacing,widthButton, heightButton);
		GuiButton buttonQuit=new GuiButton(Game.WIDTH/2-widthButton/2,buttonScore.getY()+spacing,widthButton, heightButton);
		
		buttonPlay.setText("Počni igru");
		buttonScore.setText("Najbolji rezultati");
		buttonQuit.setText("Napusti igru");
		
		buttonPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Play");
				
			}
			
		});
		buttonScore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Leaderboard");
				
			}
			
		});
		
		buttonQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		
		add(buttonPlay);
		add(buttonScore);
		add(buttonQuit);
	}
	
	/**
	 * Metoda za crtanje sadržaja panela.
	 */
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.setFont(fontTitle);
		g.setColor(Color.black);
		g.drawString(title, Game.WIDTH/2-DrawUtils.getMessageWidth(title, fontTitle, g)/2, 150);
	
	}
}

