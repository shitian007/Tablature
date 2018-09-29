package com.tabs.tablature;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.tabs.tablature.framework.implementation.CreateTabManager;
import com.tabs.tablature.framework.implementation.CreateTabScrollView;
import com.tabs.tablature.framework.implementation.CreateTabView;
import com.tabs.tablature.framework.implementation.MainThread;
import com.tabs.tablature.framework.implementation.TabGestureListener;
import com.tabs.tablature.framework.InputOutput.TablatureAudio;
import com.tabs.tablature.framework.InputOutput.TablatureFileIO;

import static android.view.View.MeasureSpec.EXACTLY;

public class CreateTabActivity extends Activity {

    public static TablatureFileIO tablatureFileIO;
    public static TablatureAudio tablatureAudio;

    CreateTabScrollView scrollView;
    CreateTabView createTabView;
    CreateTabManager createTabManager;

    Button addStaveButton;
    View.OnClickListener addStaveListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // Wake-lock for screen timeout
        PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        wakeLock.acquire();
        hideSystemUI();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        tablatureFileIO = new TablatureFileIO(this);
        tablatureAudio = new TablatureAudio(this);

        setContentView(R.layout.activity_create_tab);

        scrollView = findViewById(R.id.create_tab_scroll_view);
        createTabView = new CreateTabView(this, scrollView);
        createTabManager = new CreateTabManager(createTabView);
        createTabManager.screenScale = getResources().getDisplayMetrics().density;
        createTabManager.instantiateObjects();
        createTabView.setCreateTabManager(createTabManager);
        createTabView.setGestureDetector(new GestureDetector(this, new TabGestureListener(createTabManager)));
        scrollView.addView(createTabView);

        initializeListeners();
        addStaveButton = findViewById(R.id.add_stave_button);
        addStaveButton.setOnClickListener(addStaveListener);
    }

    /** Initialize listeners for menu buttons */
    private void initializeListeners() {

        addStaveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTabManager.addStave();
            }
        };

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