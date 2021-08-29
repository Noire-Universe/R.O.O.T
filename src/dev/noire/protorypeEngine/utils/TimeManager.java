package dev.noire.protorypeEngine.utils;

public class TimeManager {

	private boolean isRunning;
	
	long timer, lastTime;
	int seconds, minutes;
	int timeToSave;
	
	
	public TimeManager() {
		
		isRunning = false;
		
		timer = 0;
		lastTime = System.currentTimeMillis();
		seconds = 0;
		minutes = 0;
		timeToSave = 0;
		
	}
	
	public void update() {
		if(isRunning) {
			timer += System.currentTimeMillis()-lastTime;
			lastTime = System.currentTimeMillis();
			if(timer > 1000) {
				timer = 0;
				seconds++;
			}
			if(seconds >=59) {
				seconds = 0;
				minutes++;
			}
		}
		timeToSave = ((minutes*60)+seconds);
	}
	
	public void start() { isRunning = true; }
	public void stop() { isRunning = false;}
	
	public void restart() {
		timer = 0;
		lastTime = System.currentTimeMillis();
		seconds = 0;
		minutes = 0;
		timeToSave = 0;
	}

	//GETTERS & SETTERS:
	public int getSeconds() { return seconds; }
	public void setSeconds(int seconds) { this.seconds = seconds; }
	public int getMinutes() { return minutes; }
	public void setMinutes(int minutes) { this.minutes = minutes; }
	public int getTimeToSave() { return timeToSave; }
	public void setTimeToSave(int timeToSave) { this.timeToSave = timeToSave; }
}
