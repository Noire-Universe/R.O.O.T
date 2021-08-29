package dev.noire.protorypeEngine.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class VictoryState extends State {
	
	//ui
	private UIManager uiManager;
	private int buttonWidth;
	private int buttonHeight;
	
	//background
	private BufferedImage background1, background2;
	private int background1Y, background2Y;
	private float speed;
	
	//timer for automatically move back to menuState;
	private long timer, lastTime;
	private int timeIndex;
	
	private float textLineOneWidth;
	private float textLineTwoWidth;
	private float textBlockHeight;
	private double textLineOneX;
	private double textLineTwoX;
	private double textBlockY;
	
	
	public VictoryState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		buttonWidth = (handler.getWidth()/15)*7;
		buttonHeight = handler.getHeight()/12;
		
		textLineOneWidth = handler.getWidth()/1.6f;
		textLineTwoWidth = handler.getWidth()/2.181f;
		textBlockHeight = handler.getHeight()/5.33f;
		textLineOneX = handler.getWidth()/2-(textLineOneWidth/2);
		textLineTwoX = handler.getWidth()/2-(textLineTwoWidth/2);
		textBlockY = handler.getHeight()/2-(textBlockHeight/2);
		
		//menu button
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/24)*17, buttonWidth, buttonHeight, Assets.buttonMenu, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Engine.menuState.loadMouseManager();
				State.setState(Engine.menuState);
			}
		}));
		
		timer = 0;
		lastTime = System.currentTimeMillis();
		timeIndex = 0;
		
		background1 = Assets.background;
		background2 = Assets.background;
		
		background1Y = 0;
		background2Y = handler.getHeight();
		speed = 1.0f;
		
	}
	
	public void update() {
		uiManager.update();
		
		//timer
		timer += System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > 1000) {
			timeIndex++;
			if(timeIndex>=60) {
				handler.getMouseManager().setUIManager(null);
				Engine.menuState.loadMouseManager();
				State.setState(Engine.menuState);
				timeIndex = 0;	
			}
			timer = 0;
		}
		
		//background
		background1Y -= speed;
		background2Y -= speed;
		if(background1Y + handler.getHeight() < 0)
			background1Y = handler.getHeight();
		if(background2Y + handler.getHeight() < 0)
			background2Y = handler.getHeight();
		
	}
	
	public void render(Graphics g) {
		g.drawImage(background1, 0, background1Y, handler.getWidth(), handler.getHeight()+1, null);
		g.drawImage(background2, 0, background2Y, handler.getWidth(), handler.getHeight()+1, null);
		uiManager.render(g);
		
		g.drawImage(Assets.victoryPlaque, handler.getWidth()/6, handler.getHeight()/8, (handler.getWidth()/3)*2, handler.getHeight()/8, null);
		g.setColor(Color.WHITE);
		g.setFont(handler.getFontManager().getSmallFont());
		
		//if current > previous:
		if(handler.getHiScoreManager().getCurrentScore() >= handler.getHiScoreManager().getPreviousScore())
			g.drawString("NEW HIGH SCORE!", (int)textLineOneX, (int)textBlockY+(int)(1*handler.getFontManager().getSmallRatio()));
				
		g.drawString("Score: " + (handler.getHiScoreManager().getCurrentScore()/60) + ":" + String.format("%02d", (handler.getHiScoreManager().getCurrentScore()%60)),(int)textLineTwoX, (int)textBlockY+(int)(4*handler.getFontManager().getSmallRatio()));
		
		g.setFont(handler.getFontManager().getQuoteFont());
		g.drawString("Thank you for playing my game!", 25, handler.getHeight()-15);
	}

	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
		
	}

}
