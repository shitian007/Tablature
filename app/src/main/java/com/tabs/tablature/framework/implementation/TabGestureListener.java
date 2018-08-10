package com.tabs.tablature.framework.implementation;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.tabs.tablature.framework.base.NoteBase;

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

        for (NoteBase note : createTabManager.getNotes()) {
            if (note.withinTouchBox(xCoord, yCoord)) {
                Log.d("Note touched", "Positive");
            }

        }
    }
}
