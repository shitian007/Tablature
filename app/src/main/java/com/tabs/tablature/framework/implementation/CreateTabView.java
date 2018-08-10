package com.tabs.tablature.framework.implementation;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tabs.tablature.R;
import com.tabs.tablature.framework.base.NoteBase;

public class CreateTabView extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mGameThread;
    NoteBase noteBase;
    final CreateTabScrollView parentScrollView;
    final GestureDetector gestureListener;

    public CreateTabView(Context context, CreateTabScrollView parentScrollView) {
        super(context);
        getHolder().addCallback(this);

        this.parentScrollView = parentScrollView;
        this.gestureListener = new GestureDetector(context, new TabGestureListener());

        mGameThread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    /**
     * Height of view should change dynamically according to number of tab lines */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.max(MeasureSpec.getSize(heightMeasureSpec), 2000);
        setMeasuredDimension(width, height);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        noteBase = new NoteBase(BitmapFactory.decodeResource(getResources(), R.drawable.crochet), 0, 0);
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

        gestureListener.onTouchEvent(event);

        float xCoord = event.getX();
        float yCoord = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("CreateTabView onTouch", "ACTION DOWN");
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
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        if (canvas != null) {
            noteBase.x = 0;
            noteBase.y = 0;
            noteBase.draw(canvas);
            noteBase.x = 0;
            noteBase.y = 300;
            noteBase.draw(canvas);
            noteBase.x = 0;
            noteBase.y = 600;
            noteBase.draw(canvas);
            noteBase.x = 0;
            noteBase.y = 900;
            noteBase.draw(canvas);
            noteBase.x = 0;
            noteBase.y = 1200;
            noteBase.draw(canvas);
        }
    }

}
