package com.tabs.tablature;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(homeFragment);
                    return true;
                case R.id.navigation_create_tab:
                    Intent createTabIntent = new Intent(MainActivity.this, CreateTabActivity.class);
                    startActivity(createTabIntent);
                    return true;
                case R.id.navigation_profile:
                    setFragment(profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showSystemUI();
        navigation.setSelectedItemId(R.id.navigation_home);
        setFragment(homeFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backNavigationHelper();
    }

    @Override
    public void onResume() {
        super.onResume();
        backNavigationHelper();
    }

    private void backNavigationHelper() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(f instanceof HomeFragment) {
            setTitle("Home");
            navigation.setSelectedItemId(R.id.navigation_home);
        } else if (f instanceof ProfileFragment) {
            setTitle("User Profile");
            navigation.setSelectedItemId(R.id.navigation_profile);
        }
    }


    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility((
                View.SYSTEM_UI_FLAG_FULLSCREEN
                ));
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }

}
