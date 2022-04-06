import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.sound.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

public class Board extends JPanel implements Runnable , KeyListener , MouseListener, MouseMotionListener
{
	public static int DEFAULT_X = 730;
	public static int DEFAULT_Y = 640-(267/2);
	private File BoardJPG;
	private File CardJPG;
	private File SORRY;
	private File title;
	
	private File Red;
	private File Blue;
	private File Yellow;
	private File Green;
	
	private File Start;	
	
	private File back;
	
    private int MouseX;
    private int MouseY;
    
    private int nuts;
    private int TURN;
    
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
    
    private int cardx = 1;
    private int cardy = 1;

    
    private int Ax = 730;
    
    int Sx = 300;
    int Sy = 150;
    
    int Ox = 1280;
    int Oy = 1280;
    
    private int mouse_x;
    private int mouse_y;
    
    private int Cx = 166;
    private int Cy = 267;
    
    private Boolean anim = false;
    
    private Deck Deck;
    
    private ArrayList<Card> usedCards;

	
	public Board()
	{
		setBackground(Color.WHITE);
		
		BoardJPG = new File("gameboard.jpg");
		CardJPG = new File("trans.png");
		back = new File("Back-Card.png");
		Red = new File("pawnRED.png");
		Blue = new File("pawnBLUE.png");
		Yellow = new File("pawnYELLOW.png");
		Green = new File("pawnGREEN.png");
		SORRY = new File("SORRY.png");
		title = new File("title.png");
		Start = new File("start.png");
		
		nuts = 1;
		TURN = 1;

		usedCards = new ArrayList<Card>();
		
		R1 = new Piece(Red, 11, 14);
		R2 = new Piece(Red, 11, 14);
		R3 = new Piece(Red, 11, 14);
		R4 = new Piece(Red, 11, 14);
		
		B1 = new Piece(Blue, 1, 11);
		B2 = new Piece(Blue, 1, 11);
		B3 = new Piece(Blue, 1, 11);
		B4 = new Piece(Blue, 1, 11);

		Y1 = new Piece(Yellow, 4, 1);
		Y2 = new Piece(Yellow, 4, 1);
		Y3 = new Piece(Yellow, 4, 1);
		Y4 = new Piece(Yellow, 4, 1);

		G1 = new Piece(Green, 14, 4);
		G2 = new Piece(Green, 14, 4);
		G3 = new Piece(Green, 14, 4);
		G4 = new Piece(Green, 14, 4);
		
		Deck = new Deck();
		
		addKeyListener( this );
		addMouseListener( this );
		setFocusable( true );
		
		new Thread(this).start();
	}
	
	
	public void paint( Graphics window )
	{
		window.clearRect( 0,0, 1280, 1280);
		
		try {
			
			window.drawImage(ImageIO.read(BoardJPG), 0, 0, 1280, 1280, null);

			if(!Deck.isEmpty())
				window.drawImage(ImageIO.read(back), 730, 640-(267/2), Cx, Cy, null);

			for(Card c : usedCards) {
				CardJPG = c.getGraphic();
				
				if(c.isAnim && c.x > 380)
					c.x -= 50;
          
				if(Deck.size() != 45)
				  window.drawImage(ImageIO.read(CardJPG), c.x, c.y, cardx, cardy, null);
				
//				if(Deck.peek().getMoves() == 0)
//				{
//					window.drawImage(ImageIO.read(SORRY), 450, 300, 600, 600, null);
//				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		drawPiece(R1, window);
		drawPiece(R2, window);
		drawPiece(R3, window);
		drawPiece(R4, window);

		drawPiece(B1, window);
		drawPiece(B2, window);
		drawPiece(B3, window);
		drawPiece(B4, window);

		drawPiece(Y1, window);
		drawPiece(Y2, window);
		drawPiece(Y3, window);
		drawPiece(Y4, window);

		drawPiece(G1, window);
		drawPiece(G2, window);
		drawPiece(G3, window);
		drawPiece(G4, window);
		
		window.setColor(Color.BLACK);
		MouseX = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
		MouseY = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
		window.drawString("Press F1 for help", 20, 30 );
		window.fillRect( 565, 7, 150, 25);
		window.setFont( new Font("Calibri", 1, 18) );
		if(TURN == 1)
			window.setColor(Color.RED);
		if(TURN == 2)
			window.setColor(Color.BLUE);
		if(TURN == 3)
			window.setColor(Color.YELLOW);
		if(TURN == 4)
			window.setColor(Color.GREEN);
		window.drawString("Pawn " + nuts + " is selected", 572, 25 );
		
		try {
			window.drawImage(ImageIO.read(title), 0, 0, Ox, Oy, null);
			window.drawImage(ImageIO.read(Start), 75, 980, Sx, Sy, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

	public void shuffle()
	{
		if(Deck.isEmpty())
		{
			back = new File("trans.png");
		}
		
	}

	public void mousePressed(MouseEvent e ) { 
		
	}
	/*2 mouseReleased -- when mouse button is released*/
	public void mouseReleased(MouseEvent e) { 
		
	}
	/*3 mouseEntered -- when the mouse enters the window */
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) { 
		
	}
	public void mouseClicked(MouseEvent e) { 	
		if(MouseX > 123 && MouseX < 323  && MouseY > 1020 && MouseY < 1060 )
		{
			if(e.getButton() == MouseEvent.BUTTON1)
				System.out.println("left click");
				title = new File("trans.png");
				Start = new File("trans.png");
				Ox = 0;
				Oy = 0;
				Sx = 0;
				Sy = 0;
		}
			
	}
	public void mouseDragged(MouseEvent e){  
		
	}
	
	public void mouseMoved(MouseEvent e){  		
		MouseX = e.getX(); 
    	MouseY = e.getY(); 
    }

	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
		//Red
		if(TURN == 1)
		{
			if(nuts == 1)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						R1.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						R1.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						R1.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						R1.move("UP");
			}

			if(nuts == 2)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						R2.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						R2.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						R2.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						R2.move("UP");
			}

			if(nuts == 3)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						R3.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						R3.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						R3.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						R3.move("UP");
			}

			if(nuts == 4)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						R4.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						R4.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						R4.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						R4.move("UP");
			}
		}
		
		//Blue
		if(TURN == 2)
		{
			if(nuts == 1)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						B1.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						B1.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						B1.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						B1.move("UP");
			}

			if(nuts == 2)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						B2.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						B2.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						B2.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						B2.move("UP");
			}

			if(nuts == 3)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						B3.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						B3.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						B3.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						B3.move("UP");
			}

			if(nuts == 4)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						B4.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						B4.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						B4.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						B4.move("UP");
			}
		}
		
		//Yellow
		if(TURN == 3)
		{
			if(nuts == 1)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						Y1.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						Y1.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						Y1.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						Y1.move("UP");
			}

			if(nuts == 2)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						Y2.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						Y2.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						Y2.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						Y2.move("UP");
			}

			if(nuts == 3)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						Y3.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						Y3.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						Y3.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						Y3.move("UP");
			}

			if(nuts == 4)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						Y4.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						Y4.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						Y4.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						Y4.move("UP");
			}
		}
		
		//Green
		if(TURN == 4)
		{
			if(nuts == 1)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						G1.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						G1.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						G1.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						G1.move("UP");
			}

			if(nuts == 2)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						G2.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						G2.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						G2.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						G2.move("UP");
			}

			if(nuts == 3)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						G3.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						G3.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						G3.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						G3.move("UP");
			}

			if(nuts == 4)
			{
				if(e.getKeyCode() == KeyEvent.VK_RIGHT )
						G4.move("RIGHT");
				if(e.getKeyCode() == KeyEvent.VK_LEFT )
						G4.move("LEFT");
				if(e.getKeyCode() == KeyEvent.VK_DOWN )
						G4.move("DOWN");
				if(e.getKeyCode() == KeyEvent.VK_UP )
						G4.move("UP");
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_1 )
			nuts = 1;
		if(e.getKeyCode() == KeyEvent.VK_2 )
			nuts = 2;
		if(e.getKeyCode() == KeyEvent.VK_3 )
			nuts = 3;
		if(e.getKeyCode() == KeyEvent.VK_4 )
			nuts = 4;
		

		
		
		if(e.getKeyCode() == KeyEvent.VK_S )
		{
			Deck.Reset();
		}
		
		
		

		if(e.getKeyCode() == KeyEvent.VK_ENTER )
		{
			TURN = TURN + 1;
			if(TURN > 4)
				TURN = 1;
		}
		

		if(e.getKeyCode() == KeyEvent.VK_SPACE )
		{
			cardx = 166;
			cardy = 267;
			
			Card c = Deck.poll();
			c.isAnim = true;
			usedCards.add(c);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
