package com.shaunt.elementfling.game.screens;

import com.shaunt.elementfling.framework.Game;
import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Screen;
import com.shaunt.elementfling.framework.Graphics.PixmapFormat;
import com.shaunt.elementfling.game.Assets;
import com.shaunt.elementfling.game.Settings;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("images/background.png", PixmapFormat.RGB565);
		Assets.fire = g.newPixmap("images/fire.png", PixmapFormat.ARGB4444);
		Assets.water = g.newPixmap("images/water.png", PixmapFormat.ARGB4444);
		Assets.air = g.newPixmap("images/air.png", PixmapFormat.ARGB4444);
		Assets.earth = g.newPixmap("images/earth.png", PixmapFormat.ARGB4444);
		Assets.button_play = g.newPixmap("images/button_play.png", PixmapFormat.ARGB4444);
		Assets.button_about = g.newPixmap("images/button_about.png", PixmapFormat.ARGB4444);
		Assets.button_awards = g.newPixmap("images/button_awards.png", PixmapFormat.ARGB4444);
		Assets.base = g.newPixmap("images/base.png", PixmapFormat.ARGB4444);
		Assets.gameover = g.newPixmap("images/gameover.png", PixmapFormat.ARGB4444);
		Assets.select = game.getAudio().newSound("sounds/select.ogg");
		Assets.waterSound = game.getAudio().newSound("sounds/water2.ogg");
		Assets.airSound = game.getAudio().newSound("sounds/air.ogg");
		Assets.fireSound = game.getAudio().newSound("sounds/fire.ogg");
		Assets.earthSound = game.getAudio().newSound("sounds/earth.ogg");
		Assets.worldM = game.getAudio().newMusic("sounds/battle.ogg");
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
