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
    private OnItemSelectedListener mListener;

    public interface OnItemSelectedListener {
         boolean onButtonSelected(String text);
    }


    /**
     * Check to make sure on fragment attachment to activity, the activity
     * must implement the interface onItemSelectedListener.
     *
     * @param context The context to attach to
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnItemSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement interface " +
                    "FirstFragment.onItemSelectedListener.");
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO Receive data from 3rd fragment
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button button = getActivity().findViewById(R.id.button);
        if (button != null && button.getVisibility() == View.VISIBLE) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onButtonSelected("Button selected from 1st frag");
                }
            });
        }
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
