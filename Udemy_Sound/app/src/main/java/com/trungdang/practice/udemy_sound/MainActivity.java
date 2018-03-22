package com.trungdang.practice.udemy_sound;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMediaPlayer = MediaPlayer.create(this, R.raw.baby_talk);

    }

    public void startSound(View v) {

        mMediaPlayer.start();


    }

    public void pauseSound(View v) {
        mMediaPlayer.pause();
    }

}
