package dev.noire.protorypeEngine.states;

import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class MenuState extends State {

	private int buttonWidth;
	private int buttonHeight;
	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);

		buttonWidth = (handler.getWidth() / 15) * 7;
		buttonHeight = handler.getHeight() / 12;

		handler.getMouseManager().setUIManager(uiManager);

		// start button:
		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 16) * 7, buttonWidth, buttonHeight, Assets.buttonStart, new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null);
						handler.getTimeManager().restart();
						Engine.gameState = new GameState(handler);
						handler.getHiScoreManager().loadHighScore();
						handler.getSoundManager().stopMenuMusic();
						State.setState(Engine.gameState);

					}
				}));

		// settings button
		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 12) * 7, buttonWidth, buttonHeight, Assets.buttonSettings, new ClickListener() {
					@Override
					public void onClick() {
						handler.getHiScoreManager().loadHighScore();
						handler.getMouseManager().setUIManager(null);
						Engine.settingsState.loadMouseManager();
						State.setState(Engine.settingsState);
					}
				}));

		// quit button:
		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 48) * 35, buttonWidth, buttonHeight, Assets.buttonQuit, new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null);
						handler.getSoundManager().stopMenuMusic();
						System.exit(0);
					}
				}));

	}

	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

	public void update() {

		uiManager.update();

		if (handler.getKeyManager().enter) {
			handler.getMouseManager().setUIManager(null);
			handler.getTimeManager().restart();
			Engine.gameState = new GameState(handler);
			handler.getHiScoreManager().loadHighScore();
			handler.getSoundManager().stopMenuMusic();
			State.setState(Engine.gameState);
		}

	}

	public void render(Graphics g) {
		g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.menuPlaque, handler.getWidth() / 6, handler.getHeight() / 8, (handler.getWidth() / 3) * 2,
				handler.getHeight() / 8, null);
		uiManager.render(g);

	}

}
