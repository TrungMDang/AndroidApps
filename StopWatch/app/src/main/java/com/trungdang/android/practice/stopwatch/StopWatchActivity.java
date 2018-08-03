package com.trungdang.android.practice.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.TimeoutException;

public class StopWatchActivity extends AppCompatActivity {

    private static final String TAG = "StopWatchActivity";

    private static final int SECOND_PER_HOUR = 3600;
    private static final int SECOND_PER_MINUTE = 60;
    private static final String SECOND = "seconds";
    private static final String RUNNING = "running";
    private static final String WAS_RUNNING = "was_running";

    private int second_elapsed = 0;
    private boolean isRunning = false;
    private boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            second_elapsed = (int) savedInstanceState.get(SECOND);
            isRunning = (boolean) savedInstanceState.get(RUNNING);
            wasRunning = (boolean) savedInstanceState.get(WAS_RUNNING);
        }
        runTimer();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "----- onStart()");
        super.onStart();
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "----- onResume()");
        super.onResume();
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SECOND, second_elapsed);
        outState.putBoolean(RUNNING, isRunning);
        outState.putBoolean(WAS_RUNNING, wasRunning);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "---- onPause()");
        super.onPause(); if (isRunning) {

            wasRunning = true;
            isRunning = false;
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "----- onStop()");
        super.onStop();
        if (isRunning) {
            wasRunning = true;
            isRunning = false;

        }
    }

    public void onClickStart(View view) {
        if (!isRunning) {
            isRunning = true;
        }
    }

    public void onClickStop(View view) {
        if (isRunning) {
            isRunning = false;
        }
    }

    public void onClickReset(View view) {
        if (isRunning) {
            isRunning = false;
        }
        second_elapsed = 0;
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.time);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second_elapsed / SECOND_PER_HOUR;
                int minutes = (second_elapsed % SECOND_PER_HOUR) / SECOND_PER_MINUTE;
                int seconds = second_elapsed % 60;
                String time = String.format(Locale.US,
                        "%d:%02d:%02d", hours, minutes, seconds);
                timeView.setText(time);
                if (isRunning) {
                    second_elapsed++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
