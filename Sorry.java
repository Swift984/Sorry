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
		setIconImage(new ImageIcon("ICON.png").getImage());
		
		getContentPane().add(new Board() );

		setVisible(true);	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main( String args[] ) throws IOException
	{
		Sorry run = new Sorry();
	}
}
