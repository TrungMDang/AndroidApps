package com.trungdang.android.practice.starbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DrinkActivity extends AppCompatActivity {

    private static final String TAG = "DrinkActivity";

    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Bundle extra = getIntent().getExtras();
        int drinkNo;
        Drink drink = null;
        if (extra != null) {
            drinkNo = getIntent().getExtras().getInt(EXTRA_DRINKNO);
            drink = Drink.drinks[drinkNo];
            Log.d(TAG, "Drink No: " + drinkNo);
        }

        ImageView photo= findViewById(R.id.photo);
        if (photo != null && drink != null) {
            photo.setImageResource(drink.getImageResId());
            photo.setContentDescription(drink.getName());
        }

        TextView name = findViewById(R.id.name);
        if (name != null && drink != null) {
            name.setText(drink.getName());
        }

        TextView description = findViewById(R.id.description);
        if (description != null && drink != null) {
            description.setText(drink.getDescription());
        }
    }
}
