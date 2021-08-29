package dev.noire.protorypeEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.UI.ClickListener;
import dev.noire.protorypeEngine.UI.UIImageButton;
import dev.noire.protorypeEngine.UI.UIManager;
import dev.noire.protorypeEngine.gfx.Assets;

public class GameOverState extends State {

	private UIManager uiManager;
	private int buttonWidth;
	private int buttonHeight;

	private boolean stateSwitch;

	private float textLineOneWidth;
	private float textLineTwoWidth;
	private float textBlockHeight;
	private double textLineOneX;
	private double textLineTwoX;
	private double textBlockY;

	public GameOverState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);

		buttonWidth = (handler.getWidth() / 15) * 7;
		buttonHeight = handler.getHeight() / 12;

		textLineOneWidth = handler.getWidth() / 1.6f;
		textLineTwoWidth = handler.getWidth() / 2.181f;
		textBlockHeight = handler.getHeight() / 5.33f;
		textLineOneX = handler.getWidth() / 2 - (textLineOneWidth / 2);
		textLineTwoX = handler.getWidth() / 2 - (textLineTwoWidth / 2);
		textBlockY = handler.getHeight() / 2 - (textBlockHeight / 2);

		// switch the state:
		stateSwitch = false;

		// menu button
		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 48) * 31, buttonWidth, buttonHeight, Assets.buttonMenu, new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null);
						Engine.menuState.loadMouseManager();
						State.setState(Engine.menuState);
					}
				}));

		// exit button
		uiManager.addObject(new UIImageButton((handler.getWidth() / 2) - (buttonWidth / 2),
				(handler.getHeight() / 48) * 37, buttonWidth, buttonHeight, Assets.buttonQuit, new ClickListener() {
					@Override
					public void onClick() {
						handler.getMouseManager().setUIManager(null);
						handler.getSoundManager().stopMenuMusic();
						System.exit(0);
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
		uiManager.render(g);

		// migriate locations to Victory State!!!!

		g.drawImage(Assets.gameOverPlaque, handler.getWidth() / 6, handler.getHeight() / 8,
				(handler.getWidth() / 3) * 2, handler.getHeight() / 8, null);

		g.setColor(Color.WHITE);
		g.setFont(handler.getFontManager().getSmallFont());

		// if current > previous:
		if (handler.getHiScoreManager().getCurrentScore() >= handler.getHiScoreManager().getPreviousScore())
			g.drawString("NEW HIGH SCORE!", (int) textLineOneX,
					(int) textBlockY + (int) (1 * handler.getFontManager().getSmallRatio()));

		g.drawString(
				"Score: " + (handler.getHiScoreManager().getCurrentScore() / 60) + ":"
						+ String.format("%02d", (handler.getHiScoreManager().getCurrentScore() % 60)),
				(int) textLineTwoX, (int) textBlockY + (int) (4 * handler.getFontManager().getSmallRatio()));

		g.setFont(handler.getFontManager().getQuoteFont());
		g.drawString("Thank you for playing my game!", 25, handler.getHeight() - 15);
	}

	public void loadMouseManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

}
