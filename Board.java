import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

public class Board extends JPanel implements Runnable , KeyListener
{
	private File BoardJPG;
	private File CardJPG;
	private File Red;
	private File Blue;
	private File Yellow;
	private File Green;
	
    private int MouseX;
    private int MouseY;
	
    private Piece O;	//THIS IS THE SELECTED PIECE
    
    private Piece R1;
    private Piece R2;
    private Piece R3;
    private Piece R4;
    
    private Piece B1;
    private Piece B2;
    private Piece B3;
    private Piece B4;
    
    private Piece Y1;
    private Piece Y2;
    private Piece Y3;
    private Piece Y4;
    
    private Piece G1;
    private Piece G2;
    private Piece G3;
    private Piece G4;
	
	private Deck Deck;
    
	public Board()
	{
		setBackground(Color.WHITE);
		
		BoardJPG = new File("gameboard.jpg");
		CardJPG = new File("Back-Card.png");
		Red = new File("pawnRED.png");
		Blue = new File("pawnBLUE.png");
		Yellow = new File("pawnYELLOW.png");
		Green = new File("pawnGREEN.png");
		
		R1 = new Piece(Red, 11, 14);
		R2 = new Piece(Red, 11, 13);
		R3 = new Piece(Red, 11, 12);
		R4 = new Piece(Red, 11, 11);
		
		Deck = new Deck();
		
		
		addKeyListener( this );
		setFocusable( true );
		
		new Thread(this).start();
	}
	
	
	public void paint( Graphics window )
	{
		window.clearRect( 0,0, 1280, 1280);
		try {
			window.drawImage(ImageIO.read(BoardJPG), 0, 0, 1280, 1280, null);
			window.drawImage(ImageIO.read(CardJPG), 557, 640-(267/2), 166, 267, null); //166, 267
			//window.drawImage(ImageIO.read(Red), 37+(wowX*75)+(wowX/3), 37+(wowY*75)+(wowY/3), 75, 75, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drawPiece(R1, window);
		drawPiece(R2, window);
		drawPiece(R3, window);
		drawPiece(R4, window);
		
		MouseX = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
		MouseY = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
		window.setColor(Color.BLACK);
		window.drawString("Mouse  coordinates " + "(" + MouseX + "   " + MouseY + ")", 250, 30 );
		
		
	}
	
	public void drawPiece(Piece p, Graphics g)
	{
		
		try {
			g.drawImage(ImageIO.read(p.getColor()), 37+(p.getX()*75)+(p.getX()/3), 37+(p.getY()*75)+(p.getY()/3), 75, 75, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{
		try
		{
			while( true )
			{	
			   Thread.sleep(50);
			   repaint();
			}
		}
		catch( Exception e )
		{
			
		}
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT )
			R1.move("RIGHT");
		if(e.getKeyCode() == KeyEvent.VK_LEFT )
			R1.move("LEFT");
		if(e.getKeyCode() == KeyEvent.VK_DOWN )
			R1.move("DOWN");
		if(e.getKeyCode() == KeyEvent.VK_UP )
			R1.move("UP");
		if(e.getKeyCode() == KeyEvent.VK_SEMICOLON )
			CardJPG = Deck.poll().getGraphic();
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
