package dev.noire.protorypeEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.protorypeEngine.Engine;
import dev.noire.protorypeEngine.Handler;
import dev.noire.protorypeEngine.Hud.Hud;
import dev.noire.protorypeEngine.entities.BackgroundManager;
import dev.noire.protorypeEngine.entities.PlatformManager;
import dev.noire.protorypeEngine.entities.Player;

public class GameState extends State {

	private PlatformManager platformManager;
	private Player player;
	private Hud hud;

	private BackgroundManager backgroundManager;

	// game play timer:
	private long gamePlayTimer;
	private long lastTime;

	public GameState(Handler handler) {
		super(handler);

		hud = new Hud(handler);
		platformManager = new PlatformManager(handler, 1, hud);

		player = new Player(handler, (int) handler.getWidth() / 4.8f, -1); // -1 causes the player to spawn on top of
																			// the first platform

		handler.getTimeManager().start();

		backgroundManager = new BackgroundManager(handler);

		gamePlayTimer = 0;
		lastTime = System.currentTimeMillis();

		handler.getSoundManager().playAmbienceMusic();
	}

	public void update() {
		checkCollisions();

		platformManager.update();
		player.update();

		// gamePlayTimer:
		gamePlayTimer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (gamePlayTimer > 1000) {
			gamePlayTimer = 0;
			gamePlayUpdate();
		}

		// game over:
		if (player.getY() > handler.getHeight() || hud.getTimeWasted() > hud.getTimeGathered()) {
			handler.getSoundManager().playGameOverSound();
			handler.getHiScoreManager().saveScore(handler.getTimeManager().getTimeToSave());
			if (handler.getTimeManager().getTimeToSave() > 500) {
				Engine.victoryState.loadMouseManager();
				handler.getSoundManager().stopAmbienceMusic();
				handler.getSoundManager().playMenuMusic();
				State.setState(Engine.victoryState);

			} else {
				Engine.gameOverState.loadMouseManager();
				handler.getSoundManager().stopAmbienceMusic();
				handler.getSoundManager().playMenuMusic();
				State.setState(Engine.gameOverState);
			}
		}

		hud.update();

		if (handler.getKeyManager().pause) {
			handler.getTimeManager().stop();
			Engine.pausedState.loadMouseManager();
			State.setState(Engine.pausedState);
		}

		backgroundManager.update();
	}

	private void gamePlayUpdate() {
		if (handler.getTimeManager().getTimeToSave() % 7 == 0) {
			// platform speed
			if (platformManager.getSpeed() <= 3.0f) // cap
				platformManager.setSpeed(platformManager.getSpeed() + (1.5f / 71.0f)); // about 0.02112.... interval
																						// step
			// start value 1.5f, cap speed 3.0f -> 1.5 steps. 497secs (target) / 7secs
			// (interval) = 71 iterations
		}
		if (handler.getTimeManager().getTimeToSave() % 11 == 0) {
			// platform spawn
			if (platformManager.getInterval() >= 250)
				; // cap
			platformManager.setInterval(platformManager.getInterval() - (750.0f / 45.0f));
			// start interval 1750, cap interval = 1000 -> 750 steps. 496secs (target) / 11
			// seconds(interval = 45 iterations)
		}
		if (handler.getTimeManager().getTimeToSave() % 13 == 0) {
			// player move on xAxis
			if (player.getSpeed() <= 7.5f) // cap
				player.setSpeed(player.getSpeed() + (4.0f / 38.0f)); // about 0.1052631... interval step
			// start speed 3.7f, cap speed 7.5f -> 4 steps. 494secs (target) / 13secs
			// (interval) = 38 iterations
		}
		if (handler.getTimeManager().getTimeToSave() % 17 == 0) {
			// player jumpStrength
			if (player.getJumpStrength() <= -10.0f) // cap
				player.setJumpStrength(player.getJumpStrength() + (3.0f / 29.0f)); // about 0.103448... interval step
			// start strength -13f, cap strength -10.0f -> 3 steps. 493secs (target) /
			// 17secs (interval) = 29 iterations
		}
		if (handler.getTimeManager().getTimeToSave() % 19 == 0) {
			// gravity
			if (player.getGravity() < 0.67) // cap
				player.setGravity(player.getGravity() + (0.17f / 26.0f)); // about 0.006538462... interval step
			// start gravity 0.5f, cap gravity 0.67f -> 0.17 steps. 494secs (target) /
			// 19secs (interval) = 26 iterations
		}
	}

	private void checkCollisions() {
		A: for (int i = 0; i < platformManager.getPlatforms().size(); i++) {
			if (player.getRect().intersects(platformManager.getPlatforms().get(i).getRect())) {
				if (player.getBottom().intersects(platformManager.getPlatforms().get(i).getRect())) {
					player.setY(platformManager.getPlatforms().get(i).getY() - player.getHeight());
					player.setyMove(platformManager.getPlatforms().get(i).getSpeed());

					if (!platformManager.getPlatforms().get(i).isWasUsed()) {
						hud.timeGathered(platformManager.getPlatforms().get(i).getValue());
						platformManager.getPlatforms().get(i).setWasUsed(true);
					}
					player.setJumping(false);
					break A;
				}

				else if (player.getTop().intersects(platformManager.getPlatforms().get(i).getRect())) {
					player.setY((platformManager.getPlatforms().get(i).getY()
							+ platformManager.getPlatforms().get(i).getHeight()) + 1);
					player.setyMove(-player.getyMove());
					break A;
				}

				else if (player.getLeftSide().intersects(platformManager.getPlatforms().get(i).getRect())
						|| player.getRightSide().intersects(platformManager.getPlatforms().get(i).getRect())) {
					player.setxMove(0);
					break A;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		backgroundManager.render(g);
		platformManager.render(g);
		player.render(g);
		hud.render(g);
	}

	public void loadMouseManager() {
	}
}
