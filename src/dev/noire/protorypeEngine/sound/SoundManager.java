package dev.noire.protorypeEngine.sound;

public class SoundManager {

	private boolean isMuted;

	SoundObject ambienceSound;
	SoundObject menuSound;
	SoundObject jumpSound;
	SoundObject gameOverSound;

	private float soundVolume;
	private float musicVolume;
	private float ambienceVolume;
	private float menuVolume;
	private float jumpVolume;
	private float gameOverVolume;

	public SoundManager() {
		isMuted = false;

		musicVolume = 0;
		soundVolume = 0;

		ambienceVolume = 0 + musicVolume; // initialising this way leaves the option for balancing
		menuVolume = 0 + musicVolume; // initialising this way leaves the option for balancing
		jumpVolume = 0 + soundVolume; // initialising this way leaves the option for balancing
		gameOverVolume = 0 + soundVolume; // initialising this way leaves the option for balancing

		ambienceSound = new SoundObject("res/sound/Ambience.wav", true);
		menuSound = new SoundObject("res/sound/SoftRain.wav", true);
		jumpSound = new SoundObject("res/sound/Jump2.wav", false);
		gameOverSound = new SoundObject("res/sound/GameOver.wav", false);
	}

	public void update() {
	} // keep for the time being

	// play defined objects
	public void playGameOverSound() {
		if (!isMuted) {
			gameOverSound = new SoundObject("res/sound/GameOver.wav", false);
			gameOverSound.setValue(gameOverVolume);
			gameOverSound.play();
		}
	}

	public void playJumpSound() {
		if (!isMuted) {
			jumpSound = new SoundObject("res/sound/Jump2.wav", false);
			jumpSound.setValue(jumpVolume);
			jumpSound.play();
		}
	}

	public void playAmbienceMusic() {
		if (!isMuted) {
			ambienceSound.setValue(ambienceVolume);
			ambienceSound.play();
		}
	}

	public void stopAmbienceMusic() {
		if (!isMuted) {
			ambienceSound.stop();
		}
	}

	public void playMenuMusic() {
		if (!isMuted) {
			menuSound.setValue(menuVolume);
			menuSound.play();
		}
	}

	public void stopMenuMusic() {
		if (!isMuted) {
			menuSound.stop();
		}
	}

	// volume control
	public void increaseSound(float modifier) {
		jumpVolume = jumpVolume + modifier;
		gameOverVolume = gameOverVolume + modifier;
	}

	public void decreaseSound(float modifier) {
		jumpVolume = jumpVolume - modifier;
		gameOverVolume = gameOverVolume - modifier;
	}

	public void increaseMusic(float modifier) {
		ambienceSound.increaseVolume(modifier);
		menuSound.increaseVolume(modifier);
	}

	public void decreaseMusic(float modifier) {
		ambienceSound.decreaseVolume(modifier);
		menuSound.decreaseVolume(modifier);
	}

	// all over mute function
	public void mute() {
		if (!isMuted) {
			isMuted = true;
			ambienceSound.stop();
			menuSound.stop();
		}
	}

	public void unMute() {
		if (isMuted)
			isMuted = false;
	}

	public boolean isMuted() {
		return isMuted;
	}

	public void setMuted(boolean isMuted) {
		this.isMuted = isMuted;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

}
