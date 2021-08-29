package dev.noire.protorypeEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.gfx.Assets;

public class StartUpState extends State {

	private int count;
	private long timer, lastTime;
	private String[] messages;
	private String message;

	private double scrollX, leftBorder, scrollSpeed;

	public static final int DISPLAY_DURATION_MS = 2000;
	public static final int DURATION_LENGTH = 2;

	public StartUpState(Handler handler) {
		super(handler);

		// timer
		count = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();

		// scrolling message
		scrollX = handler.getHeight();
		leftBorder = (handler.getWidth() / 30) * 2;
		scrollSpeed = 15;
		initMessages();
		message = getMessage();

	}

	public void update() {

		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > DISPLAY_DURATION_MS) {
			timer = 0;
			count++;
		}

		if (count == DURATION_LENGTH) {
			handler.getSoundManager().playMenuMusic();
			State.setState(Engine.menuState);
		}

		if (scrollX > leftBorder)
			scrollX -= scrollSpeed;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.splashScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.setColor(Color.GRAY);
		g.setFont(handler.getFontManager().getSmallFont());
		g.drawString("Tip:", (int) leftBorder, (handler.getHeight() / 8) * 5);
		g.drawString(message, (int) scrollX, (handler.getHeight() / 16) * 11);

	}

	public void loadMouseManager() {
	}

	private String getMessage() {
		int r = (int) (Math.random() * messages.length);
		return messages[r];

	}

	private void initMessages() {
		String message0 = " a & d => left/right";
		String message1 = " use 'w' to jump";
		String message2 = " use 'm' to mute audio";
		String message3 = " p pauses the game";
		String message4 = " continue with 'enter'";
		String message5 = " 2xenter => game start";

		messages = new String[6];
		messages[0] = message0;
		messages[1] = message1;
		messages[2] = message2;
		messages[3] = message3;
		messages[4] = message4;
		messages[5] = message5;
	}
}
