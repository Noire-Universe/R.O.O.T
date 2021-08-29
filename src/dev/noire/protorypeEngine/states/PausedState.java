package dev.noire.protorypeEngine.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class PausedState extends State {
	
	private UIManager uiManager;
	private int buttonWidth;
	private int buttonHeight;
	
	private BufferedImage background1, background2;
	
	private int background1Y, background2Y;
	private float speed;
	
	public PausedState(Handler handler) {
		super(handler);
		
		buttonWidth = (handler.getWidth()/15)*7;
		buttonHeight = handler.getHeight()/12;
		
		uiManager = new UIManager(handler);
		
		background1 = Assets.background;
		background2 = Assets.background;
		
		background1Y = 0;
		background2Y = handler.getHeight();
		speed = 1.0f;
		
		//continue button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/6)*5, buttonWidth, buttonHeight, Assets.buttonContinue, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getTimeManager().start();
				State.setState(Engine.gameState);
			}
		}));
	}
	
	public void update() {
		
		uiManager.update();
		
		background1Y -= speed;
		background2Y -= speed;
		
		if(background1Y + handler.getHeight() < 0)
			background1Y = handler.getHeight();
		
		if(background2Y + handler.getHeight() < 0)
			background2Y = handler.getHeight();
		
		if(handler.getKeyManager().enter) {
			handler.getTimeManager().start();
			State.setState(Engine.gameState);
		}
		
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(background1, 0, background1Y, handler.getWidth(), handler.getHeight()+1, null);
		g.drawImage(background2, 0, background2Y, handler.getWidth(), handler.getHeight()+1, null);
		
		g.drawImage(Assets.pausedPlaque, handler.getWidth()/6, handler.getHeight()/8, (handler.getWidth()/3)*2, handler.getHeight()/8, null);
		
		uiManager.render(g);
		
		
	}
	
	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

}
