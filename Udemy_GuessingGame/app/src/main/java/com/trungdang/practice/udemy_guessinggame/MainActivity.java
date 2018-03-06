package com.trungdang.practice.udemy_guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int random = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomNumber();
    }

    private void randomNumber() {
        TextView tv = this.findViewById(R.id.tv);
        random = (int)(Math.random() * 21);
        tv.setText(R.string.main_message);
    }

    public void onButtonClickedHandler(View v) {
        EditText et = this.findViewById(R.id.et);
        int guess = Integer.parseInt(et.getText().toString());
        if ( guess > random) {
            Toast.makeText(this, "Lower", Toast.LENGTH_SHORT).show();
        } else if (guess < random) {
            Toast.makeText(this, "Higher", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            random = (int) (Math.random() * 21);
        }

    }
}
