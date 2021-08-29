package dev.noire.protorypeEngine;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {

	// base values
	public static final String TITLE = "R.O.O.T beta 2.0d";
	private static int gameWidth = 480;
	private static int gameHeight = 768;
	public static final int TILESIZE = 16;

	private static double screenWidth;
	private static double screenHeight;
	private static double gameToScreenRatioWidth = 3.333;
	private static double gameToScreenRatioHeight = 1.171;

	public static void main(String[] args) {

		// handle different screenSizes:
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screenSize.getWidth();
		screenHeight = screenSize.getHeight();

		// test lines for different resolutions:
		// screenWidth = 640; //test lines
		// screenHeight = 480; //test lines

		// screenWidth = 800; //test lines
		// screenHeight = 600; //test lines

		// screenWidth = 960; //test lines
		// screenHeight = 720; //test lines

		// screenWidth = 1024; //test lines
		// screenHeight = 768; //test lines

		// screenWidth = 1280; //test lines
		// screenHeight = 960; //test lines

		gameWidth = (int) (screenWidth / gameToScreenRatioWidth);
		gameHeight = (int) (screenHeight / gameToScreenRatioHeight);

		Engine engine = new Engine(TITLE, gameWidth, gameHeight);
		engine.start();

	}

	/*
	 * DOCUMENTATION:
	 * 
	 * Patch Notes: - soundObect class has been added to the project - soundManager
	 * class has been added to the project - the menuState has been updated to
	 * accommodate the new menu flow - the settingsState has been added and the menu
	 * flow has been adjusted back to maintain the functionality it had before the
	 * changes. - a splashScreen state has been added to the project (although its
	 * not doing anything just yet) - the soundState has been added to the menu flow
	 * (implementation of functionality pending...) - soundObject has been updated
	 * to support looping capability - Mute and Un-Mute functionality has been added
	 * for both, 'm' key and button in the SoundState - the issue with mute/un-mute
	 * button was not working as intended has been resolved - functionality for the
	 * splash screen message moving into the screen has been added - hitting enter
	 * twice from any sub-menu will now start the game - buttons for the volume
	 * functionality have been added to the soundState - the texture for the volume
	 * buttons has been added to the project - the volume control functionality has
	 * been added to the buttons on the SoundState - splash screen graphics have
	 * been added to the project - volume control buttons have been put into
	 * relative positions - starting SplashScreen handler has been taken to the
	 * engine constructor (which should make for quicker loading) - the elements on
	 * the SoundState have been made relative, so that they scale to different
	 * screen resolutions - all additional elements have been put into relative
	 * positions - added some additional functionality that the sound really stops
	 * when exiting the game
	 * 
	 * Known Issues: - folder structure required (folder structure still required,
	 * but reduced) - high score state font... under review for beta 2.0 - There are
	 * still situation where the font and the high-score are not loading as intended
	 * (investigate!) - sound and music files are provisional and will be replaced
	 * at a later stage
	 * 
	 * Notes: - Tasks for beta 2.1: - fileManager to save sound settings - this WILL
	 * have to make its own directory in the APPDATA folder!
	 * 
	 * To do list:
	 */

}
