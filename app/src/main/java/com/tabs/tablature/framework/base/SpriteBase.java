package com.tabs.tablature.framework.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.tabs.tablature.CreateTabActivity;
import com.tabs.tablature.constants.DimenConstants;

import java.io.IOException;

public class SpriteBase {

    private Bitmap image;

    public void setScale(double scaleX, double scaleY) {
        this.image = Bitmap.createScaledBitmap(this.image,
                (int)(scaleX * imageWidth),
                (int)(scaleY * imageHeight), false);
    }

    private double scale;

    public float x;
    public float y;

    public float imageWidth;
    public float imageHeight;

    public SpriteBase(String assetPath, float initialX, float initialY) {
        try {
            this.image = BitmapFactory.decodeStream(CreateTabActivity.tablatureFileIO.readAsset(assetPath));
            this.imageWidth = image.getWidth();
            this.imageHeight = image.getHeight();
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
        canvas.drawBitmap(image, x + DimenConstants.SCROLL_VIEW_PADDING_LEFT,
                y + DimenConstants.SCROLL_VIEW_PADDING_TOP, null);
    }
}
