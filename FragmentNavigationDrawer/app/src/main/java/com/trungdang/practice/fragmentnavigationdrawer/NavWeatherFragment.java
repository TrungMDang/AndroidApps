package com.trungdang.practice.fragmentnavigationdrawer;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.trungdang.practice.fragmentnavigationdrawer.Constants.Constants.EMPTY_STRING;
import static com.trungdang.practice.fragmentnavigationdrawer.Constants.Constants.KEY_DATA;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavWeatherFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentInteractionAdapter mAdapter;

    private Bundle mData;

    private static final String TAG = "NavWeatherFragment";

    public NavWeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NavWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavWeatherFragment newInstance() {
        Log.d(TAG, "New instance of Weather Fragment created");
        NavWeatherFragment fragment = new NavWeatherFragment();
        return fragment;
    }


    public void setData(String data) {
        this.mData.putString(TAG, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mData = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nav_weather_frag, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated() method...");
        super.onActivityCreated(savedInstanceState);

        TextView tv1 = getActivity().findViewById(R.id.main_weather_tv1);
        TextView tv2 = getActivity().findViewById(R.id.main_weather_tv2);
        Button b = getActivity().findViewById(R.id.main_weather_b);
        b.setOnClickListener(this);

        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState is not null");
            Log.d(TAG, savedInstanceState.toString());
            //mData.putString(KEY_DATA, (String) savedInstanceState.get(KEY_DATA));
            tv2.setText((String) savedInstanceState.get(KEY_DATA));

        } else {
            Log.d(TAG, "savedInstanceState is null");
            if (getArguments() != null) {
                tv2.setText(this.getArguments().getString(KEY_DATA));
                //mData.putString(KEY_DATA, tv1.getText() == null ? EMPTY_STRING : tv1.getText().toString());

                //Store data from TextView to pass to next fragment creation
                //Log.d(TAG, "Stored data: " + tv2.getText() + (tv1.getText() == null ? EMPTY_STRING : tv1.getText().toString()));
                //mData.putString(KEY_DATA, tv2.getText() + " " + (tv1.getText() == null ? EMPTY_STRING : tv1.getText().toString()));
            }

        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.d(TAG, "view state restored...");
        if (savedInstanceState != null) {
            mData.putString(KEY_DATA, (String) savedInstanceState.get(KEY_DATA));
        }
        super.onViewStateRestored(savedInstanceState);
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
            mAdapter = (FragmentInteractionAdapter) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement interface " +
                    "FragmentInteractionAdapter");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView tv2 = getActivity().findViewById(R.id.main_weather_tv2);
        if (tv2 == null) {
            Log.d(TAG, "tv2 is null");
            outState.putString(KEY_DATA, (String) mData.get(KEY_DATA));
        } else {
            Log.d(TAG, "tv2 is not null");
            outState.putString(KEY_DATA, (String) tv2.getText());
        }

        //outState.putString(KEY_DATA, (String) mData.get(KEY_DATA));
        Log.d(TAG, "Saving instance state. Data: " + outState.getString(KEY_DATA));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Button was clicked. Calling interface of " + this.getClass().getSimpleName() + " on ID: " + this.getId());
        Log.d(TAG, "Data: " + mData.getString(KEY_DATA));
        TextView tv1 = getActivity().findViewById(R.id.main_weather_tv1);
        TextView tv2 = getActivity().findViewById(R.id.main_weather_tv2);
        mData.putString(KEY_DATA, tv2.getText() + " " + (tv1.getText() == null ? EMPTY_STRING : tv1.getText().toString()));
        mAdapter.onItemSelectedListener(v.getId(), mData.getString(KEY_DATA));
    }
}
