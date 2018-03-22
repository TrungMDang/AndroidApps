package com.trungdang.practice.asynctaskdemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IReportBackObject{

    private LongTask mLongTask;

    private int aClickCount = 0;

    private IReportBackObject mReportBackObject;

    public void performLongTask() {
        mLongTask = new LongTask(this, this.getApplicationContext(), "Task1");
        mLongTask.execute("Start");
    }

    public void onButtonClicked(View v) {
        ProgressBar pB = findViewById(R.id.indeterminateBar);
        pB.setVisibility(View.VISIBLE);

        if (v.getId() == R.id.ba) {
            Button b = findViewById(R.id.bb);
            b.setVisibility(View.INVISIBLE);
            aClickCount++;
        }
        TextView tv = findViewById(R.id.tv);
        tv.setText("Performing background work. Button A was clicked: " + aClickCount);

        //Only do AsyncTask if the button is clicked the first time.
        if (aClickCount == 1) {
            performLongTask();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.bb);
        b.setVisibility(View.INVISIBLE);

        TextView tv = findViewById(R.id.tv);
        tv.setText("Ready for work. Click Button A to begin");

        ProgressBar pB = findViewById(R.id.indeterminateBar);
        pB.setVisibility(View.INVISIBLE);
        mReportBackObject = this;
    }


    @Override
    public void reportBack(String tag, int type, int progress) {
        if (type == LongTask.PROGRESS_UPDATE) {
            Log.d(tag, "Progress: " + progress);
            ProgressBar pB = findViewById(R.id.indeterminateBar);
            pB.setProgress(progress);
        } else if (type == LongTask.PROGRESS_DONE) {
            ProgressBar pB = findViewById(R.id.indeterminateBar);
            pB.setProgress(100);
            pB.setVisibility(View.INVISIBLE);
            aClickCount = 0;

            TextView tv = findViewById(R.id.tv);
            tv.setText("Background is done. Button B is back.");

            Button b = findViewById(R.id.bb);
            b.setVisibility(View.VISIBLE);

        }
    }
}
