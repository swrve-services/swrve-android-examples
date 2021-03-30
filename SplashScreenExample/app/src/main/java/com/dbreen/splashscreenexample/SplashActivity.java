package com.dbreen.splashscreenexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    private boolean canSwitchActivity;
    private boolean backgroundProcessFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        performSplashScreenTasks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.canSwitchActivity = true;
        if (this.backgroundProcessFinished) {
            maybeSwitchActivity();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.canSwitchActivity = false;
    }

    private void performSplashScreenTasks() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                maybeSwitchActivity();

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

        this.backgroundProcessFinished = true;
    }

    private void maybeSwitchActivity() {
        // Start your app main activity
        if (this.canSwitchActivity) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}