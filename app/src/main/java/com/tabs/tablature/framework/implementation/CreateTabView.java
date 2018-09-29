package com.tabs.tablature.framework.implementation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ScrollView;

import com.tabs.tablature.framework.base.Note;
import com.tabs.tablature.framework.base.Stave;

import static com.tabs.tablature.constants.DimenConstants.CREATE_TAB_MENU_HEIGHT;


public class CreateTabView extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mGameThread;
    final CreateTabScrollView parentScrollView;

    /**
     * Handles long press on objects */
    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }
    GestureDetector gestureDetector;
    static boolean gestureHandlerActive;

    public void setCreateTabManager(CreateTabManager createTabManager) {
        this.createTabManager = createTabManager;
    }
    CreateTabManager createTabManager;

    public CreateTabView(Context context, CreateTabScrollView parentScrollView) {
        super(context);
        getHolder().addCallback(this);

        this.parentScrollView = parentScrollView;

        mGameThread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    /**
     * Height of view should change dynamically according to number of tab lines
     * taking the maximum of either number of staves or screen height
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.max(MeasureSpec.getSize(heightMeasureSpec),
                createTabManager.getStaves().size() * 230);
        setMeasuredDimension(width, height);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mGameThread.setRunning(true);
        mGameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                mGameThread.setRunning(false);
                mGameThread.join();
                retry = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            canvas.drawColor(Color.WHITE);
            for (Stave stave : createTabManager.getStaves()) {
                stave.draw(canvas);
            }
            for (Note note : createTabManager.getNotes()) {
                note.draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("onTouchEvent", "xCoord: " + event.getX() + " yCoord: " + event.getY());

        if (!gestureHandlerActive) {
            Log.d("GestureHandler Inactive", "xCoord: " + event.getX() + " yCoord: " + event.getY());
            parentScrollView.enableScrolling();
            gestureDetector.onTouchEvent(event);
        } else {
            parentScrollView.disableScrolling();
            standardTouchHandler(event);
        }
        return true;
    }

    private boolean standardTouchHandler(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                Log.d("StandardTouch MOVE", "xCoord: " + event.getX() + " yCoord: " + event.getY());
                createTabManager.getCurrentlySelectedObject().move(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.d("StandardTouch UP", "xCoord: " + event.getX() + " yCoord: " + event.getY());
                gestureHandlerActive = false;
                createTabManager.resetCurrentlySelectedObject();
                break;
        }
        return true;
    }
}
