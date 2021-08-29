package dev.noire.protorypeEngine.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import dev.noire.protorypeEngine.Handler;

public class HiScoreManager {

	private int previousScore;
	private int currentScore;
	
	public HiScoreManager(Handler handler) {
		previousScore = 0;
		currentScore = 0;
		loadHighScore();
	}
	
	public void update() {} 
	
	public void loadHighScore() {
		String path = "";
		path = System.getenv("APPDATA") + File.separator + "hiscore.dat";
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(path));
			previousScore = input.readInt();
			input.close();
		} catch (IOException e) {
			previousScore = 000;
		}
	}
	
	public void saveScore(int current) {
		currentScore = current;
		
		String path = "";
		path = System.getenv("APPDATA") + File.separator + "hiscore.dat";
		
		if(currentScore > previousScore) {
			try {
				DataOutputStream output = new DataOutputStream(new FileOutputStream(path));
				output.writeInt(currentScore);
				output.close();
			} catch (IOException e) {
				//do nothing
			}
		}
	}
	
	public void resetHiScore() {
		
		String path = "";
		path = System.getenv("APPDATA") + File.separator + "hiscore.dat";
		
		try {
			DataOutputStream output = new DataOutputStream(new FileOutputStream(path));
			output.writeInt(000);
			output.close();
		} catch (IOException e) {
			//do nothing
		}
	}
	
	public int getPreviousScore() { return previousScore;}
	public int getCurrentScore() {return currentScore;}
	public void setCurrentScore(int newScore) { currentScore = newScore; }//use with caution!! saveScore does a very similar thing!
	public void setPreviousScore(int zeroValue) {
		previousScore = zeroValue;
	}
}
