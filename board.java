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

    private int MouseX;
    private int MouseY;
	
	
	
	
	public Board()
	{
		setBackground(Color.WHITE);
		
		BoardJPG = new File("gameboard.jpg");
		
		addKeyListener( this );
		setFocusable( true );
		
		new Thread(this).start();
	}
	
	
	public void paint( Graphics window )
	{
		window.clearRect( 0,0, 1350, 1350);
		try {
			window.drawImage(ImageIO.read(BoardJPG), 0, 0, 1350, 1350, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MouseX = MouseInfo.getPointerInfo().getLocation().x;
		MouseY = MouseInfo.getPointerInfo().getLocation().y;
		window.setColor(Color.BLACK);
		window.drawString("Mouse  coordinates " + "(" + MouseX + "   " + MouseY + ")", 250, 30 );
		
		
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}