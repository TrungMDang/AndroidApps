package com.trungdang.practice.fragmentnavigationdrawer;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;

import java.util.Set;

/**
 * Created by Trung Dang on 3/14/2018.
 */

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance() {
        SettingsFragment newInstance = new SettingsFragment();
        return newInstance;
    }

    @Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
