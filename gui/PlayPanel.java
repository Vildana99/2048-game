package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import game.DrawUtils;
import game.Game;
import game.GameBoard;
import game.Score;

/**
 * Panel za prikaz igre "2048".
 * Sadrži logiku za crtanje igre, informacija o rezultatu i dugmad za ponovno pokretanje igre i povratak na početni meni.
 */
public class PlayPanel extends GuiPanel{
	
	private GameBoard board;
	
	private BufferedImage info;
	private Score scores;
	private Font scoreFont;
	
	private GuiButton tryAgain;
	private GuiButton mainMenu;
	
	private int buttonWidth=160;
	private int spacing=20;
	private int buttonHeight=50;
	private boolean added;
	private int alpha=0;
	private Font gameOverFont;
	
	/**
	 * Konstruktor klase PlayPanel.
	 * Postavlja ploču za igru, dugmad za ponovno pokretanje igre i povratak na početni meni.
	 */
	public PlayPanel() {
		scoreFont=Game.main.deriveFont(24f);
		gameOverFont=Game.main.deriveFont(70f);
		
		board=new GameBoard(Game.WIDTH/2-GameBoard.BOARD_WIDTH/2,Game.HEIGHT-GameBoard.BOARD_HEIGHT-20);
		scores=board.getScores();
		info=new BufferedImage(Game.WIDTH,200,BufferedImage.TYPE_INT_RGB);
	
		mainMenu = new GuiButton(Game.WIDTH / 2-buttonWidth / 2, 450, buttonWidth, buttonHeight);
		tryAgain = new GuiButton(mainMenu.getX(), mainMenu.getY() - spacing - buttonHeight, buttonWidth, buttonHeight);
		
		tryAgain.setText("Igraj ponovo");
		mainMenu.setText("Početni meni");
		
		tryAgain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.getScores().reset();
				board.reset();
				alpha=0;
				
				remove(tryAgain);
				remove(mainMenu);	
				
				added=false;
			}			
		});
		
		mainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Menu");
			}
		});
	}
	
	/**
	 * Metoda za crtanje GUI-a.
	 */
	private void drawGui(Graphics2D g) {
		
		Graphics2D g2d=(Graphics2D) info.getGraphics();
		g2d.setColor(new Color(243, 237, 200));
		g2d.fillRect(0, 0, info.getWidth(), info.getHeight());
		g2d.setColor(new Color(234, 209, 150));
		g2d.setFont(scoreFont);
		g2d.drawString(""+scores.getCurrentScore(), 30, 40);
		g2d.setColor(new Color(191, 49, 49));
		g2d.drawString("Best: "+scores.getCurrentTopScore(), Game.WIDTH - DrawUtils.getMessageWidth("Best: "+scores.getCurrentTopScore(), scoreFont, g2d) - 20, 40);
		g2d.dispose();
		g.drawImage(info, 0, 0, null);
	}
	/**
	 * Metoda za crtanje kada je igra završena.
	 */
	public void drawGameOver(Graphics2D g) {
		g.setColor(new Color(199,197,199,alpha));
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color(125, 10, 10));
		g.setFont(gameOverFont);
		g.drawString("Kraj igre!",Game.WIDTH/2-DrawUtils.getMessageWidth("Kraj igre!", gameOverFont, g)/2,250);	
	}
	
	/**
	 * Metoda za ažuriranje logike igre.
	 * 
	 */
	@Override
	public void update() {
		board.update();
		if(board.isDead()) {
			alpha++;
			if(alpha>170)
				alpha=170;
		}
	}
	
	/**
	 * Metoda za crtanje sadržaja panela kada je igra u toku i kada je završena, tj. kada korisnik izgubi.
	 */
	@Override
	public void render(Graphics2D g) {
		drawGui(g);
		board.render(g);
		if(board.isDead()) {
			if(!added) {
				added=true;
				add(mainMenu);
				add(tryAgain);
			}
			drawGameOver(g);
		}
		super.render(g);
	}
}

