package com.trungdang.practice.broadcastreceiver_longservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Trung Dang on 3/21/2018.
 */

public class TestReceiver extends BroadcastReceiver {


    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Intent from TestReceiver1: " + intent);
        String message = intent.getStringExtra("message");
        Log.d(TAG, message);
    }
}
