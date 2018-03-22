package com.trungdang.practice.udemy_animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv2 = this.findViewById(R.id.iv2);
        iv2.setAlpha(0.0f);
        ImageView iv3 = this.findViewById(R.id.iv3);
        iv3.setTranslationX(-1500f);

    }

    public void fade(View v) {
        ImageView mario = this.findViewById(R.id.iv);
        ImageView luigi = this.findViewById(R.id.iv2);
        ImageView iv3 = this.findViewById(R.id.iv3);

        if (count == 4) {
            disappear(mario);
            disappear(luigi);
            iv3.animate().translationXBy(1500f).setDuration(1000);
        } else {
            if (mario.getAlpha() < 1.0f) {
                reappear(mario);
                disappear(luigi);
            } else {
                disappear(mario);
                reappear(luigi);
            }
        }
        count++;
        if (count > 5) {
            count = 0;
            iv3.animate().translationXBy(-1500f).setDuration(1000);

        }
        //Toast.makeText(this, count + " " + iv3.getX(), Toast.LENGTH_SHORT).show();

    }

    private void disappear(ImageView iv) {
        iv.animate().alpha(0.0f).setDuration(1000);
    }

    private void reappear(ImageView iv) {
        iv.animate().alpha(1.0f).setDuration(1000);
    }
}
