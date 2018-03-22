package com.trungdang.practice.broadcastreceiver_longservicedemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private BroadcastReceiver mReceiver;
    private BroadcastReceiver mReceiver2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new TestReceiver();
        IntentFilter iF = new IntentFilter();
        iF.addAction("com.trungdang.practice.broadcastreceiver_longservicedemo.testBroadcast");
        this.registerReceiver(mReceiver,iF);

        mReceiver2 = new TestReceiver2(this);
        iF = new IntentFilter();
        iF.addAction("com.trungdang.practice.broadcastreceiver_longservicedemo.testBroadcast2");
        this.registerReceiver(mReceiver2,iF);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mReceiver);
        this.unregisterReceiver(mReceiver2);

    }

    public void onButtonClicked(View v) {
        switch(v.getId()) {
            case R.id.button_testBroadcast:
                Log.d(TAG, "-------------Begin broadcasting------------");
                testBroadcast(this);
                Log.d(TAG, "--------------Broadcast tested------------");
                break;
            case R.id.button_testUI:
                Button b = (Button) v;
                b.setText("Random: " + (int) (Math.random() * 100) + 1);
                break;
        }
    }
    private void testBroadcast(AppCompatActivity activity) {
        String uniqueAction = "com.trungdang.practice.broadcastreceiver_longservicedemo.testBroadcast";
        Intent broadcastIntent = new Intent(uniqueAction);

        //Allow stand alone cross-processes that have broadcast receivers
        //in them to be started even though they are in stopped state.
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        broadcastIntent.putExtra("message", "This is 1.");
        activity.sendBroadcast(broadcastIntent);

        String uniqueAction2 = "com.trungdang.practice.broadcastreceiver_longservicedemo.testBroadcast2";
        Intent broadcastIntent2 = new Intent(uniqueAction2);

        //Allow stand alone cross-processes that have broadcast receivers
        //in them to be started even though they are in stopped state.
        broadcastIntent2.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            broadcastIntent2.putExtra("message", "This is 2.");
        activity.sendBroadcast(broadcastIntent2);


    }
}
