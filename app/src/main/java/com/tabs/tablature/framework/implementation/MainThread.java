package com.tabs.tablature.framework.implementation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;

    private GameView gameView;

    private boolean running;

    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {
        while (running) {
            canvas = null;
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                this.gameView.update();
                this.gameView.draw(canvas);
            }
            if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
