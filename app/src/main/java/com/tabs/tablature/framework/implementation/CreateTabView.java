package com.tabs.tablature.framework.implementation;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tabs.tablature.R;

public class CreateTabView extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mGameThread;
    Note note;

    public CreateTabView(Context context) {
        super(context);
        getHolder().addCallback(this);

        mGameThread = new MainThread(getHolder(), this);
        setFocusable(true);
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

    public void update() {
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
