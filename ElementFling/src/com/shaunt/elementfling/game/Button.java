package com.shaunt.elementfling.game;

import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Pixmap;

public class Button {
	private int x;
	private int y;
	private Pixmap pixmap;
	
	public Button(Pixmap pixmap, int x, int y) {
		this.x = x;
		this.y = y;
		this.pixmap = pixmap;
	}
	
	public void drawPixmap(Graphics g) {
		g.drawPixmap(pixmap, x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Pixmap getPixmap() {
		return pixmap;
	}

	public void setPixmap(Pixmap pixmap) {
		this.pixmap = pixmap;
	}
}
