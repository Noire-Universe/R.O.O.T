package dev.noire.protorypeEngine.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.gfx.Assets;

public class BackgroundObject extends MovingEntity {

	private int index;
	private BufferedImage texture;
	private boolean isOnScreen;
	private float modifier;
	
	public BackgroundObject(Handler handler) {
		super(handler, 0, 0, MovingEntity.DEFAULT_ENTITY_WIDTH*2, MovingEntity.DEFAULT_ENTITY_HEIGHT*2);
		
		//picking a texture:
		index = (int)(Math.random()*10); //no offset since we are getting an array index
		texture = Assets.backgroundObjects[index];

		modifier = (float)(Math.random()*16)+1;
		speed = (speed -(modifier/100))*-1;
		
		x = (int)(Math.random()*(handler.getWidth()))+1;
		y = handler.getHeight()+1;
		
		isOnScreen = true;
	}
	
	public void update() {
		
		y += speed;
		if(y < -(MovingEntity.DEFAULT_ENTITY_HEIGHT*2))
			isOnScreen = false;
			
	}
	
	public void render(Graphics g) {
		
		g.drawImage(texture, (int)x, (int)y, width-(int)modifier, height-(int)modifier, null);
		g.setColor(new Color(0, 0, 0, (int)modifier));
		g.fillRect((int)x, (int)y, width-(int)modifier, height-(int)modifier);
		
	}

	public int getValue() { //not used;
		return 0;
	}
	
	public boolean isOnScreen() {
		return isOnScreen;
	}

}
