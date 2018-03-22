package com.trungdang.practice.asynctaskdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Trung Dang on 3/20/2018.
 */

public class LongTask extends AsyncTask<String, Integer, Integer>{

    public static int PROGRESS_UPDATE = 0;
    public static int PROGRESS_DONE = 1;


    private IReportBackObject mReportBackObject;
    private Context mContext;
    private String mTag;

    public LongTask(IReportBackObject reportBackObject, Context context, String tag){
        mReportBackObject = reportBackObject;
        mContext = context;
        mTag = tag;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        for (int i = 0; i < 5; i++) {
            SystemClock.sleep(1000);
            publishProgress(100/5);
        }
        return 100;
    }

    protected void onProgressUpdate(Integer... progress) {
        Integer i = progress[0];
        mReportBackObject.reportBack(mTag, PROGRESS_UPDATE, i);
    }

    protected void onPostExecute(Integer result) {
        //Runs on the main ui thread
        mReportBackObject.reportBack(mTag,PROGRESS_DONE,  result);
    }

}
