package com.android.trung.threefragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by trung on 1/27/18.
 */

public class SecondFragment extends Fragment  {

    public static final int FRAG_ID = 1;

    private OnItemSelectedListener mListener;

    private String mName;

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
                    "OnItemSelectedListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_frag, container, false);
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle b = getArguments();
        if (b != null) {
            mName = (String) b.get(MainActivity.NAME);
            System.out.println("mName: " + mName);
        }

        TextView tv = getActivity().findViewById(R.id.textView3);
        if (tv != null && tv.getVisibility() == View.VISIBLE) {
            tv.setText(mName);
            tv.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
        }

        final Button button = getActivity().findViewById(R.id.button);
        if (button != null && button.getVisibility() == View.VISIBLE) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onButtonSelected("Button second selected", FRAG_ID, savedInstanceState);
                }
            });
        }
    }
}
