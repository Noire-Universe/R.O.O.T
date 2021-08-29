package dev.noire.protorypeEngine.entities;

import java.awt.Graphics;

import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.Launcher;
import dev.noire.protorypeEngine.gfx.Assets;

public class Platform extends MovingEntity {

	private int value;
	private float speed;
	
	public Platform(Handler handler, float x, float y, int width, int height, int value, float speed) {
		super(handler, x, y, width, height);
		
		this.speed = speed;
		this.value = 1;
		isOnScreen = true;
		wasUsed = false;	
		
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = width;
		bounds.height = height;
	}

	public void update() {
		y += speed;
		if(y + 1 > handler.getHeight())
			isOnScreen = false;
	}
	
	public void render(Graphics g) {
		if(!wasUsed) {
			//indicate platform has not been jumped on
			for(int i=0; i<(width/Launcher.TILESIZE); i++)
				g.drawImage(Assets.platformGraphics[1], (int)x+(Launcher.TILESIZE*i), (int)y, Launcher.TILESIZE, height, null);
		}else {
			//indicate platform has been jumped on
			for(int i=0; i<(width/Launcher.TILESIZE); i++)
				g.drawImage(Assets.platformGraphics[0], (int)x+(Launcher.TILESIZE*i), (int)y, Launcher.TILESIZE, height, null);
		}
	}
	
	//GETTERS & SETTERS:
	public int getValue() {
		return value;
	}

}
