package dev.noire.protorypeEngine.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.Launcher;
import dev.noire.protorypeEngine.Hud.Hud;

public class PlatformManager {

	private Handler handler;
	private ArrayList<Entity>platforms;
	private float interval;
	private float intervalModifier;
	private float speed;
	private long timer, lastTime;
	private Platform startPlatform;
	private Hud hud;
	
	public PlatformManager(Handler handler, float intervalModifier, Hud hud) {
		this.handler = handler;
		this.hud = hud;
		speed = MovingEntity.DEFAULT_SPEED;
		platforms = new ArrayList<Entity>();
		this.intervalModifier = intervalModifier;
		interval = 1750;//updated min and max values -->1750 // 1000 
		timer = 0;
		lastTime = System.currentTimeMillis();
		startPlatform = new Platform(handler,(int)handler.getWidth()/4.8f, 0, MovingEntity.DEFAULT_ENTITY_WIDTH*3, MovingEntity.DEFAULT_ENTITY_HEIGHT, 3, speed);
		platforms.add(startPlatform);
	}
	
	public void addPlatform() {
		int widthModifierLeft = (int)(Math.random()*4)+2;
		int widthModifierRight = (int)(Math.random()*4)+2;
		int widthLeft = MovingEntity.DEFAULT_ENTITY_WIDTH*widthModifierLeft;
		int widthRight = MovingEntity.DEFAULT_ENTITY_WIDTH*widthModifierRight;
		int height = MovingEntity.DEFAULT_ENTITY_HEIGHT;		
		int xPosLeft = (int)((Math.random()*(handler.getWidth()-widthLeft)-1)/2)+1;
		int yPosLeft = -1;
		int xPosRight = (int)(Math.random()*((handler.getWidth()/2)-widthRight)-1)+((handler.getWidth()/2)+1);
		int yPosRight = -(3*Launcher.TILESIZE); //<-arbitrary for now, but seems to work nicely!!!!
		platforms.add(new Platform(handler, xPosLeft, yPosLeft, widthLeft, height, widthModifierLeft, speed));
		platforms.add(new Platform(handler, xPosRight, yPosRight, widthRight, height, widthModifierRight, speed));
	}
	
	public void update() {
		//generating platforms:
		timer += System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > (interval*intervalModifier)) {
			timer = 0;
			addPlatform();
		}
		//updating platforms:
		for(int i=0; i<platforms.size(); i++) {
			if(platforms.get(i).isOnScreen()) {
				platforms.get(i).update();
			}else {
				if(!platforms.get(i).isWasUsed()) {
					hud.timeWasted(platforms.get(i).getValue());
				}
				platforms.remove(i);
			}
		}
	}
	
	public void render(Graphics g) {
		for(Entity platform : platforms)
			platform.render(g);
	}
	
	public ArrayList<Entity> getPlatforms() {
		return platforms;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float newSpeed) {
		speed = newSpeed;
	}
	
	public float getInterval() {
		return interval;
	}
	
	public void setInterval(float newInterval) {
		interval = newInterval;
	}
}
