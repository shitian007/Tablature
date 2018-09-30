package com.tabs.tablature.framework.implementation;

import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;

import java.util.concurrent.TimeUnit;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;

    private CreateTabView createTabView;

    private boolean running;

    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, CreateTabView createTabView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.createTabView = createTabView;
    }

    @Override
    public void run() {
        while (running) {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                this.createTabView.draw(canvas);
            }
            if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
