package com.tabs.tablature.framework.base;

import android.graphics.Paint;

public class InteractiveSpriteBase extends SpriteBase {

    public InteractiveSpriteBase(String assetPath, float initialX, float initialY) {
        super(assetPath, initialX, initialY);
    }

    public boolean withinTouchBox(float xCoord, float yCoord) {
        return (xCoord >= this.x && xCoord <= x + getWidth()
                && yCoord >= y && yCoord <= y + getHeight());
    }

    public void setTransparency(int transparency) {
        this.paint = new Paint();
        paint.setAlpha(transparency);
    }
}
