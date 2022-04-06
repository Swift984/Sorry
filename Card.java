import java.io.*;

public class Card {
	private int moves;
	
	public Boolean isAnim = false;
	
	public int x = Board.DEFAULT_X;
	public int y = Board.DEFAULT_Y;
	
	public Card(int m) {
		moves = m;
	}
	
	public Card(int m, int xx, int yy) {
		moves = m;
		
		x = xx;
		y = yy;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public String toString() {
		return Integer.toString(moves);
	}
	
	public File getGraphic() {
		return new File(Integer.toString(moves) + "-Card.png");
	}
}
