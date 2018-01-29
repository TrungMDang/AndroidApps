package com.android.trung.threefragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

public class MainActivity extends FragmentActivity implements FirstFragment.OnItemSelectedListener {

    // Create a new Fragment to be placed in the activity layout
    Fragment firstFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Look for json file and parse it into string
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

        //Read json string
        try {
            JSONObject reader = new JSONObject(jsonIn);
            String name = reader.getString("name");
            String classpath = reader.getString("classpath");
            System.out.println("Name:" + name);
            System.out.println("Classpath:" + classpath);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();


            /**
             *  Source: android developer
             *
             */
            // Check that the activity is using the layout version with
            // the fragment_container FrameLayout
            if (findViewById(R.id.frame_container) != null) {

                // However, if we're being restored from a previous state,
                // then we don't need to do anything and should return or else
                // we could end up with overlapping fragments.
                if (savedInstanceState != null) {
                    return;
                }


                //Reflection
                try {
                    Class fragment = Class.forName(classpath);
                    firstFragment = (Fragment) fragment.newInstance();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                firstFragment.setArguments(getIntent().getExtras());

                // Add the fragment to the 'fragment_container' ConstraintLayout
                fragmentTransaction.add(R.id.frame_container, firstFragment);
                fragmentTransaction.commit();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onButtonSelected(String text) {
        System.out.println("Button selected");
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        return false;
    }
}
