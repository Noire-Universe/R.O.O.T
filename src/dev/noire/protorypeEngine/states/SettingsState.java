package dev.noire.protorypeEngine.states;

import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class SettingsState extends State {

	private int buttonWidth, buttonHeight;
	private UIManager uiManager;
	
	private boolean stateSwitch;
	
	public SettingsState(Handler handler) {
		super(handler);
		
		stateSwitch = false;
		uiManager = new UIManager(handler);
		buttonWidth = (handler.getWidth()/15)*7;
		buttonHeight = handler.getHeight()/12;
		handler.getMouseManager().setUIManager(uiManager);
		
		//buttons:
		//Hi-score button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/16)*7, buttonWidth, buttonHeight, Assets.buttonHiScore, new ClickListener() {
			@Override
			public void onClick() {
				handler.getHiScoreManager().loadHighScore();
				handler.getMouseManager().setUIManager(null);
				Engine.highScoreState.loadMouseManager();
				State.setState(Engine.highScoreState);
			}
		}));
		
		//Sound button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/12)*7, buttonWidth, buttonHeight, Assets.buttonSound, new ClickListener() {
			@Override
			public void onClick() {
				handler.getHiScoreManager().loadHighScore();
				handler.getMouseManager().setUIManager(null);
				
				Engine.soundState.loadMouseManager();
				State.setState(Engine.soundState);
			}
		}));
		
		//info button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/24)*17, buttonWidth, buttonHeight, Assets.buttonInfo, new ClickListener() {
			@Override
			public void onClick() {
				handler.getHiScoreManager().loadHighScore();
				handler.getMouseManager().setUIManager(null);
				Engine.infoState.loadMouseManager();
				State.setState(Engine.infoState);
			}
		}));
		
		//back button:
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/6)*5, buttonWidth, buttonHeight, Assets.buttonBack, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Engine.menuState.loadMouseManager();
				State.setState(Engine.menuState);
			}
		}));
		
		
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
		
		//label
		g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.settingsPlaque, handler.getWidth()/6, handler.getHeight()/8, (handler.getWidth()/3)*2, handler.getHeight()/8, null);
		
		uiManager.render(g);
		
	}
	
	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
}
