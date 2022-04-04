import java.io.*;

public class Card {
	private int moves;
	
	public Card(int m) {
		moves = m;
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