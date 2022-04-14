import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.*;

public class Playlist {
	private File[] files;
	private int index;
	
	private AudioInputStream inputStream;
	Clip clip;
	
	Random rand;
	
	private Boolean playing;
	
	public Playlist(int songs) throws UnsupportedAudioFileException, IOException {
		files = new File[songs];
		playing = false;
		
		rand = new Random(System.currentTimeMillis());
		index = rand.nextInt(songs);
		
		while(index != 3)
			index = rand.nextInt(songs);
		
		for(int i = 0; i < songs; i++) {
			files[i] = new File("songs\\" + i + ".wav");
			
		}
		
		for(File x : files) {
			System.out.println(x.toString());
		}
		
		inputStream = AudioSystem.getAudioInputStream(files[index].getAbsoluteFile());
	}
	
	public void next() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		stop();
		index++;
		
		if(index >= files.length)
			index = 0;
		
		System.out.println(index);
		play();
	}
	
	public void prev() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		stop();
		index--;
		
		if(index < 0)
			index = files.length -1;
		
		System.out.println(index);
		play();
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getLength() {
		return files.length;
	}
	
	public File getSong() {
		return files[index];
	}
	
	public Boolean isPlaying() {
		return playing;
	}
	
	public void play() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if(playing != true) {
			playing = true;
			inputStream = AudioSystem.getAudioInputStream(new File(files[index].toString()).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stop() {
		if(playing == true) {
			playing = false;
			clip.stop();
		}
	}

}
