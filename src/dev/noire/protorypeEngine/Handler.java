package dev.noire.protorypeEngine;

import dev.noire.protorypeEngine.input.KeyManager;
import dev.noire.protorypeEngine.input.MouseManager;
import dev.noire.protorypeEngine.sound.SoundManager;
import dev.noire.protorypeEngine.utils.FontManager;
import dev.noire.protorypeEngine.utils.HiScoreManager;
import dev.noire.protorypeEngine.utils.TimeManager;

public class Handler {

	private Engine engine;

	public Handler(Engine engine) {
		this.engine = engine;

	}

	// input
	public KeyManager getKeyManager() {
		return engine.getKeyManager();
	}

	public MouseManager getMouseManager() {
		return engine.getMouseManager();
	}

	// utilities
	public TimeManager getTimeManager() {
		return engine.getTimeManager();
	}

	public HiScoreManager getHiScoreManager() {
		return engine.getHiScoreManager();
	}

	public FontManager getFontManager() {
		return engine.getFontManager();
	}

	public SoundManager getSoundManager() {
		return engine.getSoundManager();
	}

	// misc
	public int getWidth() {
		return engine.getWidth();
	}

	public int getHeight() {
		return engine.getHeight();
	}

	// GETTERS & SETTERS:
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

}
