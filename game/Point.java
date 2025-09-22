package game;

/**
 * Klasa koja se koristi za predstavljanje položaja pločice na igračkoj ploči.
 * Praktičan način za rad sa koordinatama u 2d prostoru
 */
public class Point {
	
	public int row;
	public int col;
	
	/**
	 * @param row-red
	 * @param col-kolona
	 * Konstruktor koji inicijalizira novi objekat tačke sa zadanim redom i kolonom.
	 */
	public Point(int row, int col) {
		this.row=row;
		this.col=col;
	}
}
