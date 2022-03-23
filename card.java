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
}