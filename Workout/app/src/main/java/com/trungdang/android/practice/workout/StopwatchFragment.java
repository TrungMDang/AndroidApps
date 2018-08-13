package com.trungdang.android.practice.workout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "StopWatchActivity";

    private static final int SECOND_PER_HOUR = 3600;
    private static final int SECOND_PER_MINUTE = 60;
    private static final String SECOND = "seconds";
    private static final String RUNNING = "running";
    private static final String WAS_RUNNING = "was_running";

    private int second_elapsed = 0;
    private boolean isRunning = false;
    private boolean wasRunning;

    private Context mContext;
    private Button startB;
    private Button stopB;
    private Button resetB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (savedInstanceState != null) {
            second_elapsed = (int) savedInstanceState.get(SECOND);
            isRunning = (boolean) savedInstanceState.get(RUNNING);
            wasRunning = (boolean) savedInstanceState.get(WAS_RUNNING);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        if (layout != null) {
            startB = layout.findViewById(R.id.start);
            stopB = layout.findViewById(R.id.stop);
            resetB = layout.findViewById(R.id.reset);
            startB.setOnClickListener(this);
            stopB.setOnClickListener(this);
            resetB.setOnClickListener(this);

                runTimer(layout);

        }
        return layout;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "----- onStart()");
        super.onStart();
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "----- onResume()");
        super.onResume();
        if (wasRunning) {
            isRunning = true;
        }
        Log.d(TAG, "isRunning: " + isRunning + " wasRunning: " + wasRunning);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SECOND, second_elapsed);
        outState.putBoolean(RUNNING, isRunning);
        outState.putBoolean(WAS_RUNNING, wasRunning);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "---- onPause()");
        super.onPause();
        if (isRunning) {

            wasRunning = true;
            isRunning = false;
        }
    }

    @Override
    public void onStop() {
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
            wasRunning = false;
        }
    }

    public void onClickReset(View view) {
        if (isRunning) {
            isRunning = false;
            wasRunning = false;
        }
        second_elapsed = 0;
    }

    private void runTimer(View layout) {
        final TextView timeView = layout.findViewById(R.id.time);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                onClickStart(view);
                break;
            case R.id.stop:
                onClickStop(view);
                break;
            case R.id.reset:
                onClickReset(view);
                break;
        }
    }
}
