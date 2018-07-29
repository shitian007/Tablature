package com.tabs.tablature;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tabs.tablature.framework.implementation.TablatureAudio;
import com.tabs.tablature.framework.implementation.TablatureFileIO;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class CreateTabActivity extends AppCompatActivity {

    GLSurfaceView glView;
    TablatureFileIO tablatureFileIO;
    TablatureAudio tablatureAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Wake-lock for screen timeout
        PowerManager powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        wakeLock.acquire();
        // TODO: Extend GLSurfaceView to have OpenGL inside layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        hideSystemUI();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(new SimpleRenderer());
        setContentView(glView);

        tablatureFileIO = new TablatureFileIO(this);
        tablatureAudio = new TablatureAudio(this);
    }

    static class SimpleRenderer implements GLSurfaceView.Renderer {
        Random rand = new Random();

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            Log.d("GLSurfaceViewTest", "surface created");

        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int i, int i1) {
            Log.d("GLSurfaceViewTest", "surface changed: "+ i +"x" + i1);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            gl10.glClearColor(rand.nextFloat(), rand.nextFloat(),
                    rand.nextFloat(), 1);
            gl10.glClear(GL11.GL_COLOR_BUFFER_BIT);
        }
    }

    // Enable stick immersive display on full screen
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
