package com.tabs.tablature.framework.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.tabs.tablature.framework.base.SpriteBase;

public class Note extends SpriteBase {

    public Note(Bitmap bitmap, float initialX, float initialY) {
        super(bitmap, initialX, initialY);
    }

    public boolean withinTouchBox(float xCoord, float yCoord) {
        return (xCoord >= x && xCoord <= x + imageWidth
                && yCoord >= y && yCoord <= y + imageHeight);
    }
}
