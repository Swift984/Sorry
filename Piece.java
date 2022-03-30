import java.util.Scanner;

public class Piece {
	public String color;
	public int pos;
	public int steps;
	public int move;
	private int pieceX;
	private int pieceY;

	
	public Piece(String c, int x, int y)
	{
		color = c;
		pieceX = x;
		pieceY = y;
	}
	
	
	
	public void move(String a)
	{
		if(a.equals("UP"))
			pieceY = pieceY-1;
		if(a.equals("DOWN"))
			pieceY = pieceY+1;
		if(a.equals("RIGHT"))
			pieceX = pieceX+1;
		if(a.equals("LEFT"))
			pieceX = pieceX-1;
	}
	
	public void move(int s)
	{
	
		move = 0;
		if(s == 0)
		{
			System.out.println("If you have a pawn in start you can switch it's place with an opponants pawn on the board. Otherwise skip your turn.");
		}
		 if(s == 1)
			 move = 1;
		 System.out.println("Would you like to get a pawn out of start?");
		 
		 if(s == 2)
			 move = 2;
		 System.out.println("Would you like to get a pawn out of start?");
		 
		 if(s == 3)
			 move = 3;
		 
		 if(s == 4)
			 move = -4;
		 
		 if(s == 5)
			 move = 5;
		 
		 if(s == 6)
			 move = 6;
		 
		 if(s == 7)
			 move = 7;
		 System.out.println("Would you like to split the movement between 2 pawns");
		 
		 if(s == 8)
			 move = 8;
		 
		 if(s == 9)
			 move = 9;
		 
		 if(s == 10)
			 System.out.println("Would you like to get A move 10, or B move backwards 1?");
		 		Scanner input = new Scanner(System.in);
		 		String p = input.next();
		 		while(p != null)
		 		{
		 			if(p == "A" )
		 				move = 10;
		 			if(p == "B" )
		 				move = -1;
		 		}
		 if(s == 11)
			 move = 11;
		 	System.out.println("Would you like to switch places with another players pawn?");
		 	
		 if(s == 12)
			 move = 12;
		 
												 
			 
	}
}
