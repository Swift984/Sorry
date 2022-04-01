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
	
	public int getY()
	{
		return pieceY;
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
