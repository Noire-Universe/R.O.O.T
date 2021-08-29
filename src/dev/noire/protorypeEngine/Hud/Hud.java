package dev.noire.protorypeEngine.Hud;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.gfx.Assets;

public class Hud {

	private Handler handler; 
	
	private int timeGathered;
	private int timeWasted;
	
	//live display:
	private double displayX, displayY, displaySpeedX, displaySpeedY;
	private int displayWidth, displayHeight;
	
	//high-score indicator:
	private float opacity;
	private float index;
	private float iterationIncrement;
	
	public Hud(Handler handler) {
		this.handler = handler;
		
		timeGathered = 0;
		timeWasted = 0;
		
		//live display:
		displaySpeedX = 0.5;
		displaySpeedY = 0.5;
		displayWidth = (handler.getWidth()/3)*2;
		displayHeight = handler.getHeight()/4;
		displayX = (handler.getWidth()/2)-(displayWidth/2);
		displayY = (handler.getHeight()/2)-(displayHeight/2);
		
		//high-score indicator:
		opacity = 200;
		index = 0;
		iterationIncrement = handler.getHiScoreManager().getPreviousScore()/16.0f;
	}
	
	public void update() {
		updateHighScoreIndicator();
		
		//live display:
		displayX += displaySpeedX;
		displayY += displaySpeedY;
		if(displayX<0||displayX>handler.getWidth()-displayWidth)
			displaySpeedX = -displaySpeedX;
		if(displayY<(handler.getHeight()/100)*10||displayY>(handler.getHeight()-displayHeight)-((handler.getHeight()/100)*10))
			displaySpeedY = -displaySpeedY;
		
	}
	
	public void render(Graphics g) {
		
		//live display:
		g.setColor(new Color(60, 60, 60, 25));
		g.setFont(handler.getFontManager().getHudFont());
		g.drawString(""+(timeGathered-timeWasted), (int)displayX+(displayWidth/2), (int)displayY+(displayHeight/2));
		
		//high-score indicator:
		g.drawImage(Assets.indicatorGraphics[((int)index)], 5, (handler.getHeight()-5)-80, 80, 80, null);
		g.setColor(new Color(0, 0, 0, (int)opacity));
		g.fillRect(5, (handler.getHeight()-5)-80, 80, 80);
	}
	
	private void updateHighScoreIndicator() {
		
		if(handler.getTimeManager().getTimeToSave() >= handler.getHiScoreManager().getPreviousScore()) {
			index = 15;
			opacity = 0;
		}else {
			index = handler.getTimeManager().getTimeToSave()/iterationIncrement;
			if(index >=15) index = 15; //<-fail-save 
			opacity = 192.0f-((192.0f/16.0f)*index);
		}
		
	}
	
	
	//GETTERS & SETTERS:
	public void timeGathered(int time) {
		timeGathered += time;
	}
	
	public void timeWasted(int time) {
		timeWasted += time;
	}
	
	public int getTimeGathered() {
		return timeGathered;
	}
	
	public int getTimeWasted() {
		return timeWasted;
	}
}
