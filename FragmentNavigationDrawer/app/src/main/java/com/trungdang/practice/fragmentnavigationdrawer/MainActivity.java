package com.trungdang.practice.fragmentnavigationdrawer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.trungdang.practice.fragmentnavigationdrawer.Util.JsonParser;
import com.trungdang.practice.fragmentnavigationdrawer.dummy.DummyContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements FragmentInteractionAdapter,
        NewsFragment.OnListFragmentInteractionListener {

    private static final String KEY_NAME = "name";
    private static final String KEY_DATA = "data";
    private final String TAG = this.getClass().getSimpleName();

    private NavigationView mNavView;

    private Toolbar mToolbar;

    private Stack<String> mPrevFragName;

    private DrawerLayout mDrawer;

    private ActionBarDrawerToggle drawerToggle;

    private Fragment mFrag;

    private int backStackSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);


        mToolbar.setTitle(R.string.today_news);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);

        mNavView = this.findViewById(R.id.nvView);

        setupDrawerContent(mNavView);


        drawerToggle = setUpDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        mPrevFragName = new Stack<>();

        if (findViewById(R.id.flContent) != null) {
            if (savedInstanceState != null) {
                return;
            } else {

                String input = JsonParser.getJSONInput(this, "data/news_frag.json");
                mFrag = readFragmentData(input, null);
                final FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.flContent, mFrag, mFrag.getArguments().getString(KEY_NAME))
                        .addToBackStack(mFrag.getArguments().getString(KEY_NAME))
                        .commit();
                mPrevFragName.push(mFrag.getArguments().getString(KEY_NAME));
                backStackSize++;


            }
        }

        final FragmentManager fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {

                        // Update your UI here.
                        Log.d(TAG, "Previous back stack size: " + backStackSize);
                        Log.d(TAG, "New back stack size: " + fm.getBackStackEntryCount());
                        //Back button. Back stack entry removed
                        if (fm.getBackStackEntryCount() > 0 && fm.getBackStackEntryCount() < backStackSize) {
                            FragmentManager.BackStackEntry bSE =
                                    fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1);
                            Log.d(TAG, "bSE: " + bSE.toString());
                            mToolbar.setTitle(bSE.getName());
                            Menu menu = mNavView.getMenu();
                            int count = 0;
                            Log.d(TAG, "bSE name: " + bSE.getName());

                            //Compare name of back stack entry and title of menu item.
                            while (count < menu.size() && !menu.getItem(count).getTitle().toString().toLowerCase().equalsIgnoreCase(bSE.getName())) {
                                Log.d(TAG, "menuItem title: " + menu.getItem(count).getTitle());
                                count++;
                            }
                            menu.getItem(count).setChecked(true);
                        } else if (fm.getBackStackEntryCount() > 0 && fm.getBackStackEntryCount() > backStackSize) {
                            return;
                        } else {
                            mToolbar.setTitle(null);
                        }
                        backStackSize = fm.getBackStackEntryCount();
                    }

                });

    }

    @Override
    public void onAttachFragment(Fragment frag) {
        Log.d(TAG, "onAttachFragment()...");
        super.onAttachFragment(frag);
    }

    @Override
    public void onContentChanged() {
        Log.d(TAG, "onContentChanged()...");
        super.onContentChanged();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "Starting...");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        FragmentManager fm = getSupportFragmentManager();

//        //Clear back stack on activity starts
//        while (fm.getBackStackEntryCount() > 0){
//            fm.popBackStack();
//        }
        super.onStart();


    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        Log.d(TAG, "Restoring instance state...");

//        Log.d(TAG, "Restoring instance state...");
//        int id = (int) inState.get(KEY_NAME);
//        Log.d(TAG, "Saved data: " + id);
//        String input;
//        switch (id) {
//            case R.id.main_weather_b:
//                input = JsonParser.getJSONInput(this, "data/traffic_frag.json");
//                break;
//            case R.id.main_traffic_b:
//                input = JsonParser.getJSONInput(this, "data/drink_frag.json");
//                break;
//            case R.id.main_drink_b:
//                input = JsonParser.getJSONInput(this, "data/weather_frag.json");
//                break;
//            default:
//                Log.d(TAG, "Button was clicked. ID was: " + id + "...No fragment matched. Error");
//                input = JsonParser.getJSONInput(this, "data/weather_frag.json");
//                break;
//        }
//        mFrag = readFragmentData(input, );
        super.onRestoreInstanceState(inState);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate()...");
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "Resuming...");
        super.onResume();

    }

    @Override
    public void onPostResume() {
        Log.d(TAG, "Post resume...");
        super.onPostResume();

    }

    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "Attached to window...");
        super.onAttachedToWindow();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu...");
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public void onPause() {
        Log.d(TAG, "Pausing...");
        super.onPause();
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        Log.d(TAG, "Saving instance state...");
//        getSupportFragmentManager().putFragment(outState, KEY_NAME, mFrag);
//        super.onSaveInstanceState(outState);
//    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Stopping...");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setUpDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() for menu item.");
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_home:
                Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();

                String input = JsonParser.getJSONInput(this, "data/news_frag.json");
                Fragment frag = readFragmentData(input, null);
                mFrag = frag;
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(frag.getArguments().getString(KEY_NAME))
                        .replace(R.id.flContent, frag, frag.getArguments().getString(KEY_NAME))
                        .commit();
                backStackSize++;

//                Log.d(TAG, "Fragment name: " + frag.getArguments().getString(KEY_NAME));
//                mToolbar.setTitle(frag.getArguments().getString(KEY_NAME));
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentManager.BackStackEntry bSE = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1);
//                Menu menu = mNavView.getMenu();
//                int id = bSE.getId();
//                MenuItem mItem = menu.getItem(id % (menu.size() - 1));
//                if (mItem != null) mItem.setChecked(true);
                break;
            case R.id.action_settings:
                Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        onDrawerItemSelected(menuItem);
                        return true;
                    }
                });
    }

    /**
     * Drawer menu items interaction
     */
    private void onDrawerItemSelected(MenuItem menuItem) {
        //menuItem.setChecked(true);
        Fragment frag;
        String input;
        String fragName;
        switch (menuItem.getItemId()) {
            case R.id.nav_weather_frag:
                input = JsonParser.getJSONInput(this, "data/weather_frag.json");

                break;
            case R.id.nav_traffic_frag:
                input = JsonParser.getJSONInput(this, "data/traffic_frag.json");
                break;
            case R.id.nav_drink_frag:
                input = JsonParser.getJSONInput(this, "data/drink_frag.json");
                break;
            case R.id.nav_news_frag:
                input = JsonParser.getJSONInput(this, "data/news_frag.json");
                break;
            default:
                input = JsonParser.getJSONInput(this, "data/news_frag.json");
                break;
        }


        frag = readFragmentData(input, null);

        fragName = (String) frag.getArguments().get(KEY_NAME);

        mToolbar.setTitle(fragName);
        mPrevFragName.push(fragName);

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction()
                .setCustomAnimations(R.animator.enter_from_right, R.animator.exit_to_left,
                        R.animator.enter_from_left, R.animator.exit_to_right)
                .replace(R.id.flContent, frag, fragName)
                .addToBackStack(fragName)
                .commit();
        backStackSize++;

        Log.d(TAG, "Previous fragment name saved: " + mPrevFragName.peek());
        mFrag = frag;

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();


    }

    @Override
    public void onItemSelectedListener(int iD, String dataIn) {

        Fragment frag = null;
        Class fragClass;
        String input = null;
        String fragName = null;
        Log.d(TAG, "Comparing: ID: " + iD + "\n" +
                R.id.main_weather_b + "\n" +
                R.id.main_traffic_b + "\n" +
                R.id.main_drink_b);
        switch (iD) {
            case R.id.main_weather_b:
                input = JsonParser.getJSONInput(this, "data/traffic_frag.json");
                break;
            case R.id.main_traffic_b:
                input = JsonParser.getJSONInput(this, "data/drink_frag.json");
                break;
            case R.id.main_drink_b:
                input = JsonParser.getJSONInput(this, "data/weather_frag.json");
                break;
            default:
                Log.d(TAG, "Button was clicked. ID was: " + iD + "...No fragment matched. Error");
                input = JsonParser.getJSONInput(this, "data/weather_frag.json");
                break;
        }

        frag = readFragmentData(input, dataIn);
        fragName = (String) frag.getArguments().get(KEY_NAME);
        Log.d(TAG, "Fragment name from JSON: " + fragName);

        mToolbar.setTitle(fragName);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.flContent, frag, fragName)
                .addToBackStack(frag.getArguments().getString(KEY_NAME))
                .commit();
        backStackSize++;

        mFrag = frag;

    }


    private Fragment readFragmentData(String input, String dataIn) {
        JSONObject reader;
        Fragment frag = null;
        try {
            Log.d(TAG, "Reading json input from: " + input);
            Log.d(TAG, "Data in: " + dataIn);
            reader = new JSONObject(input);
            String fragName = reader.getString("name");
            Class fragClass = Class.forName(reader.getString("classPath"));
            frag = (Fragment) fragClass.newInstance();

            Bundle b = new Bundle();

            //Passing data from this Activity to the new fragment(data may come from another fragment)
            if (dataIn != null) {
                b.putString(KEY_DATA, dataIn);
            }

            b.putString(KEY_NAME, fragName);
            frag.setArguments(b);
            Log.d(TAG, "Passed data to next fragment: " + frag.getArguments().getString(KEY_DATA));


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return frag;


    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, "Item: " + item.toString());
        Toast.makeText(this, "Item #" + item.id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
