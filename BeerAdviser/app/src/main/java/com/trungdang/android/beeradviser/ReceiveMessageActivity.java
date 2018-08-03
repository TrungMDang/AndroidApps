package com.trungdang.android.beeradviser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    private static final String TAG = "ReceiveMessageActivity";

    public static final String EXTRA_MESSAGE = "message_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
//        if (intent != null) {
//            String message = intent.getStringExtra(EXTRA_MESSAGE);
//            TextView textView = findViewById(R.id.message_display);
//            if (textView != null) {
//                textView.setText(message);
//            }
//        }
        if (intent.getType() != null && intent.getType().equals("text/plain")) {
            Log.d(TAG, "----- Intent of type: " + intent.getType());
            TextView textView = findViewById(R.id.message_display);
            if (textView != null) {
                textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
                if (!textView.isInLayout()) {
                    textView.requestLayout();
                }
            }
        }
    }
}
