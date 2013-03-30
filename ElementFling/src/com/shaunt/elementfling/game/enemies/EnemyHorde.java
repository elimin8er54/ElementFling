package com.shaunt.elementfling.game.enemies;

import java.util.ArrayList;
import java.util.List;

import com.shaunt.elementfling.game.Assets;

public class EnemyHorde {
	public List<Enemies> parts = new ArrayList<Enemies>();

	public EnemyHorde() {
		
		parts.add(new Enemies(Assets.fire,0, 0));
		parts.add(new Enemies(Assets.fire,1,0));
		parts.add(new Enemies(Assets.fire,2,0)); 
		parts.add(new Enemies(Assets.fire,3,0)); 
		parts.add(new Enemies(Assets.fire,4,0)); 

	}

	public void group() {

		parts.add(new Enemies(Assets.fire,0, 0));
		parts.add(new Enemies(Assets.fire,1,0));
		parts.add(new Enemies(Assets.fire,2,0)); 
		parts.add(new Enemies(Assets.fire,3,0)); 
		parts.add(new Enemies(Assets.fire,4,0)); 


	}

	public void advance() {


		for (int i = 0; i < parts.size(); i++) {
			Enemies temp = parts.get(i);
			temp.setY(temp.getY() + 1);
			temp.getRectangle().left = temp.getX() * 96;
			temp.getRectangle().top = temp.getY();
			temp.getRectangle().right = temp.getRectangle().left + temp.getPixmap().getWidth();
			temp.getRectangle().bottom = temp.getY() + temp.getPixmap().getHeight();
		}

	}

	public boolean checkHit() {
		int len = parts.size();

		for (int i = 0; i < len; i++) {
			Enemies part = parts.get(i);
			if (part.y == 650)
				return true;
		}
		return false;
	}

	public void attack() {
		// TODO Auto-generated method stub
		
	}
}
