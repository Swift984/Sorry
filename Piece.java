import java.awt.*;
import javax.sound.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

public class Piece {
	
	private int pieceX;
	private int pieceY;
	private File color;
	private int number;
	
	public Piece(File c, int x, int y, int n)
	{
		color = c;
		pieceX = x;
		pieceY = y;
		number = n;
	}
	
	public int getX()
	{
		return pieceX;
	}
	

	public void changeX(int x)
	{
		pieceX = x;
	}

	public int getY()
	{
		return pieceY;
	}
	

	public void changeY(int y)
	{
		pieceY = y;
	}

	public File getColor()
	{
		return color;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public boolean sameColor(Piece t)
	{
		if(this.color == t.color)
			return true;
		return false;
	}
	

	public void swap(Piece a, Piece b)
	{
		int x = a.getX();
		int y = a.getY();
		
		a.pieceX = b.getX();
		a.pieceY = b.getY();
		b.pieceX = x;
		b.pieceY = y;
		
	}

	public void gotoStart()
	{
		if(this.color.equals(new File("pawnRED.png")))
		{
			pieceX = 11;
			pieceY = 14;
		}
		if(this.color.equals(new File("pawnBLUE.png")))
		{
			pieceX = 1;
			pieceY = 11;
		}
		if(this.color.equals(new File("pawnYELLOW.png")))
		{
			pieceX = 4;
			pieceY = 1;
		}
		if(this.color.equals(new File("pawnGREEN.png")))
		{
			pieceX = 14;
			pieceY = 4;
		}
	}
	
	public void move(String a)
	{
		if(a.equals("UP") && ((pieceY-1>=0 && (pieceX==0 || pieceX==15)) || ((pieceY-1>=9 && pieceY-1<=15) && pieceX==13) || ((pieceY-1<=6 && pieceY-1>=0) && pieceX==2) || (pieceY-1==0 && pieceX==4) || (pieceY-1==14 && pieceX==11)) )
			pieceY = pieceY-1;
		if(a.equals("DOWN") && ((pieceY+1<=15 && (pieceX==0 || pieceX==15)) || ((pieceY+1>=9 && pieceY+1<=15) && pieceX==13) || ((pieceY+1<=6 && pieceY+1>=0) && pieceX==2) || (pieceY+1==15 && pieceX==11) || (pieceY+1==1 && pieceX==4)) )
			pieceY = pieceY+1;
		if(a.equals("RIGHT") && ((pieceX+1<=15 && (pieceY==0 || pieceY==15)) || ((pieceX+1>=9 && pieceX+1<=15) && pieceY==2) || ((pieceX+1<=6 && pieceX+1>=0) && pieceY==13) || (pieceX+1==15 && pieceY==4) || (pieceX+1==1 && pieceY==11)) )
			pieceX = pieceX+1;
		if(a.equals("LEFT") && ((pieceX-1>=0 && (pieceY==0 || pieceY==15)) || ((pieceX-1>=9 && pieceX-1<=15) && pieceY==2) || ((pieceX-1<=6 && pieceX-1>=0) && pieceY==13) || (pieceX-1==0 && pieceY==11) || (pieceX-1==14 && pieceY==4)) )
			pieceX = pieceX-1;
	}
}
