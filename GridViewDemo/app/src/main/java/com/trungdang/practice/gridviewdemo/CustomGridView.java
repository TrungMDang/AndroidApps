package com.trungdang.practice.gridviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

/**
 * Created by Trung Dang on 3/6/2018.
 */

public class CustomGridView extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.custom_grid_view);

        GridView gv = this.findViewById(R.id.gv);
        ImageAdapter id = new ImageAdapter(this);
        gv.setAdapter(id);
    }
}
