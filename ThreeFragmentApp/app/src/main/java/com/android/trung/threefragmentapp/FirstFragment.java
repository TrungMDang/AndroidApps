package com.android.trung.threefragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by trung on 1/27/18.
 */

public class FirstFragment extends Fragment {

    /**
     * Use to listen for item selected and communicate with parent activity.
     */
    private onItemSelected listener;

    public interface onItemSelected {
         boolean onButtonSelected(String text);
    }

    /**
     * Guide:
     * https://github.com/codepath/android_guides/wiki/Creating-and-Using-Fragments#fragment-listener
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onItemSelected) {
            listener = (onItemSelected) context;
            System.out.println("Listener created.");
        } else {
            throw new ClassCastException(context.toString() + "Must implement interface " +
                    "FirstFragment.onItemSelected.");
        }
    }

    public void onButtonSelected(View view) {
        listener.onButtonSelected("button selected");
        System.out.println("button selected");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO Receive data from 3rd fragment
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.first_frag, container, false);
    }

}
