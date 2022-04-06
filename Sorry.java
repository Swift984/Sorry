import java.awt.*;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class Sorry extends JFrame
{
	private static final int WIDTH = 1296;
	private static final int HEIGHT = 1320;
	
	public Sorry() throws IOException
	{
		super("SORRY");

		setSize(WIDTH,HEIGHT);
		setLocation(650,0);
		setIconImage(new ImageIcon("pawnBLACK.png").getImage());
		
		getContentPane().add(new Board() );

		setVisible(true);	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	public static void main( String args[] ) throws IOException
	{
		Sorry run = new Sorry();
	}
}