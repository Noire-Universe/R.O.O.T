package dev.noire.protorypeEngine.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.noire.protorypeEngine.Handler;

public class BackgroundManager {

	private Handler handler;
	private ArrayList<Entity>backgroundObjects;
	
	private int interval;
	private long timer, lastTime;
	
	public BackgroundManager(Handler handler) {
		this.handler = handler;
		
		backgroundObjects = new ArrayList<Entity>();
		
		interval = 500;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	private void addObject() {
		backgroundObjects.add(new BackgroundObject(handler));
	}
	
	public void update() {
		timer += System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > interval) {
			timer = 0;
			addObject();
		}
		
		for(int i=0; i<backgroundObjects.size(); i++) {
			backgroundObjects.get(i).update();
			if(!backgroundObjects.get(i).isOnScreen)
				backgroundObjects.remove(i);
		}
		
	}
	
	public void render(Graphics g) {
		for(Entity bo : backgroundObjects)
			bo.render(g);
	}
	
}
