package com.android.trung.threefragmentapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * The second fragment in 3 fragment sample app. It used 2 listener interfaces to communicate with
 * parent activity.
 *
 * Created by trung on 1/27/18.
 */
@Preample(
        date = "01/30/2018" ,
        version = "1.0",
        lastModified = "01/30/2018" ,
        modifiedBy = "Trung",
        reviewers = {"Trung"}
)
public class SecondFragment extends Fragment  {

    private static final int FRAG_ID = 1;

    private OnItemSelectedListener mListener;

    private String mData;


    /**
     * Check to make sure on fragment attachment to activity, the activity
     * must implement the interface onItemSelectedListener.
     *
     * @param context The context to attach to
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnItemSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement interface " +
                    "OnItemSelectedListener.");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_frag, container, false);
    }

    /**
     * When the parent activity is created and its onCreate method has finished set up a new
     * instance of a fragment, this method is called to add UI component to the fragment with data.
     * It also set up a listener for the button.
     *
     * @param savedInstanceState Saved instance state
     */
    @VisibleForTesting()
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle b = getArguments();
        if (b != null) {
            String mName = (String) b.get(MainActivity.NAME);
            final String className = this.getClass().getSimpleName();

            TextView tv = getActivity().findViewById(R.id.textView3);
            if (tv != null && tv.getVisibility() == View.VISIBLE) {
                tv.setText(mName);
                tv.setGravity(TextView.TEXT_ALIGNMENT_CENTER);

            }
            final TextView tv1 = getActivity().findViewById(R.id.textViewS_1);
            if (tv1 != null && tv1.getVisibility() == View.VISIBLE) {
                tv1.setText((String) b.get(MainActivity.DATA));
                tv1.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            }
            final Button button = getActivity().findViewById(R.id.button);
            if (button != null && button.getVisibility() == View.VISIBLE) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        assert tv1 != null;
                        mListener.onButtonSelected(tv1.getText() + className, FRAG_ID,
                                savedInstanceState);
                    }
                });
            }

        }
    }
}
