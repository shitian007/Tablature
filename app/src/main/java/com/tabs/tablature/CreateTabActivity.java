package com.tabs.tablature;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tabs.tablature.framework.implementation.CreateTabView;
import com.tabs.tablature.framework.implementation.TablatureAudio;
import com.tabs.tablature.framework.implementation.TablatureFileIO;

public class CreateTabActivity extends Activity {

    TablatureFileIO tablatureFileIO;
    TablatureAudio tablatureAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // Wake-lock for screen timeout
        PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        wakeLock.acquire();
        hideSystemUI();

        // File and Audio assets
        tablatureFileIO = new TablatureFileIO(this);
        tablatureAudio = new TablatureAudio(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new CreateTabView(this));
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