package com.android.trung.threefragmentapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonIn = null;
        try {
            InputStream in = getAssets().open("data/first_frag.json");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            jsonIn = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject reader = new JSONObject(jsonIn);
            System.out.println("Name:" + reader.getString("name"));
            System.out.println("Classpath:" + reader.getString("classpath"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
