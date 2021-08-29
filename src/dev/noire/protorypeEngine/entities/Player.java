package dev.noire.protorypeEngine.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.gfx.Animation;
import dev.noire.protorypeEngine.gfx.Assets;

public class Player extends MovingEntity {
	
	private Rectangle leftSide, rightSide, top, bottom;
	
	private Animation playerIdleAnimation;
	private Animation playerJumpUpAnimation;
	private Animation playerJumpLeftAnimation;
	private Animation playerJumpRightAnimation;
	private float jumpStrength;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, MovingEntity.DEFAULT_ENTITY_WIDTH, MovingEntity.DEFAULT_ENTITY_HEIGHT);
		
		playerIdleAnimation = new Animation(250, Assets.playerIdle);
		playerJumpUpAnimation = new Animation(2, Assets.playerJumpUp);
		playerJumpLeftAnimation = new Animation(5, Assets.playerJumpLeft);
		playerJumpRightAnimation = new Animation(5, Assets.playerJumpRight);
		
		bounds.x = (int)x;
		bounds.y = (int)y;
		bounds.width = MovingEntity.DEFAULT_ENTITY_WIDTH;
		bounds.height = MovingEntity.DEFAULT_ENTITY_HEIGHT;
		
		speed = 3.7f; //overwrite superclass speed
		jumpStrength = -13;
		
		createBoundingBoxes();
	}
	
	private void createBoundingBoxes() {
		int boxSize = 5;
		leftSide = new Rectangle(0, 0, boxSize, MovingEntity.DEFAULT_ENTITY_HEIGHT);
		rightSide = new Rectangle(MovingEntity.DEFAULT_ENTITY_WIDTH-boxSize, 0, boxSize, MovingEntity.DEFAULT_ENTITY_HEIGHT);
		top = new Rectangle(0, 0, MovingEntity.DEFAULT_ENTITY_WIDTH, boxSize);
		bottom = new Rectangle(0, MovingEntity.DEFAULT_ENTITY_HEIGHT-boxSize, MovingEntity.DEFAULT_ENTITY_WIDTH, boxSize);

	}
	
	public void update() {
		move();
		getInput();
		
		if(x<0)
			x=0;
		if(x>handler.getWidth()-width)
			x=handler.getWidth()-width;
		
		playerIdleAnimation.update();
		playerJumpUpAnimation.update();
		playerJumpLeftAnimation.update();
		playerJumpRightAnimation.update();
	}
	
	public void move() {
		x += xMove;
		y += yMove;
		yMove += gravity;
		
	}
	
	private void getInput() {
		xMove = 0;
		
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		if(handler.getKeyManager().jump && !isJumping)
			jump();
	}
	
	public void jump() {
		isJumping = true;
		yMove = jumpStrength;
	}
	
	public void render(Graphics g) {
	
		g.drawImage(renderFrame(), (int)x, (int)y, width, height, null);
	}
	
	//GETTERS FOR RECTANGLES!!!!
	public Rectangle getLeftSide() {return new Rectangle((int)(x+leftSide.x), (int)y+leftSide.y, leftSide.width, leftSide.height);}
	public Rectangle getRightSide() {return new Rectangle((int)(x+rightSide.x), (int)y+rightSide.y, rightSide.width, rightSide.height);}
	public Rectangle getBottom() {return new Rectangle((int)(x+bottom.x), (int)y+bottom.y, bottom.width, bottom.height);}
	public Rectangle getTop() {return new Rectangle((int)(x+top.x), (int)y+top.y, top.width, top.height);}
	
	public int getValue() {
		return 0;
	}
	
	private BufferedImage renderFrame() {
		if(isJumping && xMove < 0) return playerJumpLeftAnimation.getCurrentFrame();
		else if(isJumping && xMove > 0)return playerJumpRightAnimation.getCurrentFrame();
		else if(isJumping) return playerJumpUpAnimation.getCurrentFrame();
		else return playerIdleAnimation.getCurrentFrame();
	}

	public float getJumpStrength() {
		return jumpStrength;
	}

	public void setJumpStrength(float jumpStrength) {
		this.jumpStrength = jumpStrength;
	}
	
}
