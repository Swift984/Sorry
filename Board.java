import java.awt.*;
import java.awt.List;
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

	private File title;
	private File titleBorder;
	private File titleLogo;

	
	private File BoardJPG;
	private File CardJPG;
	
	private File InstructionJPG;

	private File Red;
	private File Blue;
	private File Yellow;
	private File Green;
	
	private Piece swap1;
	private Piece swap2;
	
	
	private File Start;

	private File back;
	
	private int MouseX;
	private int MouseY;
	
	private int SELECT;
	private int TURN;
	
	private int Player = 1;
	private int PAWN = 1;
	
	int cnt = 0;
	
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
  
	private int Ox = 1280;
	private int Oy = 1280;
	private int Sx = 300;
	private int Sy = 150;
		
	private int cardx = 1;
	private int cardy = 1;
	
	private int Ax = 730;
  
	private int Cx = 166;
	private int Cy = 267;
	
	private Boolean anim = false;
	
	private Deck Deck;
	
	private ArrayList<Card> usedCards;
	
	private Boolean showInstructions = true;
	
	public Board()
	{
		setBackground(Color.WHITE);
		
		BoardJPG = new File("gameboard.jpg");
		CardJPG = new File("trans.png");
		InstructionJPG = new File("Instructions.png");
		back = new File("Back-Card.png");
		Red = new File("pawnRED.png");
		Blue = new File("pawnBLUE.png");
		Yellow = new File("pawnYELLOW.png");
		Green = new File("pawnGREEN.png");
		title = new File("Menu.png");
		titleBorder = new File("MenuBorder.png");
		titleLogo = new File("MenuSorry.png");
		Start = new File("start.png");
		
		SELECT = 1;
		TURN = 1;

		usedCards = new ArrayList<Card>();
		

		SELECT = 1;
	
		
		usedCards = new ArrayList<Card>();
		
		R1 = new Piece(Red, 11, 14, 1);
		R2 = new Piece(Red, 11, 14, 2);
		R3 = new Piece(Red, 11, 14, 3);
		R4 = new Piece(Red, 11, 14, 4);
		
		B1 = new Piece(Blue, 1, 11, 1);
		B2 = new Piece(Blue, 1, 11, 2);
		B3 = new Piece(Blue, 1, 11, 3);
		B4 = new Piece(Blue, 1, 11, 4);

		Y1 = new Piece(Yellow, 4, 1, 1);
		Y2 = new Piece(Yellow, 4, 1, 2);
		Y3 = new Piece(Yellow, 4, 1, 3);
		Y4 = new Piece(Yellow, 4, 1, 4);

		G1 = new Piece(Green, 14, 4, 1);
		G2 = new Piece(Green, 14, 4, 2);
		G3 = new Piece(Green, 14, 4, 3);
		G4 = new Piece(Green, 14, 4, 4);

		
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
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		drawPiece(R4, window);

		drawPiece(R3, window);
		drawPiece(R2, window);
		drawPiece(R1, window);
		
		drawPiece(B4, window);
		drawPiece(B3, window);
		drawPiece(B2, window);
		drawPiece(B1, window);
		
		drawPiece(Y4, window);
		drawPiece(Y3, window);
		drawPiece(Y2, window);
		drawPiece(Y1, window);
		
		drawPiece(G4, window);
		drawPiece(G3, window);
		drawPiece(G2, window);
		drawPiece(G1, window);
		
		
		
		window.setColor(Color.BLACK);
		MouseX = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
		MouseY = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
		window.setFont( new Font("Arial", 0, 12) );
		window.drawString( "Team " + Player + " is selected", 20, 30 );
		window.drawString( "Pawn " + PAWN + " is selected", 150, 30 );
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

		window.drawString("Pawn " + SELECT + " is selected", 572, 25 );

		try {
			if(showInstructions)
				window.drawImage(ImageIO.read(InstructionJPG), 0, 0, 1280, 1280, null);
		} catch(IOException e) {
			
		}
		
		try {
			window.drawImage(ImageIO.read(title), 0, 0, Ox, Oy, null);
			window.drawImage(ImageIO.read(titleBorder), 0, 0, Ox, Oy, null);
			window.drawImage(ImageIO.read(titleLogo), 0, 0, Ox, Oy, null);
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
			g.setFont( new Font("Calibri", 1, 30) );
			g.drawString("" + p.getNumber() + " ", 37+(p.getX()*75)+(p.getX()/3)+30, 37+(p.getY()*75)+(p.getY()/3)+65 );
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
			   Thread.sleep(100);
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
				titleBorder = new File("trans.png");
				titleLogo = new File("trans.png");
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

		
		if(e.getKeyCode() == KeyEvent.VK_F1)
			showInstructions = !showInstructions;
		
		//Red
		if(TURN == 1)
		{
			if(SELECT == 1)
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

			if(SELECT == 2)
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

			if(SELECT == 3)
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

			if(SELECT == 4)
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
			if(SELECT == 1)
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

			if(SELECT == 2)
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

			if(SELECT == 3)
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

			if(SELECT == 4)
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
			if(SELECT == 1)
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

			if(SELECT == 2)
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

			if(SELECT == 3)
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

			if(SELECT == 4)
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
			if(SELECT == 1)
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

			if(SELECT == 2)
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

			if(SELECT == 3)
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

			if(SELECT == 4)
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
			SELECT = 1;
		if(e.getKeyCode() == KeyEvent.VK_2 )
			SELECT = 2;
		if(e.getKeyCode() == KeyEvent.VK_3 )
			SELECT = 3;
		if(e.getKeyCode() == KeyEvent.VK_4 )
			SELECT = 4;
		
		if(e.getKeyCode() == KeyEvent.VK_S && Deck.size() == 0 )
		{
			usedCards.clear();
			Deck.Reset();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER )
		{
			TURN = TURN + 1;
			if(TURN > 4)
				TURN = 1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && Deck.size() != 0 )
		{
			cardx = 166;
			cardy = 267;
			
			Card c = Deck.poll();
			c.isAnim = true;
			usedCards.add(c);
			cnt--;
		}
		

		if(e.getKeyCode() == KeyEvent.VK_S )
		{
			Deck.Reset();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE )
		{
			cardx = 166;
			cardy = 267;
			
			Card c = Deck.poll();
			c.isAnim = true;
			usedCards.add(c);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_5 )
			PAWN = 1;
		if(e.getKeyCode() == KeyEvent.VK_6 )
			PAWN = 2;
		if(e.getKeyCode() == KeyEvent.VK_7 )
			PAWN = 3;
		if(e.getKeyCode() == KeyEvent.VK_8 )
			PAWN = 4;
		
		if(e.getKeyCode() == KeyEvent.VK_0 )
		{
			Player = Player + 1;
			if(Player > 4)
				Player = 1;
		}
		
		
			
			if(e.getKeyCode() == KeyEvent.VK_L )
			{
				//Red
				if(Player == 1)
				{
					if(PAWN == 1)
					{
						swap1 = R1;
					}
					if(PAWN == 2)
					{
						swap1 = R2;
					}
					if(PAWN == 3)
					{
						swap1 = R3;
					}
					if(PAWN == 4)
					{
						swap1 = R4;
					}
				}
				
				//Blue
				if(Player == 2)
				{
					if(PAWN == 1)
					{
						swap1 = B1;
					}
					if(PAWN == 2)
					{
						swap1 = B2;
					}
					if(PAWN == 3)
					{
						swap1 = B3;
					}
					if(PAWN == 4)
					{
						swap1 = B4;
					}	
				}
				
				//Yellow
				if(Player == 3)
				{
					if(PAWN == 1)
					{
						swap1 = Y1;
					}
					if(PAWN == 2)
					{
						swap1 = Y2;
					}
					if(PAWN == 3)
					{
						swap1 = Y3;
					}
					if(PAWN == 4)
					{
						swap1 = Y4;
					}	
				}
				
				//Green
				if(Player == 4)
				{
					if(PAWN == 1)
					{
						swap1 = G1;
					}
					if(PAWN == 2)
					{
						
						swap1 = G2;
					}
					if(PAWN == 3)
					{
						swap1 = G3;
					}
					if(PAWN == 4)
					{
						swap1 = G4;
					}
				}
			}
				
				
				
				if(e.getKeyCode() == KeyEvent.VK_K )
				{
					//Red
					if(Player == 1)
					{
						if(PAWN == 1)
						{					
							swap2 = R1;
						}
						if(PAWN == 2)
						{
							swap2 = R2;
						}
						if(PAWN == 3)
						{
							swap2 = R3;
						}
						if(PAWN == 4)
						{
							swap2 = R4;
						}
					}
					
					//Blue
					if(Player == 2)
					{
						if(PAWN == 1)
						{
							swap2 = B1;
						}
						if(PAWN == 2)
						{
							swap2 = B2;
						}
						if(PAWN == 3)
						{
							swap2 = B3;
						}
						if(PAWN == 4)
						{
							swap2 = B4;
						}	
					}
					
					//Yellow
					if(Player == 3)
					{
						if(PAWN == 1)
						{
							swap2 = Y1;
						}
						if(PAWN == 2)
						{
							swap2 = Y2;
						}
						if(PAWN == 3)
						{
							swap2 = Y3;
						}
						if(PAWN == 4)
						{
							swap2 = Y4;
						}	
					}
					
					//Green
					if(Player == 4)
					{
						if(PAWN == 1)
						{
							swap2 = G1;
						}
						if(PAWN == 2)
						{
							swap2 = G2;
						}
						if(PAWN == 3)
						{
							swap2 = G3;
						}
						if(PAWN == 4)
						{
							swap2 = G4;
						}
					}
				}		
					
				
		if(Deck.peek().getMoves() == 11 || Deck.peek().getMoves() == 0)
			cnt = 2;
		
		
		
			if(e.getKeyCode() == KeyEvent.VK_H)
			{
				swap1.changeX(swap2.getX());
				swap1.changeY(swap2.getY());
				swap2.gotoStart();
				
			}
			
			if(e.getKeyCode() == KeyEvent.VK_V )
			{
				int x = swap1.getX();
				int y = swap1.getY();
				swap1.changeX(swap2.getX());
				swap1.changeY(swap2.getY());
				swap2.changeX(x);
				swap2.changeY(y);
			}
		
		
	}
		
	
			

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
