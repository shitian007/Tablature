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
        Log.d("TabGestureListener", "Long press detected");

        float xCoord = e.getX();
        float yCoord = e.getY();

        for (Note note : createTabManager.notes) {
            if (note.withinTouchBox(xCoord, yCoord)) {
                createTabManager.setCurrentlySelectedObject(note);
                note.setTransparency(150);
                note.move(xCoord, yCoord); // Snap sprite to center of click
            }

        }
        if (createTabManager.getCurrentlySelectedObject() != null) {
            CreateTabView.gestureHandlerActive = true;
        }
    }
}
