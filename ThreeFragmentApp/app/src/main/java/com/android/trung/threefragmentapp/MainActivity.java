package com.android.trung.threefragmentapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


/**
 * The main activity of the sample app. When the app is launched, this activity is created with
 * first fragment. When user clicks "Next" button from any fragment, depending on fragment ID, this
 * activity will replace current fragment with next one in order 1st - 2nd - 3rd - 1st. Class name
 * of each fragment is aggregated, passed to new one, and displayed on its TextView.
 *
 * Communication between fragments is handled by interface OnItemSelectedListener which sits between
 * a fragment and parent activity. Any formation from a fragment to another must be passed to parent
 * activity (this activity) first.
 *
 * @author Trung
 */
@Preample(
        date = "01/30/2018" ,
        version = "1.0",
        lastModified = "01/30/2018" ,
        modifiedBy = "Trung",
        reviewers = {"Trung"}
)
public class MainActivity extends FragmentActivity implements OnItemSelectedListener, OnDetachListener {

    private static final short FRAG_ADD = 0;
    private static final short FRAG_REPLACE = 1;
    public static final String KEY_NAME = "Name";
    public static final String KEY_DATA = "Data";
    private final String mTag = this.getClass().getSimpleName();
    private int mFrag_ID;
    private Fragment mFrag;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_DATA, String.valueOf(mFrag_ID));
        super.onSaveInstanceState(outState);
    }
    /**
     * Set up necessary work to create the first fragment when the app is launched or resumed.
     *
     * @param savedInstanceState Saved instance state
     */
    @VisibleForTesting
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(mTag, "--------------  onCreate()  ---------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonIn;
        if (savedInstanceState == null) {
            Log.d(mTag, "savedInstanceState is null");
            jsonIn = getJSONInput("data/first_frag.json");
            FragmentManager fm = getSupportFragmentManager();
            mFrag = initializeNewFrag(FRAG_ADD, null, jsonIn, fm, savedInstanceState);
            System.out.println(this.getLocalClassName() + " created");
        }

    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putInt("frag_id", mFrag_ID);
//        super.onSaveInstanceState(outState);
//    }

    /**
     * Implementation of method onButtonSelected from interface OnItemSelectedListener.
     * Depending on the fragment ID, create the next fragment in order 1st - 2nd - 3rd - 1st.
     *
     * @param className Fragment class name passed from parent activity
     * @param fragID Fragment ID of this activity
     * @param savedInstanceState Saved instance state
     */
    @VisibleForTesting()
    @Override
    public void onButtonSelected(String className, int fragID, Bundle savedInstanceState) {

        FragmentManager fm = getSupportFragmentManager();
        String jsonIn;
        className = className + " ";
        switch (fragID) {
            case 0:     //First fragment button was selected
                Log.d(this.getClass().getSimpleName(), "First fragment's button was clicked");
                jsonIn = getJSONInput("data/second_frag.json");
                initializeNewFrag(FRAG_REPLACE, className, jsonIn, fm, savedInstanceState);
                mFrag_ID = 1;
                break;
            case 1:     //Second fragment button was selected
                Log.d(this.getClass().getSimpleName(), "Second fragment's button was clicked");
                jsonIn = getJSONInput("data/third_frag.json");
                initializeNewFrag(FRAG_REPLACE, className, jsonIn, fm, savedInstanceState);
                mFrag_ID = 2;
                break;
            case 2:
                Log.d(this.getClass().getSimpleName(), "Third fragment's button was clicked");
                jsonIn = getJSONInput("data/first_frag.json");
                initializeNewFrag(FRAG_REPLACE, className, jsonIn, fm, savedInstanceState);
                mFrag_ID = 0;
                break;
        }
    }

    /**
     * Initialize new fragment with information passed from parent activity.
     *
     * @param type Type of initialization. ADD or REPLACE only.
     * @param className Previous fragment class name passed from parent activity
     * @param jsonIn JSON input
     * @param fm    Fragment Manager
     * @param savedInstanceState Saved instance state
     */
    @VisibleForTesting()
    private Fragment initializeNewFrag(short type, String className,
                                   String jsonIn, @NonNull FragmentManager fm,
                                   @Nullable Bundle savedInstanceState) {
        Log.d(mTag, "Initialize new fragment with type: " + type + " - data: " + className);
        // Create a new Fragment to be placed in the activity layout
        Fragment fragment = null;
        try {
            JSONObject reader = new JSONObject(jsonIn);
            String name = reader.getString("name");
            String classpath = reader.getString("classpath");
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            /***********************************************************************************/
            //  Source: Google Android Developer
            // Check that the activity is using the layout version with
            // the fragment_container FrameLayout
            if (findViewById(R.id.frame_container) != null) {
                // However, if we're being restored from a previous state,
                // then we don't need to do anything and should return or else
                // we could end up with overlapping fragments.
//                if (savedInstanceState != null) {
//                    return;
//                }

                if (savedInstanceState != null) {
//                    Log.d(mTag, "savedInstanceState is not null");
//                    fragmentTransaction.add(R.id.frame_container,
//                            fm.findFragmentByTag(String.valueOf(mFrag_ID)), String.valueOf(mFrag_ID));
//                    fragmentTransaction.commit();
                    return null;
                } else {
                    Log.d(mTag, "savedInstanceState is null");
                    //Reflection
                    Class fragmentClass;
                    try {
                        fragmentClass = Class.forName(classpath);
                        fragment = (Fragment) fragmentClass.newInstance();


                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }

                    Bundle b = new Bundle();
                    if (savedInstanceState != null) {
                        b.putString(KEY_NAME, name);
                        b.putString(KEY_DATA, (String) savedInstanceState.get("name"));
                    } else {

                        b.putString(KEY_NAME, name);
                        b.putString(KEY_DATA, className);

                    }

                    assert fragment != null;
                    fragment.setArguments(b);

                    mFrag_ID = fragment.getId();
                    if (type == FRAG_ADD) {
                        // Add the fragment to the 'fragment_container' ConstraintLayout
                        fragmentTransaction.add(R.id.frame_container, fragment, String.valueOf(mFrag_ID));
                    } else if (type == FRAG_REPLACE) {
                        fragmentTransaction.replace(R.id.frame_container, fragment, String.valueOf(mFrag_ID));
                    }
                    fragmentTransaction.addToBackStack(name);
                    fragmentTransaction.commit();
                    return fragment;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /***********************************************************************************/
        return fragment;
    }

    /**
     * Get the input as string from a JSON file specified by filePath.
     *
     * @param filePath The relative path of the JSON file.
     * @return A JSON string
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Nullable
    @VisibleForTesting()
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

    /**
     * Implementation of interface onDetachListener method. Causes the app to exit when
     * back button is pressed while first fragment is in view.
     *
     *
     */
    @VisibleForTesting()
    @Override
    public void onDetach() {
        this.finish();
    }




}
