package com.tabs.tablature.framework.implementation;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tabs.tablature.R;

public class CreateTabView extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mGameThread;
    Note note;
    CreateTabScrollView parentScrollView;

    public CreateTabView(Context context, CreateTabScrollView parentScrollView) {
        super(context);
        getHolder().addCallback(this);
        this.parentScrollView = parentScrollView;

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
        note = new Note(BitmapFactory.decodeResource(getResources(), R.drawable.crochet), 0, 0);
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

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true; //processed
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        if (canvas != null) {
            note.x = 0;
            note.y = 0;
            note.draw(canvas);
            note.x = 0;
            note.y = 300;
            note.draw(canvas);
            note.x = 0;
            note.y = 600;
            note.draw(canvas);
            note.x = 0;
            note.y = 900;
            note.draw(canvas);
            note.x = 0;
            note.y = 1200;
            note.draw(canvas);
        }
    }

}
