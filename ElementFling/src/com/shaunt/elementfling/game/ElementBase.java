package com.shaunt.elementfling.game;

import android.graphics.Bitmap;

import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Pixmap;


public class ElementBase {

	private int x;
	private int y;
	private Pixmap pixmap;

	
	public ElementBase() {
		this.x = 0;
		this.y = 650;
		this.pixmap = Assets.base;
	}
	
	public void drawPixmap(Graphics g) {
		g.drawPixmap(pixmap, x, y);
	}

	public Pixmap getPixmap() {
		// TODO Auto-generated method stub
		return pixmap;
	}
	
}
