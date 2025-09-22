package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import game.DrawUtils;
import game.Game;
import game.Leaderboard;

public class LeaderboardPanel extends GuiPanel{

	private Leaderboard lBoard;
	private int buttonWidth = 100;
	private int backButtonWidth = 220;
	private int buttonSpacing = 20;
	private int buttonY = 120;
	private int buttonHeight = 50;
	private int leaderboardsX = 130;
	private int leaderboardsY = buttonY + buttonHeight + 90;
	
	private String title = "Najbolji rez";
	private Font titleFont = Game.main.deriveFont(48f);
	private Font scoreFont = Game.main.deriveFont(30f);
	private State currentState = State.SCORE;
	
	/**
	 * Konstruktor klase LeaderboardPanel.
	 * Postavlja dugmad za prelazak između prikaza top pločica i top rezultata, kao i povratak na glavni meni.
	 */
	public LeaderboardPanel(){
		super();
		lBoard = Leaderboard.getInstance();
		lBoard.loadScores();

		GuiButton tileButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2, buttonY, buttonWidth, buttonHeight);
		tileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentState = State.TILE;
			}
		});
		tileButton.setText("Pločice");
		add(tileButton);
		
		GuiButton scoreButton = new GuiButton(Game.WIDTH / 2 - buttonWidth / 2 - tileButton.getWidth() - buttonSpacing, buttonY, buttonWidth, buttonHeight);
		scoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentState = State.SCORE;
			}
		});
		scoreButton.setText("Bodovi");
		add(scoreButton);
		
		
		
		GuiButton backButton = new GuiButton(Game.WIDTH / 2 - backButtonWidth / 2, 500, backButtonWidth, 60);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GuiScreen.getInstance().setCurrentPanel("Menu");
			}
		});
		backButton.setText("Nazad");
		add(backButton);
	}
	
	/**
	 * Metoda koja isccrta top liste pločica i bodova na ekranu.
	 */
	private void drawLeaderboards(Graphics2D g){
		ArrayList<String> strings = new ArrayList<String>();
		if(currentState == State.SCORE){
			strings = convertToStrings(lBoard.getTopScores());
		}
		else if(currentState == State.TILE){
			strings = convertToStrings(lBoard.getTopTiles());
		}
		
		g.setColor(Color.black);
		g.setFont(scoreFont);
		
		for(int i = 0; i < strings.size(); i++){
			String s = (i + 1) + ". " + strings.get(i);
			g.drawString(s, leaderboardsX, leaderboardsY + i * 40);
		}
	}
	
	/**
	 * Metoda koja pretvara listu brojeva u listu stringova.
	 */
	private ArrayList<String> convertToStrings(ArrayList<? extends Number> list){
		ArrayList<String> ret = new ArrayList<String>();
		for(Number n : list){
			ret.add(n.toString());
		}
		return ret;
	}
	
	@Override
	public void update(){
		
	}
	
	/**
	 * Metoda za crtanje sadržaja panela.
	 * Crta naslov "Leaderboard" i top liste pločica ili rezultata na ekranu.
	 */
	@Override
	public void render(Graphics2D g){
		super.render(g);
		g.setColor(Color.black);
		g.drawString(title, Game.WIDTH / 2 - DrawUtils.getMessageWidth(title, titleFont, g) / 2, DrawUtils.getMessageHeight(title, titleFont, g) + 40);
		drawLeaderboards(g);
	}
	
	/**
	 * Enumeracija koja predstavlja trenutno stanje prikaza (pločice ili rezultati).
	 */
	private enum State{
		SCORE,
		TILE
	}
}


