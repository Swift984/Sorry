
public class Card {
	int steps;
	Boolean sorry;
	
	public Card( int s, Boolean b) {
		steps = s;
		sorry = b;
	} 
	
	public Boolean isSorry() {
		return sorry;
	}
	
	public int getSteps() {
		return steps;
	}
}
