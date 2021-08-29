package dev.noire.protorypeEngine.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 16, height = 16;

	// player:
	public static BufferedImage[] playerIdle;
	public static BufferedImage[] playerJumpUp;
	public static BufferedImage[] playerJumpLeft;
	public static BufferedImage[] playerJumpRight;

	// background objects:
	public static BufferedImage[] backgroundObjects;

	// hi-score indicator:
	public static BufferedImage[] indicatorGraphics;

	// buttons:
	public static BufferedImage[] buttonStart;
	public static BufferedImage[] buttonQuit;
	public static BufferedImage[] buttonHiScore;
	public static BufferedImage[] buttonContinue;
	public static BufferedImage[] buttonBack;
	public static BufferedImage[] buttonReset;
	public static BufferedImage[] buttonInfo;
	public static BufferedImage[] buttonMenu;
	public static BufferedImage[] buttonSettings;
	public static BufferedImage[] buttonSound;
	public static BufferedImage[] buttonMute;
	public static BufferedImage[] buttonUnMute;
	public static BufferedImage[] buttonPlus;
	public static BufferedImage[] buttonMinus;

	// platforms:
	public static BufferedImage[] platformGraphics;

	// labels:
	public static BufferedImage gameOverPlaque;
	public static BufferedImage hiScorePlaque;
	public static BufferedImage pausedPlaque;
	public static BufferedImage victoryPlaque;
	public static BufferedImage infoPlaque;
	public static BufferedImage menuPlaque;
	public static BufferedImage settingsPlaque;
	public static BufferedImage soundPlaque;

	// background:
	public static BufferedImage background;

	// splash screen:
	public static BufferedImage splashScreen;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/textures.png"));
		SpriteSheet backgroundSheet = new SpriteSheet(ImageLoader.loadImage("/textures/background.png"));
		SpriteSheet splashSheet = new SpriteSheet(ImageLoader.loadImage("/textures/SplashScreen.png"));

		// player:
		playerIdle = new BufferedImage[4];
		playerIdle[0] = sheet.crop(0, height, width, height);
		playerIdle[1] = sheet.crop(width, height, width, height);
		playerIdle[2] = sheet.crop(width * 2, height, width, height);
		playerIdle[3] = sheet.crop(width * 3, height, width, height);

		playerJumpUp = new BufferedImage[6];
		playerJumpUp[0] = sheet.crop(0, height * 2, width, height);
		playerJumpUp[1] = sheet.crop(width, height * 2, width, height);
		playerJumpUp[2] = sheet.crop(width * 2, height * 2, width, height);
		playerJumpUp[3] = sheet.crop(width * 3, height * 2, width, height);
		playerJumpUp[4] = sheet.crop(width * 4, height * 2, width, height);
		playerJumpUp[5] = sheet.crop(width * 5, height * 2, width, height);

		playerJumpLeft = new BufferedImage[4];
		playerJumpLeft[0] = sheet.crop(0, 0, width, height);
		playerJumpLeft[1] = sheet.crop(width * 3, 0, width, height);
		playerJumpLeft[2] = sheet.crop(width * 2, 0, width, height);
		playerJumpLeft[3] = sheet.crop(width, 0, width, height);

		playerJumpRight = new BufferedImage[4];
		playerJumpRight[0] = sheet.crop(0, 0, width, height);
		playerJumpRight[1] = sheet.crop(width, 0, width, height);
		playerJumpRight[2] = sheet.crop(width * 2, 0, width, height);
		playerJumpRight[3] = sheet.crop(width * 3, 0, width, height);

		// background objects:
		backgroundObjects = new BufferedImage[10];
		backgroundObjects[0] = sheet.crop(0, height * 3, width, height);
		backgroundObjects[1] = sheet.crop(width, height * 3, width, height);
		backgroundObjects[2] = sheet.crop(width * 2, height * 3, width, height);
		backgroundObjects[3] = sheet.crop(width * 3, height * 3, width, height);
		backgroundObjects[4] = sheet.crop(width * 4, height * 3, width, height);
		backgroundObjects[5] = sheet.crop(0, height * 4, width, height);
		backgroundObjects[6] = sheet.crop(width, height * 4, width, height);
		backgroundObjects[7] = sheet.crop(width * 2, height * 4, width, height);
		backgroundObjects[8] = sheet.crop(width * 3, height * 4, width, height);
		backgroundObjects[9] = sheet.crop(width * 4, height * 4, width, height);

		// buttons:
		buttonStart = new BufferedImage[2];
		buttonStart[0] = sheet.crop(0, height * 9, width * 14, height * 4);
		buttonStart[1] = sheet.crop(width * 14, height * 9, width * 14, height * 4);
		buttonQuit = new BufferedImage[2]; // <--Quit button
		buttonQuit[0] = sheet.crop(0, height * 17, width * 14, height * 4);
		buttonQuit[1] = sheet.crop(width * 14, height * 17, width * 14, height * 4);
		buttonHiScore = new BufferedImage[2]; // <--HiScore button
		buttonHiScore[0] = sheet.crop(0, height * 13, width * 14, height * 4);
		buttonHiScore[1] = sheet.crop(width * 14, height * 13, width * 14, height * 4);
		buttonContinue = new BufferedImage[2]; // <--Continue button
		buttonContinue[0] = sheet.crop(0, height * 5, width * 14, height * 4);
		buttonContinue[1] = sheet.crop(width * 14, height * 5, width * 14, height * 4);
		buttonBack = new BufferedImage[2]; // <-- Back button
		buttonBack[0] = sheet.crop(0, height * 21, width * 14, height * 4);
		buttonBack[1] = sheet.crop(width * 14, height * 21, width * 14, height * 4);
		buttonReset = new BufferedImage[2]; // <-- Reset button
		buttonReset[0] = sheet.crop(0, height * 25, width * 14, height * 4);
		buttonReset[1] = sheet.crop(width * 14, height * 25, width * 14, height * 4);
		buttonMenu = new BufferedImage[2]; // <-- Menu button
		buttonMenu[0] = sheet.crop(0, height * 29, width * 14, height * 4);
		buttonMenu[1] = sheet.crop(width * 14, height * 29, width * 14, height * 4);
		buttonInfo = new BufferedImage[2]; // <-- Info button
		buttonInfo[0] = sheet.crop(0, 768, width * 14, height * 4);
		buttonInfo[1] = sheet.crop(width * 14, 768, width * 14, height * 4);
		buttonSettings = new BufferedImage[2]; // <-- Settings button
		buttonSettings[0] = sheet.crop(0, 832, width * 14, height * 4);
		buttonSettings[1] = sheet.crop(width * 14, 832, width * 14, height * 4);
		buttonSound = new BufferedImage[2]; // <-- Sound button
		buttonSound[0] = sheet.crop(0, 896, width * 14, height * 4);
		buttonSound[1] = sheet.crop(width * 14, 896, width * 14, height * 4);
		buttonMute = new BufferedImage[2]; // <-- mute button
		buttonMute[0] = sheet.crop(0, 960, width * 14, height * 4);
		buttonMute[1] = sheet.crop(width * 14, 960, width * 14, height * 4);
		buttonUnMute = new BufferedImage[2]; // <-- un-mute button
		buttonUnMute[0] = sheet.crop(0, 1024, width * 14, height * 4);
		buttonUnMute[1] = sheet.crop(width * 14, 1024, width * 14, height * 4);
		buttonPlus = new BufferedImage[2]; // <-- plus button
		buttonPlus[0] = sheet.crop(width * 14, 0, width * 4, height * 4);
		buttonPlus[1] = sheet.crop(width * 18, 0, width * 4, height * 4);
		buttonMinus = new BufferedImage[2]; // <-- minus button
		buttonMinus[0] = sheet.crop(width * 6, 0, width * 4, height * 4);
		buttonMinus[1] = sheet.crop(width * 10, 0, width * 4, height * 4);

		indicatorGraphics = new BufferedImage[16];
		indicatorGraphics[0] = sheet.crop(0, 528, 80, 80);
		indicatorGraphics[1] = sheet.crop(80, 528, 80, 80);
		indicatorGraphics[2] = sheet.crop(160, 528, 80, 80);
		indicatorGraphics[3] = sheet.crop(240, 528, 80, 80);
		indicatorGraphics[4] = sheet.crop(320, 528, 80, 80);
		indicatorGraphics[5] = sheet.crop(400, 528, 80, 80); // <-- end of line one
		indicatorGraphics[6] = sheet.crop(0, 608, 80, 80);
		indicatorGraphics[7] = sheet.crop(80, 608, 80, 80);
		indicatorGraphics[8] = sheet.crop(160, 608, 80, 80);
		indicatorGraphics[9] = sheet.crop(240, 608, 80, 80);
		indicatorGraphics[10] = sheet.crop(320, 608, 80, 80);
		indicatorGraphics[11] = sheet.crop(400, 608, 80, 80); // <-- end of line two
		indicatorGraphics[12] = sheet.crop(0, 688, 80, 80);
		indicatorGraphics[13] = sheet.crop(80, 688, 80, 80);
		indicatorGraphics[14] = sheet.crop(160, 688, 80, 80);
		indicatorGraphics[15] = sheet.crop(240, 688, 80, 80);

		// platforms:
		platformGraphics = new BufferedImage[2];
		platformGraphics[0] = sheet.crop(width * 4, 0, width, height);
		platformGraphics[1] = sheet.crop(width * 5, 0, width, height);

		// labels:
		gameOverPlaque = sheet.crop(480, 0 * (8 * height), 26 * width, 8 * height);
		hiScorePlaque = sheet.crop(480, 1 * (8 * height), 26 * width, 8 * height);
		pausedPlaque = sheet.crop(480, 2 * (8 * height), 26 * width, 8 * height);
		victoryPlaque = sheet.crop(480, 3 * (8 * height), 26 * width, 8 * height);
		menuPlaque = sheet.crop(480, 4 * (8 * height), 26 * width, 8 * height);
		infoPlaque = sheet.crop(480, 5 * (8 * height), 26 * width, 8 * height);
		settingsPlaque = sheet.crop(480, 6 * (8 * height), 26 * width, 8 * height);
		soundPlaque = sheet.crop(480, 7 * (8 * height), 26 * width, 8 * height);

		// background;
		background = backgroundSheet.crop(0, 0, 400, 800);

		// splash screen:
		splashScreen = splashSheet.crop(0, 0, 480, 768);
	}

}
