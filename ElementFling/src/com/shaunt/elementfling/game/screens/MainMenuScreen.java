package com.shaunt.elementfling.game.screens;

import java.util.List;

import android.util.Log;

import com.shaunt.elementfling.framework.Game;
import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Input.TouchEvent;
import com.shaunt.elementfling.framework.Screen;
import com.shaunt.elementfling.game.Assets;
import com.shaunt.elementfling.game.Button;
import com.shaunt.elementfling.game.Settings;
import com.shaunt.elementfling.game.screens.GameScreen.GameState;

public class MainMenuScreen extends Screen {
	enum GameState {
		Ready, Play, About, Awards
	}
	
	static final float TICK_INITIAL = .025f;
	static final float TICK_DECREMENT = 0.05f;
	float tickTime = 0;
	static float tick = TICK_INITIAL;
	GameState state = GameState.Ready;


	private Button playButton;
	private Button aboutButton;
	private Button awardsButton;
	public MainMenuScreen(Game game) {
		super(game);
		playButton = new Button(Assets.button_play,230,400);
		aboutButton = new Button(Assets.button_about,230,500);
		awardsButton = new Button(Assets.button_awards,230,600);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
					Settings.soundEnabled = !Settings.soundEnabled;
					if (Settings.soundEnabled)
						Assets.select.play(1);
				}
				
				  if(inBounds(event, playButton.getX(), playButton.getY(), playButton.getPixmap().getWidth(), playButton.getPixmap().getHeight()) ) {
					  
					  if(Settings.soundEnabled) {
						  Assets.select.play(1); 
					  } 

					  state = GameState.Play;
					  //game.setScreen(new GameScreen(game)); 
					  return; 
				  }
				  if(inBounds(event, aboutButton.getX(), aboutButton.getY(), aboutButton.getPixmap().getWidth(), aboutButton.getPixmap().getHeight()) ) {
					  
					  if(Settings.soundEnabled) {
						  Assets.select.play(1); 
					  } 
					  state = GameState.About;
					  //game.setScreen(new GameScreen(game)); 
					  return; 
				  }
				  if(inBounds(event, awardsButton.getX(), awardsButton.getY(), awardsButton.getPixmap().getWidth(), awardsButton.getPixmap().getHeight()) ) {
					  
					  if(Settings.soundEnabled) {
						  Assets.select.play(1); 
					  } 
					  state = GameState.Awards;
					  //game.setScreen(new GameScreen(game)); 
					  return; 
				  }

			}
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		if (state != GameState.Play || state != GameState.About || state != GameState.Awards) {
			playButton.drawPixmap(g);
			aboutButton.drawPixmap(g);
			awardsButton.drawPixmap(g);
		}
		 if (state == GameState.Play || state == GameState.About || state == GameState.Awards) {

			tickTime += deltaTime;
			
			while (tickTime > tick) {
				g.drawPixmap(Assets.background, 0,0);
				playButton.drawPixmap(g);
				aboutButton.drawPixmap(g);
				awardsButton.drawPixmap(g);
				playButton.setX(playButton.getX() - 20);
				
				if(playButton.getX() <= 175) {
					aboutButton.setX(aboutButton.getX() - 20);
					if(aboutButton.getX() <= 175) {
						awardsButton.setX(awardsButton.getX() - 20);
					}
				}
				tickTime -= tick;
			}
			
			if(awardsButton.getX() + awardsButton.getPixmap().getWidth() <= 0) {
				
				if(state == GameState.Play) {
					game.setScreen(new GameScreen(game)); 
				} else if(state == GameState.About) {
					game.setScreen(new AboutScreen(game)); 
				} else if(state == GameState.Awards) {
					game.setScreen(new AwardsScreen(game)); 
				}
			}
		
	}
	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
		Settings.load(game.getFileIO());
	}

	@Override
	public void dispose() {
	}
}