package com.trungdang.practice.broadcastreceiver_longservicedemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by Trung Dang on 3/21/2018.
 */

public class TestReceiver2 extends BroadcastReceiver{


    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;

    public TestReceiver2(Context context) {
        mContext = context;
    }

    /**
     * Perform a long task
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, final Intent intent) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(TAG, "Receiver 2 started... on Main UI thread");
        } else {
            Log.d(TAG, "Receiver 2 started... on another thread");
        }
        final PendingResult pendingResult = goAsync();
        AsyncTask<String, Integer, String> asyncTask = new MyTask(pendingResult, intent);
        asyncTask.execute();

    }



     class MyTask extends AsyncTask<String, Integer, String> {

        public static final String TASK_DONE = "Done";

        private PendingResult mPR;

        private Intent mIntent;

        private final String TAG = this.getClass().getSimpleName();

        public MyTask(PendingResult pR, Intent intent) {
            mPR = pR;
            mIntent = intent;
        }

        @Override
        public void onPostExecute(String result) {
            if (result == TASK_DONE) {
                notifyUser();
            }
        }

        private void notifyUser() {

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "1")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Background Task")
                    .setContentText("Status: " + TASK_DONE)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d(TAG, "Creating channel for notification...");
                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                CharSequence name = "1";
                String description ="Channel 1";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel("1", name, importance);
                channel.setDescription(description);
                // Register the channel with the system
                NotificationManager notificationManager =
                        (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                //notificationManager.createNotificationChannel(channel);
            }

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, mBuilder.build());
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb = new StringBuilder();
            sb.append("Action: " + mIntent.getAction() + "\n");
            sb.append("URI: " + mIntent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
            Log.d(TAG, mIntent.getStringExtra("message"));
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Log.d(TAG, "Long task started... on Main UI thread");
            } else {
                Log.d(TAG, "Long task started... on another thread");
            }
            for(int i = 0; i < 5; i++) {
                SystemClock.sleep(2000);
                Log.d(TAG, "Sleeping at cycle: " + i);
            }
            // Must call finish() so the BroadcastReceiver can be recycled.
            mPR.finish();
            Log.d(TAG, "Long task status: " + TASK_DONE);
            return TASK_DONE;
        }
    }
}
