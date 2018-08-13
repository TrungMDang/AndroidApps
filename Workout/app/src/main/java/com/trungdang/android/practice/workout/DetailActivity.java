package com.trungdang.android.practice.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "extra_workout_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment workoutDetailFragment =
                (WorkoutDetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.detail_frag);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
            if (workoutDetailFragment != null) {
                workoutDetailFragment.setWorkoutId(workoutId);
            }
        }
    }
}
