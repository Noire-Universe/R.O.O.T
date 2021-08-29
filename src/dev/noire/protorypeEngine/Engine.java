package dev.noire.protorypeEngine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.noire.protorypeEngine.display.Display;
import dev.noire.protorypeEngine.gfx.Assets;
import dev.noire.protorypeEngine.input.KeyManager;
import dev.noire.protorypeEngine.input.MouseManager;
import dev.noire.protorypeEngine.sound.SoundManager;
import dev.noire.protorypeEngine.states.GameOverState;
import dev.noire.protorypeEngine.states.HighScoreState;
import dev.noire.protorypeEngine.states.InfoState;
import dev.noire.protorypeEngine.states.MenuState;
import dev.noire.protorypeEngine.states.PausedState;
import dev.noire.protorypeEngine.states.SettingsState;
import dev.noire.protorypeEngine.states.SoundState;
import dev.noire.protorypeEngine.states.StartUpState;
import dev.noire.protorypeEngine.states.State;
import dev.noire.protorypeEngine.states.VictoryState;
import dev.noire.protorypeEngine.utils.FontManager;
import dev.noire.protorypeEngine.utils.HiScoreManager;
import dev.noire.protorypeEngine.utils.TimeManager;

public class Engine implements Runnable {

	// GAME ENGINE:
	// fields:
	private Display display; // display object

	private String title;
	private int width, height;

	private BufferStrategy bs;
	private Graphics g;

	private boolean running = false; // <-needs to be false, otherwise the start method won't trigger it
	private Thread thread; // thread to run the game on

	private boolean muteControl;

	// input:
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// states:
	public static State menuState;
	public static State gameState;
	public static State pausedState;
	public static State gameOverState;
	public static State infoState;
	public static State victoryState;
	public static State startUpState;
	public static State settingsState;
	public static State soundState;
	public static State highScoreState;

	// utilities:
	private TimeManager timeManager;
	private HiScoreManager hiScoreManager;
	private FontManager fontManager;
	private SoundManager soundManager;

	// handler:
	private Handler handler;

	// -->Constructor<--
	public Engine(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		muteControl = false;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		timeManager = new TimeManager();
		soundManager = new SoundManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);

		handler = new Handler(this);

		hiScoreManager = new HiScoreManager(handler);
		fontManager = new FontManager(handler);

		Assets.init();

		pausedState = new PausedState(handler);
		gameOverState = new GameOverState(handler);
		infoState = new InfoState(handler);
		victoryState = new VictoryState(handler);
		highScoreState = new HighScoreState(handler);
		settingsState = new SettingsState(handler);
		soundState = new SoundState(handler);
		menuState = new MenuState(handler);
		startUpState = new StartUpState(handler);
		State.setState(startUpState);
	}

	// game loop:
	public void update() {

		keyManager.update();
		timeManager.update();
		hiScoreManager.update();
		soundManager.update();

		if (State.getState() != null)
			State.getState().update();

		// mute <-> un-mute:
		if (handler.getKeyManager().mute && !muteControl) {
			muteControl = true;
		}
		if (muteControl) {
			if (!handler.getKeyManager().mute) {
				if (soundManager.isMuted()) {
					soundManager.unMute();
					if (State.getState() == gameState)
						soundManager.playAmbienceMusic();
					else if (State.getState() != Engine.gameState && State.getState() != Engine.startUpState)
						soundManager.playMenuMusic();
					else
						System.out.println("some music to be played here...");
				} else
					soundManager.mute();

				muteControl = false;
			}
		}

		if (keyManager.quit) {
			if (State.getState() == menuState)
				soundManager.stopMenuMusic();
			else
				soundManager.stopAmbienceMusic();
			System.exit(0);
		}

	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);

		if (State.getState() != null)
			State.getState().render(g);

		bs.show();
		g.dispose();
	}

	public void run() {
		init();

		double timer = 0;
		int fps = 60;
		double framesPerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / framesPerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				delta--;
			}

			if (timer >= 1000000000) {
				timer = 0;
			}
		}

		stop();

	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// GETTERS & SETTERS:
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public TimeManager getTimeManager() {
		return timeManager;
	}

	public HiScoreManager getHiScoreManager() {
		return hiScoreManager;
	}

	public FontManager getFontManager() {
		return fontManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}
}
