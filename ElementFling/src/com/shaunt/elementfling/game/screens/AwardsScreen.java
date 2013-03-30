package com.shaunt.elementfling.game.screens;

import com.shaunt.elementfling.framework.Game;
import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Screen;
import com.shaunt.elementfling.game.Assets;

public class AwardsScreen extends Screen {

	public AwardsScreen(Game game) {
		super(game);

	}

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
