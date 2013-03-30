package com.shaunt.elementfling.framework.impl;

import android.graphics.Bitmap;
import com.shaunt.elementfling.framework.Graphics.PixmapFormat;
import com.shaunt.elementfling.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
	public Bitmap bitmap;
	PixmapFormat format;

	public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}

	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	@Override
	public PixmapFormat getFormat() {
		return format;
	}

	@Override
	public void dispose() {
		bitmap.recycle();
	}

	@Override
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		
	}


}