package dev.noire.protorypeEngine.entities;

import java.awt.Rectangle;

import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.Launcher;

public abstract class MovingEntity extends Entity {

	public static final int DEFAULT_ENTITY_WIDTH = Launcher.TILESIZE,
							DEFAULT_ENTITY_HEIGHT = Launcher.TILESIZE;
	public static final float DEFAULT_SPEED = 1.5f; //1.25f;
	public static final float DEFAULT_GRAVITY = 0.5f;//0.6f;
	
	protected float speed, gravity, xMove, yMove;
	
	
	protected boolean isJumping;
	
	public MovingEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		speed = DEFAULT_SPEED;
		gravity = DEFAULT_GRAVITY;
		
		xMove = 0;
		yMove = 0;
		
		isJumping = false;
	}
	
	public Rectangle getRect() {return new Rectangle((int)x, (int)y, width, height);}

	//GETTERS & SETTERS:
	public float getSpeed() {
		return speed;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
}
