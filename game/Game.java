package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gui.GuiScreen;
import gui.LeaderboardPanel;
import gui.MenuPanel;
import gui.PlayPanel;

/**
 * Klasa 'Game' predstavlja glavnu klasu igre. 
 * Ova klasa nasljeđuje JPanel i implementira interfejse KeyListener, MouseListener, MouseMotionListener i Runnable.
 * Ova klasa sadrži glavnu petlju igre, kao i metode za ažuriranje i iscrtavanje ekrana igre.
 */
public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH=500;
	public static final int HEIGHT=600;
	public static final Font main=new Font("Bebas Neue Regular", Font.PLAIN, 28);
	
	private Thread game;
	private boolean running;
	private BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	private GuiScreen screen;
	
	/**
     * Konstruktor klase Game.
     * Postavlja postavke panela igre i dodaje listener-e događaja (tastatura, miš).
     * Inicijalizira GuiScreen i postavlja početni ekran na "Menu".
     */
	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		screen=GuiScreen.getInstance();
		screen.add("Menu", new MenuPanel());
		screen.add("Play",new PlayPanel());
		screen.add("Leaderboard", new LeaderboardPanel());
		screen.setCurrentPanel("Menu");
	}
	
	/**
     * Metoda za ažuriranje stanja igre.
     */
	private void update() {
		screen.update();
		Keyboard.update();	
	}
	
	 /**
     * Metoda za iscrtavanje ekrana igre.
     */
	private void render() {
		Graphics2D g=(Graphics2D)image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,WIDTH,HEIGHT);
		screen.render(g);
		g.dispose();
		
		Graphics2D g2d=(Graphics2D)getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}
	
	/**
     * Glavna petlja igre koja upravlja ažuriranjem i iscrtavanjem.
     */
	@Override
	public void run() {
		int fps=0, updates=0;
		long fpsTimer=System.currentTimeMillis();
		double nsPerUpdate=1000000000.0/60;		
		double then=System.nanoTime();
		double unprocessed=0;
		
	
		while(running) {
			
			boolean shouldRender=false;
			
			double now=System.nanoTime();
			unprocessed+=(now-then)/nsPerUpdate;
			then=now;
		
			while(unprocessed>=1) {
				updates++;
				update();
				unprocessed--;
				shouldRender=true;	
			}
			
			if(shouldRender) {
				fps++;
				render();
				shouldRender=false;
			}
			else {
				try {
					Thread.sleep(1);
				}
				catch(Exception e){
					e.printStackTrace();		
				}
			}
		}
	
		if(System.currentTimeMillis()-fpsTimer>1000) {
			System.out.printf("%d fps %d updates",fps,updates);
			System.out.println();
			fps=0;
			updates=0;
			fpsTimer+=1000;
		}
	}
	
	/**
     * Metoda za pokretanje igre (pokreće novi thread).
     */
	public synchronized void start() {
		if(running) return;
		running=true;
		game=new Thread(this,"game");
		game.start();
	}
	

    /**
     * Metoda za zaustavljanje igre (zatvara prozor igre).
     */
	public synchronized void stop() {
		if(!running) return;
		running=false;
		System.exit(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.keyReleased(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		screen.mouseDragged(e);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		screen.mouseMoved(e);	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		screen.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		screen.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}

