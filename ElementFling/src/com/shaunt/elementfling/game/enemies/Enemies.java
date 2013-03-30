package com.shaunt.elementfling.game.enemies;

import android.graphics.Rect;

import com.shaunt.elementfling.framework.Pixmap;

public class Enemies {
	
	 public int x;
	 public int y;

	private Pixmap pixmap;
	private float health = 100;

	private Rect rectangle;
	
	public Enemies(Pixmap pixmap, int x, int y) {
		this.pixmap = pixmap;
		this.x = x;
		this.y = y;
		setRectangle(new Rect(x ,y, x +pixmap.getWidth(),y + pixmap.getHeight()));
	}


	public Pixmap getPixmap() {
		return pixmap;
	}


	public void setPixmap(Pixmap pixmap) {
		this.pixmap = pixmap;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public float getHealth() {
		return health;
	}


	public void setHealth(float health) {
		this.health = health;
	}


	public Rect getRectangle() {
		return rectangle;
	}


	public void setRectangle(Rect rectangle) {
		this.rectangle = rectangle;
	}
	
}
