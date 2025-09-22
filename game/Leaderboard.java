package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Klasa predstavlja pomoćnu klasu za praćenje bodova i pločica sa najvećim vrijednostima u igri.
 */
public class Leaderboard {
	private static Leaderboard lBoard;
	private String filePath;
	private String highScores;
	
	private ArrayList<Integer> topScores;
	private ArrayList<Integer> topTiles;
	
	/**
	 * Konstruktor koji dobavlja apsolutnu putanju do file-a gdje spremamo rez, naziv file-a, te inicijaliziramo liste za najbolje bodove/pločice.
	 */
	private Leaderboard() {
		filePath=new File("").getAbsolutePath();
		highScores="Rezultati";
		
		topScores=new ArrayList<Integer>();
		topTiles=new ArrayList<Integer>();
	}
	
	 
    /**
     * Metoda za dobavljanje instance klase 'Leaderboard'.
     */
	public static Leaderboard getInstance() {
		if(lBoard==null) {
			lBoard=new Leaderboard();
		}
		return lBoard;
	}
	
	 /**
     * Metoda za dodavanje rezultata u listu najboljih rezultata.
     */
	public void addScore(int score) {
		for(int i=0;i<topScores.size();i++) {
			//ako je novi skor veci od bilo kojeg na ljestvici, ubacuje se na to mjesto
			if(score>=topScores.get(i)) {
				topScores.add(i, score);
				//izbacimo posljednji u ljestvici jer je ovaj novi zauzeo jedno mjesto
				topScores.remove(topScores.size()-1);
				return;
			}
		}
		
	}
	
	/**
     * Metoda za dodavanje vrijednosti pločice u listu najboljih vrijednosti pločice.
     */
	public void addTile(int tileValue) {
		for(int i=0;i<topTiles.size();i++) {
			if(tileValue>=topTiles.get(i)) {
				topTiles.add(i, tileValue);
				topTiles.remove(topTiles.size()-1);
				return;
			}
		}
	}
	
	 /**
     * Metoda za učitavanje rezultata iz file-a.
     */
	public void loadScores() {
		try {
			File f=new File(filePath,highScores);
			if(!f.isFile()) {
				createSaveData();
			}
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			
			topScores.clear();
			topTiles.clear();
			
			String[] scores=reader.readLine().split("-");
			String[] tiles=reader.readLine().split("-");
			
			for(int i=0;i<scores.length;i++) {
				topScores.add(Integer.parseInt(scores[i]));
				
			}
			for(int i=0;i<tiles.length;i++) {
				topTiles.add(Integer.parseInt(tiles[i]));
				
			}
			reader.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Metoda za spremanje rezultata u file.
     */
	public void saveScores() {
		FileWriter output=null;
		
		try {
			File f=new File(filePath,highScores);
			output=new FileWriter(f);
			BufferedWriter writer=new BufferedWriter(output);
			
			writer.write(topScores.get(0)+"-"+topScores.get(1)+"-"+topScores.get(2)+"-"+topScores.get(3)+"-"+topScores.get(4));
			writer.newLine();
			writer.write(topTiles.get(0)+"-"+topTiles.get(1)+"-"+topTiles.get(2)+"-"+topTiles.get(3)+"-"+topTiles.get(4));
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * Metoda za stvaranje novih podataka o rezultatima ako file ne postoji.
     */
	private void createSaveData() {
		FileWriter output=null;
		
		try {
			File f=new File(filePath,highScores);
			output=new FileWriter(f);
			BufferedWriter writer=new BufferedWriter(output);
			
			writer.write("0-0-0-0-0");
			writer.newLine();
			writer.write("0-0-0-0-0");
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getHighScore() {
		return topScores.get(0);
	}
	
	public int getHighTile() {
		return topTiles.get(0);
	}

	public ArrayList<Integer> getTopScores() {
		return topScores;
	}

	public ArrayList<Integer> getTopTiles() {
		return topTiles;
	}
	
}
