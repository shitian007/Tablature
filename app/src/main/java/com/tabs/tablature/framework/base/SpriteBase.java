package com.tabs.tablature.framework.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.tabs.tablature.CreateTabActivity;

import java.io.IOException;

public class SpriteBase {

    protected Paint paint;
    protected Bitmap image;

    public void setScale(double scaleX, double scaleY) {
        this.image = Bitmap.createScaledBitmap(this.image,
                (int)(scaleX * getWidth()),
                (int)(scaleY * getHeight()), false);
    }

    public float x;
    public float y;

    public SpriteBase(String assetPath, float initialX, float initialY) {
        try {
            this.image = BitmapFactory.decodeStream(CreateTabActivity.tablatureFileIO.readAsset(assetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = initialX;
        this.y = initialY;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, this.paint);
    }
}
