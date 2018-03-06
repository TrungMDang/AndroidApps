package com.trungdang.practice.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private ArrayAdapter<String> listAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListView();
    }

    private void setupListView() {
        lv1 = this.findViewById(R.id.lv);
        final String[] colors = new String[]{"Red", "Green", "Blue", "Orange", "Purple", "Change colors"};
        final List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(colors));

        //listAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, list);
        lv1.setAdapter(listAdapter1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String itemVal = (String) lv1.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), itemVal, Toast.LENGTH_LONG).show();
                if (itemVal == "Change colors") {
                    list.clear();
                    list.add("Pink");
                    list.add("Yellow");
                    list.add("Sky Blue");
                    listAdapter1.notifyDataSetChanged();
                }
            }
        });

        lv1.setChoiceMode(lv1.CHOICE_MODE_MULTIPLE);
    }

    public void onClickedListener(View v) {
        int length = lv1.getCount();
        SparseBooleanArray viewItems = lv1.getCheckedItemPositions();
        for (int i = 0; i < length; i++) {
            if (viewItems.get(i)) {
                String selectedColor = (String) lv1.getItemAtPosition(i);
                Log.v("INFO-ListView", selectedColor + " is checked at position: " + i);
            }
        }

    }

}
