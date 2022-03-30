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
	private File Red;
	private File Blue;
	private File Yellow;
	private File Green;
	
    private int MouseX;
    private int MouseY;
    
    private int wowX;
    private int wowY;
	
    private Piece R1;
	
	
	public Board()
	{
		setBackground(Color.WHITE);
		
		BoardJPG = new File("gameboard.jpg");
		Red = new File("pawnRED.png");
		Blue = new File("pawnBLUE.png");
		Yellow = new File("pawnYELLOW.png");
		Green = new File("pawnGREEN.png");
		wowX = 0;
		wowY = 0;
		R1 = new Piece("Red", 11, 14);
		
		addKeyListener( this );
		setFocusable( true );
		
		new Thread(this).start();
	}
	
	
	public void paint( Graphics window )
	{
		window.clearRect( 0,0, 1280, 1280);
		try {
			window.drawImage(ImageIO.read(BoardJPG), 0, 0, 1280, 1280, null);
			//window.drawImage(ImageIO.read(Red), 37+(wowX*75)+(wowX/3), 37+(wowY*75)+(wowY/3), 75, 75, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drawPiece(R1, window);
		
		MouseX = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
		MouseY = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
		window.setColor(Color.BLACK);
		window.drawString("Mouse  coordinates " + "(" + MouseX + "   " + MouseY + ")", 250, 30 );
		
		
	}
	
	public void drawPiece(Piece p, Graphics g)
	{
		
		try {
			g.drawImage(ImageIO.read(Red), 37+(wowX*75)+(wowX/3), 37+(wowY*75)+(wowY/3), 75, 75, null);
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
			wowX = wowX+1;
		if(e.getKeyCode() == KeyEvent.VK_LEFT )
			wowX = wowX-1;
		if(e.getKeyCode() == KeyEvent.VK_DOWN )
			wowY = wowY+1;
		if(e.getKeyCode() == KeyEvent.VK_UP )
			wowY = wowY-1;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
