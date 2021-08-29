package dev.noire.protorypeEngine.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundObject {

	private File file;
	private Clip clip;
	private FloatControl gainControl;
	//private float volume;
	private boolean isLooping;
	
	
	public SoundObject(String location, boolean isLooping) {
		
		this.isLooping = isLooping;
		
		file = new File(location);
		//volume = 0; // <-- local volume value for the individual object
		
		try {
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
		}catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
			System.out.println("unsupported audio file"); //<-- debug!
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("IOException");  //<-- debug!
		}
		catch(LineUnavailableException e) {
			e.printStackTrace();
			System.out.println("LineUnavailable");  //<-- debug!
		}
	}
	
	public void play() {
		Runnable jumpPlayer = new Runnable() {
			@Override
			public void run() {
				try {
					clip.setMicrosecondPosition(0);
					gainControl.setValue(gainControl.getValue()); //<-- re-think if volume is the correct approach
					clip.start();
					if(isLooping)
						clip.loop(Clip.LOOP_CONTINUOUSLY); //<-- this is the line to loop the file
				}catch(Exception e) {
					e.printStackTrace();
				}
			
				}
			};
			new Thread(jumpPlayer).start();
	}
	public void stop() {
		clip.stop();
	}
	
	public void increaseVolume(float modifier) {
		if(modifier + gainControl.getValue() < gainControl.getMaximum())
			gainControl.setValue(gainControl.getValue()+modifier);
	}
	
	public void decreaseVolume(float modifier) {
		if(gainControl.getValue() - modifier > gainControl.getMinimum())
			gainControl.setValue(gainControl.getValue()-modifier);
	}
	
	public void setValue(float newValue) {
		if(gainControl.getValue()-newValue > gainControl.getMinimum() && gainControl.getValue()+newValue < gainControl.getMaximum()) {
			gainControl.setValue(gainControl.getValue()+newValue);
		}
	}
	
	public float getCurrentValue() { return gainControl.getValue();}
	public float getMaxValue() { return gainControl.getMaximum();}
	public float getMinValue() { return gainControl.getMinimum();}
}
