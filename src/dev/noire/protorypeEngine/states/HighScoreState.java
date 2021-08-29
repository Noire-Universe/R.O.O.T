package dev.noire.protorypeEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class HighScoreState extends State {

	private int buttonWidth, buttonHeight;
	private UIManager uiManager;
	
	private boolean stateSwitch;
	private double textX, textY;
	private float textWidth, textHeight;
	
	public HighScoreState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		
		stateSwitch = false;
		
		buttonWidth = (handler.getWidth()/15)*7;
		buttonHeight = handler.getHeight()/12;
		
		textWidth = handler.getWidth()/2.4f;
		textHeight = handler.getHeight()/7.68f;
		textX = (handler.getWidth()/2)-(textWidth/2);
		textY = (((handler.getHeight()/12)*7)-((handler.getHeight()/8)+buttonHeight)+(textHeight/2));
		
		handler.getMouseManager().setUIManager(uiManager);
		
		//reset button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/12)*7, buttonWidth, buttonHeight, Assets.buttonReset, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(uiManager);
				handler.getHiScoreManager().resetHiScore();
				handler.getHiScoreManager().setPreviousScore(0);
			}
		}));
		
		//back button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/24)*17, buttonWidth, buttonHeight, Assets.buttonBack, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Engine.settingsState.loadMouseManager();
				State.setState(Engine.settingsState);
			}
		}));
		
	}
	
	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
	public void update() {
		uiManager.update();
		
		if(handler.getKeyManager().enter && !stateSwitch) {
			stateSwitch = true;
		}
		
		if(stateSwitch) {
			if(!handler.getKeyManager().enter) {
				handler.getMouseManager().setUIManager(null);
				Engine.menuState.loadMouseManager();
				State.setState(Engine.menuState);
				stateSwitch = false;
			}
			
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
		
		g.drawImage(Assets.hiScorePlaque, handler.getWidth()/6, handler.getHeight()/8, (handler.getWidth()/3)*2, handler.getHeight()/8, null);
		g.setColor(Color.WHITE);
		g.setFont(handler.getFontManager().getLargeFont());
		g.drawString((handler.getHiScoreManager().getPreviousScore()/60) + ":" + String.format("%02d",(handler.getHiScoreManager().getPreviousScore()%60)), (int)textX, (int)textY);
		
		uiManager.render(g);
	}
	
}
