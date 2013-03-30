package com.shaunt.elementfling.game;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Rect;

import com.shaunt.elementfling.game.elements.Elements;
import com.shaunt.elementfling.game.enemies.Enemies;
import com.shaunt.elementfling.game.enemies.EnemyHorde;

public class World {
	static final int WORLD_WIDTH = 10;

	static final int SCORE_INCREMENT = 10;
	static final float TICK_INITIAL = .025f;
	static final float TICK_DECREMENT = 0.05f;

	public ArrayList<Elements> elems = new ArrayList<Elements>();
	public ElementBase base;
	public EnemyHorde horde;
	public boolean gameOver = false;
	public int gold = 0;
	boolean fields[] = new boolean[WORLD_WIDTH];
	Random random = new Random();
	float tickTime = 0;
	static float tick = TICK_INITIAL; 

	public World() {
		horde = new EnemyHorde();
		base = new ElementBase();
		placeEnemy();
		
	}
	

	private void placeEnemy() {
		for (int x = 0; x < WORLD_WIDTH; x++) {

			fields[x] = false;

		}
		int len = horde.parts.size();
		for (int i = 0; i < len; i++) {
			Enemies part = horde.parts.get(i);
			fields[part.x] = true;
		}
	}

	public void update(float deltaTime) {
		if (gameOver)
			return;
		tickTime += deltaTime;
		while (tickTime > tick) {
			tickTime -= tick;
			horde.advance();
			if (horde.checkHit()) {
				gameOver = true;
				Assets.worldM.stop();
				return;
			}

			for(int i = 0; i < elems.size();i++) {
				for(int j = 0; j < horde.parts.size();j++) {
					
					Elements eValue = elems.get(i);
					Enemies enValue = horde.parts.get(j);

					
					if(Rect.intersects(eValue.getRectangle(), enValue.getRectangle())) {
						elems.remove(i);
						horde.parts.remove(j);
						break;
					}
				}
			}
			
			if(horde.parts.size() == 0) {
				horde.group();
			}

			gold += SCORE_INCREMENT;
				//horde.attack();
				if (horde.parts.size() == WORLD_WIDTH ) {
					gameOver = true;
					return;
				} else {
					placeEnemy();
				}
				if (gold % 100 == 0 && tick - TICK_DECREMENT > 0) {
					tick -= TICK_DECREMENT;
				}
			
		}
	}
}