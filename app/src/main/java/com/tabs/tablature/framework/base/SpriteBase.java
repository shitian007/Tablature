package com.tabs.tablature.framework.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class SpriteBase {

    private Bitmap image;

    public float x;
    public float y;

    public float imageWidth;
    public float imageHeight;

    public SpriteBase(Bitmap bitmap, float initialX, float initialY) {
        this.image = bitmap;
        this.x = initialX;
        this.y = initialY;
        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();
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
