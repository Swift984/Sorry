import java.io.File;
import java.util.Scanner;

public class Piece {
	public int pos;
	public int steps;
	public int move;
	private int pieceX;
	private int pieceY;
	private File color;
	
	public Piece(File c, int x, int y)
	{
		color = c;
		pieceX = x;
		pieceY = y;
	}
	
	public int getX()
	{
		return pieceX;
	}
	
	public int getY()
	{
		return pieceY;
	}
	
	public File getColor()
	{
		return color;
	}
	
	
	public boolean sameColor(Piece t)
	{
		if(this.color == t.color)
			return true;
		return false;
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
	
	
	
}
