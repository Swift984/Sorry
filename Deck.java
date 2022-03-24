import java.util.*;

public class Deck {
	
	private Stack<Card> deck = new Stack<Card>();
	private Card[] rawDeck;
	
	public Deck() {
		Reset();
	}
	
	// The modern deck contains 45 cards: there are five 1 cards as well as four each of the other cards (Sorry!, 2, 3, 4, 5, 7, 8, 10, 11 and 12). The 6s or 9s are omitted to avoid confusion with each other. 
	public void Reset() {
		rawDeck = new Card[45];
		int inc = 0;
		
		for(int i = 0; i < 5; i++) 
			rawDeck[inc++] = new Card(1);
		
		for(int i = 0; i < 4; i++)
			rawDeck[inc++] = new Card(0);
		
		for(int x = 2; x <= 5; x++)
			for(int i = 0; i < 4; i++) 
				rawDeck[inc++] = new Card(x);
		
		for(int x = 7; x <= 8; x++)
			for(int i = 0; i < 4; i++) 
				rawDeck[inc++] = new Card(x);
		
		for(int x = 10; x <= 12; x++)
			for(int i = 0; i < 4; i++) 
				rawDeck[inc++] = new Card(x);
		
		System.out.println(inc);
		
		Shuffle();
		
		for(Card x : rawDeck) {
			System.out.print(x.getMoves() + ", ");
			deck.add(x);
		}
		
	}
	
	public int size()
	{
		return deck.size();
	}
	
	public void Shuffle() {
		int index;
		Card temp;
		
		Card[] array = rawDeck;
		
	    Random random = new Random();
	    
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
	}
	
	public Card poll() {
		return deck.pop();
	}
	
	public Card peek() {
		return deck.peek();
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck();
	}

}
