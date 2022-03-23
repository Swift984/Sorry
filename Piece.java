
public class Piece {
	public int color;
	public int pos;
	public int steps;
	public int move;
	
	public Piece(int c, int p, int s)
	{
		c = color;
		p = pos;
		s = steps;
		
	}
	
	public void move(int s)
	{
		move = 0;
		 if(s == 1)
			 move = 1;
		 System.out.println("ould you like to get a pawn out of start?");
		 if(s == 2)
			 move = 2;
		 if(s == 3)
			 move = 1;
		 if(s == 4)
			 move = 1;
		 if(s == 5)
			 move = 1;
		 if(s == 6)
			 move = 1;
		 if(s == 7)
			 move = 1;
		 if(s == 8)
			 move = 1;
		 if(s == 9)
			 move = 1;
		 if(s == 10)
			 move = 1;
		 if(s == 11)
			 move = 1;
		 if(s == 12)
			 move = 1;
		 
												 
			 
	}
}
