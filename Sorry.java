import java.awt.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Sorry extends JFrame
{
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1920;
	
	public Sorry() throws IOException
	{
		super("SORRY");

		setSize(WIDTH,HEIGHT);
		setLocation(325,0);
		
		getContentPane().add(new Board() );

		setVisible(true);	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void players() {
		System.out.println("How many players?");
		Scanner input = new Scanner(System.in);
		int p = input.nextInt();
			if(p > 0 && p < 5)
			{
				System.out.println(p);
			}
			else if(p < 1 || p > 4 )
			{
				System.out.println("Please pick a number of players between 1 and 4");
					players();
			}
	}
	public static void main( String args[] ) throws IOException
	{
		Sorry run = new Sorry();
		run.players();
		Deck deck = new Deck();
		if(deck.size() == 0)
			deck.Shuffle();
		
	}
}