import java.awt.*;
import javax.sound.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

public class Piece {
	
	private int pieceX;
	private int pieceY;
	private String color;
	
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
}
