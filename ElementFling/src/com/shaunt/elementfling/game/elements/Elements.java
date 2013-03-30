package com.shaunt.elementfling.game.elements;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.shaunt.elementfling.framework.Graphics;
import com.shaunt.elementfling.framework.Pixmap;
import com.shaunt.elementfling.framework.impl.AndroidPixmap;

public class Elements {
	enum ElementState {
		Idle, LevelOne, LevelTwo, LevelThree
	}

	private int x;
	private int y;
	private int startingX, startingY;
	private int endingX, endingY;
	private Pixmap pixmap;
	static final float DAMAGE = 100;
	private ElementState state = ElementState.Idle;
	private Rect rectangle;

	public Elements(Pixmap pixmap, int x, int y, int startingX, int startingY,
			int endingX, int endingY) {
		this.setPixmap(pixmap);
		this.setX(x);
		this.setY(y);
		this.setStartingX(startingX);
		this.setStartingY(startingY);
		this.setEndingX(endingX);
		this.setEndingY(endingY);
		setRectangle(new Rect(x,y, x +pixmap.getWidth(),y + pixmap.getHeight()));
	}

	public Elements(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public Elements(Pixmap pixmap) {
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

	public ElementState getState() {
		return state;
	}

	public void setState(ElementState state) {
		this.state = state;
	}

	public void rotate(int degrees) {

		Matrix matrix = new Matrix();
		matrix.postRotate(degrees);
		Bitmap rotated = Bitmap.createBitmap(
				((AndroidPixmap) getPixmap()).bitmap, 0, 0, getPixmap()
						.getWidth(), getPixmap().getHeight(), matrix, true);
		getPixmap().setBitmap(rotated);

	}

	public void checkHitEnemy() {

	}

	public int getStartingX() {
		return startingX;
	}

	public void setStartingX(int startingX) {
		this.startingX = startingX;
	}

	public int getStartingY() {
		return startingY;
	}

	public void setStartingY(int startingY) {
		this.startingY = startingY;
	}

	public int getEndingX() {
		return endingX;
	}

	public void setEndingX(int endingX) {
		this.endingX = endingX;
	}

	public int getEndingY() {
		return endingY;
	}

	public void setEndingY(int endingY) {
		this.endingY = endingY;
	}
	
	public void move() {
		double theta = Math.atan2(endingY - startingY, endingX - startingX);
		double dx = 40 * Math.cos(theta);
		double dy = 40 * Math.sin(theta);

		this.setX((int) (this.getX() + dx));
		this.setY((int) (this.getY() + dy));
		this.rectangle.left = x;
		this.rectangle.top = y;
		this.rectangle.right = x + pixmap.getWidth();
		this.rectangle.bottom = y + pixmap.getHeight();

		
	}

	public Rect getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rect rectangle) {
		this.rectangle = rectangle;
	}
	
	

}
