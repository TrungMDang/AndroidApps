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
 *
 *
 */
public class FirstFragment extends Fragment {

    public static final int FRAG_ID = 0;
    /**
     * Use to listen for item selected and communicate with parent activity.
     */
    private OnItemSelectedListener mListener;

    private OnDetachListener mDetachListener;

    private String mName;

    private String mData;
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
            mDetachListener = (OnDetachListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement interface " +
                    "OnItemSelectedListener and OnDetachListener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            mData = (String) savedInstanceState.get(MainActivity.DATA);
        //TODO Receive data from 3rd fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.first_frag, container, false);

    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Bundle b = getArguments();
        if (b != null) {
            mName = (String) b.get(MainActivity.NAME);
            System.out.println("mName: " + mName);
            final String className = this.getClass().getSimpleName();
            TextView tv = getActivity().findViewById(R.id.textView2);
            if (tv != null && tv.getVisibility() == View.VISIBLE) {
                tv.setText(mName);
                tv.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            }
            tv = getActivity().findViewById(R.id.textViewF_1);
            if (tv != null && tv.getVisibility() == View.VISIBLE) {
                tv.setText((String) b.get(MainActivity.DATA));
                tv.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            }
            final Button button = getActivity().findViewById(R.id.button);
            if (button != null && button.getVisibility() == View.VISIBLE) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onButtonSelected(className, FRAG_ID,
                                savedInstanceState);
                    }
                });
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println(this.getClass().getName() + " started");

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(this.getClass().getName() + " resume");

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println(System.currentTimeMillis() + " " + this.getClass().getName() + " paused");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println(System.currentTimeMillis() + " " + this.getClass().getName() + " stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println(System.currentTimeMillis() + " " + this.getClass().getName() + " view destroyed.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(System.currentTimeMillis() + " " + this.getClass().getName() + " destroyed");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDetachListener.onDetach();
    }

}
