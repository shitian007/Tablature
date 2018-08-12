package com.tabs.tablature.framework.base;

public class InteractiveSpriteBase extends SpriteBase {

    public InteractiveSpriteBase(String assetPath, float initialX, float initialY) {
        super(assetPath, initialX, initialY);
    }

    public boolean withinTouchBox(float xCoord, float yCoord) {
        return (xCoord >= x && xCoord <= x + imageWidth
                && yCoord >= y && yCoord <= y + imageHeight);
    }
}
