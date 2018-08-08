package com.tabs.tablature.framework.implementation;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TabGestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("TabGestureListener", "Long press detected");
    }
}
