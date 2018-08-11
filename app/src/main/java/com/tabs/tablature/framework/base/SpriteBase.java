package com.tabs.tablature.framework.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteBase {

    private Bitmap image;

    public int x;
    public int y;

    public SpriteBase(Bitmap bitmap, int initialX, int initialY) {
        this.image = bitmap;
        this.x = initialX;
        this.y = initialY;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
}
