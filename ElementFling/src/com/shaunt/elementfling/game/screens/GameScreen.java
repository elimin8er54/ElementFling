package com.shaunt.elementfling.game.screens;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

import com.shaunt.elementfling.framework.Game;
import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Input.TouchEvent;
import com.shaunt.elementfling.framework.impl.AndroidPixmap;
import com.shaunt.elementfling.framework.Pixmap;
import com.shaunt.elementfling.framework.Screen;
import com.shaunt.elementfling.game.Assets;
import com.shaunt.elementfling.game.ElementBase;
import com.shaunt.elementfling.game.Settings;
import com.shaunt.elementfling.game.World;
import com.shaunt.elementfling.game.elements.Air;
import com.shaunt.elementfling.game.elements.Earth;
import com.shaunt.elementfling.game.elements.Elements;
import com.shaunt.elementfling.game.elements.Fire;
import com.shaunt.elementfling.game.elements.Water;
import com.shaunt.elementfling.game.enemies.Enemies;
import com.shaunt.elementfling.game.enemies.EnemyHorde;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	enum FlingState {
		Ready, Fling, Waiting
	}

	boolean dragged = false;
	boolean down = false;
	GameState state = GameState.Running;
	FlingState flingState = FlingState.Ready;
	World world;
	String score = "0";
	int start_x;
	int start_y;
	int end_x;
	int end_y;

	public GameScreen(Game game) {
		super(game);
		if (Settings.soundEnabled) {
			Assets.worldM.play();
			Assets.worldM.setLooping(true);
			Assets.worldM.setVolume(.5f);
		}
		world = new World();

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (flingState == FlingState.Ready) {
					start_x = event.x;
					start_y = event.y;
					down = true;
				}
			} else if (event.type == TouchEvent.TOUCH_DRAGGED) {
				if (event.x != start_x && event.y != start_y) {
					if (flingState == FlingState.Ready) {
						if (down == true) {
							end_x = event.x;
							end_y = event.y;
							dragged = true;

						}
					}
				}
				// create the line from start_x and start_y to the current
				// location
				// don't forget to invalidate the View otherwise your line won't
				// get redrawn

			} else if (event.type == TouchEvent.TOUCH_UP) {
				if (dragged == true) {
					if (flingState == FlingState.Ready) {
						flingState = FlingState.Fling;
						dragged = false;
						down = false;
					}
				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 64 && event.y < 64) {

					state = GameState.Paused;
					return;
				}
			}
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.x < 64 && event.y > 416) {

				}
				if (event.x > 256 && event.y > 416) {

				}
			}
		}
		world.update(deltaTime);
		if (world.gameOver) {

			state = GameState.GameOver;
		}
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
		drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2,
				g.getHeight() - 42);
	}

	private void drawWorld(World world) {
		Graphics g = game.getGraphics();

		int x = 0;
		int y = 0;
		ElementBase base = world.base;
		EnemyHorde horde = world.horde;

		base.drawPixmap(g);

		int len = horde.parts.size();
		for (int i = 0; i < len; i++) {
			Enemies part = horde.parts.get(i);
			x = part.x * 96;
			y = part.y;
			g.drawPixmap(Assets.fire, x, y);

		}

		if (flingState == FlingState.Fling) {
			if (start_y >= 800 - base.getPixmap().getHeight() && start_y <= 800) {
				if (start_x >= 0 && start_x < 120) {
					world.elems.add(new Water(Assets.water, start_x
							- (Assets.water.getWidth() / 2), start_y
							- (Assets.water.getHeight() / 2), start_x, start_y,
							end_x, end_y));
					if (Settings.soundEnabled)
						Assets.waterSound.play(1);
				} else if (start_x >= 120 && start_x < 240) {
					world.elems.add(new Earth(Assets.earth, start_x
							- (Assets.earth.getWidth() / 2), start_y
							- (Assets.earth.getHeight() / 2), start_x, start_y,
							end_x, end_y));
					if (Settings.soundEnabled)
						Assets.earthSound.play(1);
				} else if (start_x >= 240 && start_x < 360) {
					world.elems.add(new Fire(Assets.fire, start_x
							- (Assets.fire.getWidth() / 2), start_y
							- (Assets.fire.getHeight() / 2), start_x, start_y,
							end_x, end_y));
					if (Settings.soundEnabled)
						Assets.fireSound.play(1);
				} else if (start_x >= 360 && start_x < 480) {
					world.elems.add(new Air(Assets.air, start_x
							- (Assets.air.getWidth() / 2), start_y
							- (Assets.air.getHeight() / 2), start_x, start_y,
							end_x, end_y));
					if (Settings.soundEnabled)
						Assets.airSound.play(1);
				}

				// elem.rotate(90);

				flingState = FlingState.Waiting;
			} else {
				flingState = FlingState.Ready;
			}
		}
		// if (flingState == FlingState.Waiting) {
		g.drawLine(start_x, start_y, end_x, end_y, Color.WHITE);

		if (world.elems.size() > 0) {
			for (int i = 0; i < world.elems.size(); i++) {
				Elements value = world.elems.get(i);
				g.drawPixmap(value.getPixmap(), value.getX(), value.getY());
			}
		}

		for (int i = 0; i < world.elems.size(); i++) {
			world.elems.get(i).move();
		}

		// if (elem.getY() <= 0 - elem.getPixmap().getHeight() ||
		// elem.getX() <= 0 - elem.getPixmap().getWidth()|| elem.getX() >=
		// 440
		// || elem.getY() >= 800) {

		flingState = FlingState.Ready;
		// }
		// }

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();
	}

	private void drawRunningUI() {

		Graphics g = game.getGraphics();

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.gameover, 240, 100);

	}

	public void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			if (character == ' ') {
				x += 20;
				continue;
			}
			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}

			x += srcWidth;
		}
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
		if (world.gameOver) {
			Settings.addScore(world.gold);
			Settings.save(game.getFileIO());
		}
		Assets.worldM.stop();
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {

		if (state == GameState.Paused) {
			state = GameState.Running;
			Assets.worldM.play();

		}

	}

	@Override
	public void dispose() {
	}
}
