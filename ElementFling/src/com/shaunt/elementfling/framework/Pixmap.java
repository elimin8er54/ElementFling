package com.shaunt.elementfling.framework;



import android.graphics.Bitmap;

import com.shaunt.elementfling.framework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getWidth();

	public int getHeight();

	public PixmapFormat getFormat();

	public void dispose();
	
	public void setBitmap(Bitmap bitmap);
}
