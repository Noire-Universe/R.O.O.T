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

public class SoundState extends State {

	private int buttonWidth, buttonHeight;
	private UIManager uiManager;

	private BufferedImage[] buttonTexture;

	private boolean stateSwitch;

	public SoundState(Handler handler) {
		super(handler);

		stateSwitch = false;
		uiManager = new UIManager(handler);
		buttonWidth = (handler.getWidth() / 15) * 7;
		buttonHeight = handler.getHeight() / 12;
		handler.getMouseManager().setUIManager(uiManager);
		createTexture();

		// button music plus (right side button)
		uiManager.addObject(new UIImageButton((handler.getWidth() / 10) * 7, (handler.getHeight() / 48) * 25,
				(handler.getWidth() / 15), (handler.getHeight() / 24), Assets.buttonPlus, new ClickListener() {
					@Override
					public void onClick() {
						handler.getSoundManager().increaseMusic(1.0f);
					}
				}));

		// button music minus (leftSide button)
		uiManager.addObject(new UIImageButton((handler.getWidth() / 15) * 8, (handler.getHeight() / 48) * 25,
				(handler.getWidth() / 15), (handler.getHeight() / 24), Assets.buttonMinus, new ClickListener() {
					@Override
					public void onClick() {
						handler.getSoundManager().decreaseMusic(1.0f);
					}
				}));

		// button sound plus (right side button)
		uiManager.addObject(new UIImageButton((handler.getWidth() / 10) * 7, (handler.getHeight() / 12) * 7,
				(handler.getWidth() / 15), (handler.getHeight() / 24), Assets.buttonPlus, new ClickListener() {
					@Override
					public void onClick() {
						handler.getSoundManager().increaseSound(1.0f);
						handler.getSoundManager().playJumpSound();
					}
				}));

		// button sound minus (left side button)
		uiManager.addObject(new UIImageButton((handler.getWidth() / 15) * 8, (handler.getHeight() / 12) * 7,
				(handler.getWidth() / 15), (handler.getHeight() / 24), Assets.buttonMinus, new ClickListener() {
					@Override
					public void onClick() {
						handler.getSoundManager().decreaseSound(1.0f);
						handler.getSoundManager().playJumpSound();
					}
				}));

		// backButton:
		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 24) * 17, buttonWidth, buttonHeight, Assets.buttonBack, new ClickListener() {
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

		if (handler.getKeyManager().enter && !stateSwitch) {
			stateSwitch = true;
		}
		if (stateSwitch) {
			if (!handler.getKeyManager().enter) {
				handler.getMouseManager().setUIManager(null);
				Engine.menuState.loadMouseManager();
				State.setState(Engine.menuState);
				stateSwitch = false;
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.soundPlaque, handler.getWidth() / 6, handler.getHeight() / 8, (handler.getWidth() / 3) * 2,
				handler.getHeight() / 8, null);
		uiManager.render(g); // needs to be rendered first!!!

		g.setColor(Color.GRAY);
		g.setFont(handler.getFontManager().getSmallFont());
		g.drawString("'m' toggles mute", (handler.getWidth() / 2) - ((handler.getWidth() / 3) * 2) / 2,
				(handler.getHeight() / 48) * 17);

		BufferedImage buttonPicture;
		if (handler.getSoundManager().isMuted()) {
			buttonPicture = Assets.buttonUnMute[0];
		} else
			buttonPicture = Assets.buttonMute[0];
		g.drawImage(buttonPicture, (handler.getWidth() / 2) - (buttonWidth / 2), (handler.getHeight() / 48) * 19,
				buttonWidth, buttonHeight, null);

		g.drawString("Music:", handler.getWidth() / 10, (handler.getHeight() / 16) * 9);
		g.drawString("Sound:", handler.getWidth() / 10, (handler.getHeight() / 16) * 10);

	}

	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

	private void createTexture() {
		if (handler.getSoundManager().isMuted())
			buttonTexture = Assets.buttonUnMute;
		else
			buttonTexture = Assets.buttonMute;

		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 48) * 19, buttonWidth, buttonHeight, buttonTexture, new ClickListener() {
					@Override
					public void onClick() {
						if (handler.getSoundManager().isMuted()) {
							handler.getSoundManager().unMute();
							if (State.getState() == Engine.gameState)
								handler.getSoundManager().playAmbienceMusic();
							else if (State.getState() != Engine.gameState && State.getState() != Engine.startUpState)
								handler.getSoundManager().playMenuMusic();
							else
								System.out.println("some music to be played here...");
						} else
							handler.getSoundManager().mute();
					}
				}));
	}
}
