package com.tabs.tablature.framework.implementation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tabs.tablature.framework.base.Note;
import com.tabs.tablature.framework.base.Stave;


public class CreateTabView extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mGameThread;
    final CreateTabScrollView parentScrollView;

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }
    GestureDetector gestureDetector;

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
        int height = Math.max(MeasureSpec.getSize(heightMeasureSpec), 2000);
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
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        float xCoord = event.getX();
        float yCoord = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("CreateTabView onTouch", String.format("ACTION DOWN on coordinates: %f, %f", xCoord, yCoord));
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("CreateTabView onTouch", "ACTION MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("CreateTabView onTouch", "ACTION UP");
                break;
        }
        return true;
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
}
