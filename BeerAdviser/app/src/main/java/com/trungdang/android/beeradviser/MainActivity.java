package com.trungdang.android.beeradviser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String mBeers = null;
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //Full screen, no status bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();

        //Hide the system nav bar
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("beers", mBeers);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mBeers = (String) savedInstanceState.get("beers");
        TextView brand = findViewById(R.id.brands);
        brand.setText(mBeers);
    }

    public void onClickFindBeer(View view) {
        TextView brand = findViewById(R.id.brands);
        Spinner s = findViewById(R.id.spinner);
        if (brand != null && s != null) {
            String beerType = String.valueOf(s.getSelectedItem());
            List<String> brands = BeerExpert.getBrands(this, beerType);
            StringBuilder sb = new StringBuilder();
            int len = brands.size();
            for (int i = 0; i < len; i++) {
                sb.append(brands.get(i));
                sb.append("\n");
            }
            brand.setText(sb.toString());
            mBeers = sb.toString();
        }
    }


    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.message);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
        Intent chooserIntent = Intent.createChooser(intent, "Choose an app");
        startActivity(chooserIntent);
    }
}
