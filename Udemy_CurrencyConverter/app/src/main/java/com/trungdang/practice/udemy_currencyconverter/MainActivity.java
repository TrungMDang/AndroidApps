package com.trungdang.practice.udemy_currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Formatter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final double POUND_USD_RATIO = 1.39;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClickedHandler(View v) {
        EditText et = this.findViewById(R.id.main_currIn);
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb, Locale.getDefault());
        Double input = Double.parseDouble(et.getText().toString());
        input *= POUND_USD_RATIO;

        Toast.makeText(this, fm.format("%2.2f USD", input).toString(), Toast.LENGTH_LONG).show();


    }
}
