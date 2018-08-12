package com.tabs.tablature.framework.implementation;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.tabs.tablature.framework.base.Note;
import com.tabs.tablature.framework.base.SpriteBase;

public class TabGestureListener extends GestureDetector.SimpleOnGestureListener {

    CreateTabManager createTabManager;

    public TabGestureListener(CreateTabManager createTabManager) {
        this.createTabManager = createTabManager;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        CreateTabView.gestureHandlerActive = true;
        Log.d("TabGestureListener", "Long press detected");

        float xCoord = e.getX();
        float yCoord = e.getY();

        for (Note note : createTabManager.getNotes()) {
            if (note.withinTouchBox(xCoord, yCoord)) {
                createTabManager.setCurrentlySelectedObject(note);
                note.setTransparency(150);
                snapToCenter(note, xCoord, yCoord);
            }

        }
    }

    /**
     * Snaps the sprite to the center of the clicked area
     */
    private void snapToCenter(SpriteBase spriteBase, float xCoord, float yCoord) {
        spriteBase.x = xCoord - spriteBase.getWidth() / 2;
        spriteBase.y = yCoord - spriteBase.getHeight() / 2;
    }
}
