package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Score {
	/**
	 * Varijabla koja čuva informaciju o trenutnom rezultatu.
	 */
	private int currentScore;
	/**
	 * Varijabla koja čuva informaciju o najboljem rezultatu.
	 */
	private int currentTopScore;
	
	/**
	 * Varijabla koja polja iz igračke ploče čuva u nizu.
	 */
	private int[] board=new int[GameBoard.ROWS*GameBoard.COLS];
	
	/**
	 * Varijabla koja čuva putanju do datoteke.
	 */
	private String filePath;
	
	/**
	 * Varijabla koja čuva naziv datoteke.
	 */
	private String temp="TEMP.tmp";
	

	/**
	 * Varijabla za igračku ploču.
	 */
	private GameBoard gameBoard;
	

	/**
	 * Boolean varijabla za praćenje da li je započeta nova igra.
	 */
	private boolean newGame=false;
	

	/**
	 * Konstruktor koji prima GameBoard i pored toga u filePath stavlja apsolutnu putanju trenutnog radnog direktorija. 
	 */
	public Score(GameBoard gameBoard) {
		this.gameBoard=gameBoard;
		
		filePath=new File("").getAbsolutePath();
	}
	
	/**
	 * Metoda koja resetira trenutni rezultat i briše privremenu datoteku
	 */
	public void reset() {
		File f=new File(filePath,temp);
		if(f.isFile()) {
			f.delete();
		}
		newGame=true;
		currentScore=0;	
	}
	
	/**
	 * Metoda koja stvara privremenu datoteku za praćenje rezultata, stanja igrice i postavlja ih na početne vrijednosti.
	 */
	private void createFile() {
		FileWriter output=null;
		newGame=true;
		
		try {
			File f=new File(filePath,temp);
			output=new FileWriter(f);
			BufferedWriter writer=new BufferedWriter(output);
			writer.write(""+0);
			writer.newLine();
			writer.write(""+0);
			writer.newLine();
			
			for(int row=0;row<GameBoard.ROWS;row++) {
				for(int col=0;col<GameBoard.COLS;col++) {
					if(row==GameBoard.ROWS-1 && col==GameBoard.COLS-1) {
						writer.write(""+0);
					}
					else {
						writer.write(0+"-");
					}
				}
			}
			writer.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Metoda koja sprema trenutno stanje igre u datoteku
	 */
	public void saveGame() {
		FileWriter output=null;
		
		if(newGame)
			newGame=false;
		try {
			File f=new File(filePath,temp);
			output=new FileWriter(f);
			BufferedWriter writer=new BufferedWriter(output);
			writer.write(""+currentScore);
			writer.newLine();
			writer.write(""+currentTopScore);
			writer.newLine();
			
			for(int row=0;row<GameBoard.ROWS;row++) {
				for(int col=0;col<GameBoard.COLS;col++) {
					int location=row*GameBoard.COLS+col;
					Tile tile=gameBoard.getBoard()[row][col];
					this.board[location]=tile!=null ? tile.getValue():0;
					
					if(row==GameBoard.ROWS-1 && col==GameBoard.COLS-1) {
						writer.write(""+board[location]);
					}
					else {
						writer.write(board[location]+"-");
					}
				}
			}			
			writer.close();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Metoda koja učitava stanje igre iz datoteke
	 */
	public void loadGame() {
		try {
			File f=new File(filePath,temp);
			if(!f.isFile()) {
				createFile();
			}
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			currentScore=Integer.parseInt(reader.readLine());
			currentTopScore=Integer.parseInt(reader.readLine());
			
			String[] board=reader.readLine().split("-");
			for(int i=0;i<board.length;i++) {
				this.board[i]=Integer.parseInt(board[i]);
			}
			
			reader.close();
		}
		catch(Exception e){
			e.printStackTrace();		
		}
	}

	/**
	 * Geter za trenutni rezultat. 
	 */
	public int getCurrentScore() {
		return currentScore;
	}
	
	/**
	 * Seter za trenutni rezultat. 
	 */
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	/**
	 * Geter za najbolji rezultat. 
	 */
	public int getCurrentTopScore() {
		return currentTopScore;
	}

	/**
	 * Seter za najbolji rezultat. 
	 */
	public void setCurrentTopScore(int currentTopScore) {
		this.currentTopScore = currentTopScore;
	}

	/**
	 * Geter za igračku ploču
	 */
	public int[] getBoard() {
		return board;
	}
	
	/**
	 * Metoda koja detektuje da li je započeta nova igra ili ne.
	 */
	public boolean newGame() {
		return newGame;
	}
}
