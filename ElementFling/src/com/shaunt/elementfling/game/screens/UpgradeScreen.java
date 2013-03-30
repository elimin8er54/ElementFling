package com.shaunt.elementfling.game.screens;

import com.shaunt.elementfling.framework.Game;
import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Screen;
import com.shaunt.elementfling.game.Assets;
import com.shaunt.elementfling.game.Button;

public class UpgradeScreen extends Screen{
	
	public UpgradeScreen(Game game) {
		super(game);

	}
	
	private Button next_level;
	
	private Button wind_upgrade_1;
	private Button air_upgrade_1;
	private Button earth_upgrade_1;
	private Button fire_upgrade_1;
	
	private Button damage;

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
