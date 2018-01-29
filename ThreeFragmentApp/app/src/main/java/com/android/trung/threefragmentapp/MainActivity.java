package com.android.trung.threefragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.TimeUtils;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;

public class MainActivity extends FragmentActivity implements OnItemSelectedListener, OnDetachListener {

    public static final short FRAG_ADD = 0;
    public static final short FRAG_REPLACE = 1;
    public static final String NAME = "Name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonIn = getJSONInput("data/first_frag.json");
        FragmentManager fm = getSupportFragmentManager();
        initializeNewFrag(FRAG_ADD, jsonIn, fm, savedInstanceState);
        System.out.println(this.getLocalClassName() + " created");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(this.getLocalClassName() + " resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(this.getLocalClassName() + " started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(this.getLocalClassName() + " paused");
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(this.getLocalClassName() + " stopped");
    }


    @Override
    public boolean onButtonSelected(String text, int fragID, Bundle savedInstanceState) {
        System.out.println("Button selected");
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text + " " + fragID, Toast.LENGTH_SHORT);
        toast.show();

        FragmentManager fm = getSupportFragmentManager();
        String jsonIn = null;
        switch (fragID) {
            case 0:     //First fragment button was selected
                jsonIn = getJSONInput("data/second_frag.json");
                initializeNewFrag(FRAG_REPLACE, jsonIn, fm, savedInstanceState);
                break;
            case 1:     //Second fragment button was selected
                jsonIn = getJSONInput("data/third_frag.json");
                initializeNewFrag(FRAG_REPLACE, jsonIn, fm, savedInstanceState);
                break;
            case 2:
                jsonIn = getJSONInput("data/first_frag.json");
                initializeNewFrag(FRAG_REPLACE, jsonIn, fm, savedInstanceState);
                break;
        }
        return false;
    }

    private void initializeNewFrag(short type, String jsonIn, FragmentManager fm, Bundle savedInstanceState) {
        // Create a new Fragment to be placed in the activity layout
        Fragment fragment = null;
        try {
            JSONObject reader = new JSONObject(jsonIn);
            String name = reader.getString("name");
            String classpath = reader.getString("classpath");
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            /**
             *  Source: android developer
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
                    Class fragmentClass = Class.forName(classpath);
                    fragment = (Fragment) fragmentClass.newInstance();


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                Bundle b = new Bundle();
                b.putString(NAME, name);
                fragment.setArguments(b);

                if (type == FRAG_ADD) {
                    // Add the fragment to the 'fragment_container' ConstraintLayout
                    fragmentTransaction.add(R.id.frame_container, fragment);
                } else if (type == FRAG_REPLACE) {
                    fragmentTransaction.replace(R.id.frame_container, fragment);
                }
                fragmentTransaction.addToBackStack(name);
                fragmentTransaction.commit();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param filePath
     * @return
     */
    private String getJSONInput(String filePath) {
        String jsonIn = null;
        try {
            InputStream in = getAssets().open(filePath);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            jsonIn = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonIn;
    }

    @Override
    public void onBackPressed() {
        System.out.println(System.currentTimeMillis() + " GO BACK");
        super.onBackPressed();
    }


    @Override
    public void onDetach() {
        this.finish();
    }
}
