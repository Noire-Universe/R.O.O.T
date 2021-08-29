package dev.noire.protorypeEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class InfoState extends State {

	private int buttonWidth, buttonHeight;
	private UIManager uiManager;
	
	private boolean stateSwitch;
	
	//text block:
	private double blockX, blockY;
	private float blockWidth, blockHeight;
	
	public InfoState(Handler handler) {
		super(handler);
		
		stateSwitch = false;
		uiManager = new UIManager(handler);
		buttonWidth = (handler.getWidth()/15)*7;
		buttonHeight = handler.getHeight()/12;
		handler.getMouseManager().setUIManager(uiManager);
		
		//text block:
		blockWidth = handler.getWidth()/1.6f;
		blockHeight = handler.getHeight()/5.33f;
		blockX = handler.getWidth()/2 -(blockWidth/2);
		blockY = handler.getHeight()/2-(blockHeight/2);
		
		uiManager.addObject(new UIImageButton((handler.getWidth()/2)-(buttonWidth/2), (handler.getHeight()/24)*17, buttonWidth, buttonHeight, Assets.buttonBack, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Engine.settingsState.loadMouseManager();
				State.setState(Engine.settingsState);
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
		g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
		
		g.drawImage(Assets.infoPlaque, handler.getWidth()/6, handler.getHeight()/8, (handler.getWidth()/3)*2, handler.getHeight()/8, null);
		g.setColor(Color.GRAY);
		g.setFont(handler.getFontManager().getSmallFont());
		g.drawString("CONTROLS:", (int)blockX + 1, (int)blockY + (int)(1*handler.getFontManager().getSmallRatio()));
		g.drawString("<- / ->: a / d", (int)blockX + 1, (int)blockY + (int)(2*handler.getFontManager().getSmallRatio()));
		g.drawString("jump:    w", (int)blockX + 1, (int)blockY + (int)(3*handler.getFontManager().getSmallRatio()));
		g.drawString("pause:   p", (int)blockX + 1, (int)blockY + (int)(4*handler.getFontManager().getSmallRatio()));
		g.drawString("continue:enter", (int)blockX + 1, (int)blockY + (int)(5*handler.getFontManager().getSmallRatio()));
		g.drawString("start:   enter", (int)blockX + 1, (int)blockY + (int)(6*handler.getFontManager().getSmallRatio()));
		g.drawString("quit:    escape", (int)blockX + 1, (int)blockY + (int)(7*handler.getFontManager().getSmallRatio()));
		g.drawString("mute:    m", (int)blockX + 1, (int)blockY + (int)(8*handler.getFontManager().getSmallRatio()));
		
		uiManager.render(g);
	}
	
	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
}
