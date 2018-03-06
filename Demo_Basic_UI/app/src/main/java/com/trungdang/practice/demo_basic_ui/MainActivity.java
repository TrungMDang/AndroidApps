package com.trungdang.practice.demo_basic_ui;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        TimePickerFragment.OnFragmentInteractionListener,
        DatePickerFragment.OnFragmentInteractionListener {

    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTextView();
        setEditText();
        setAutoCompleteTextView();
        setupMultiAutoCompleteTextView();
        setupButtons();
        setupCheckboxes();
        setupSwitch();
        setupRadioButton();


    }


    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(this.getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getFragmentManager(), "datePicker");
    }

    private void setupRadioButton() {
        RadioGroup rg = this.findViewById(R.id.rg);
        final String tag = "INFO-RadioGroup";
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case -1:
                        Log.v(tag,"Choices cleared");
                        break;
                    case R.id.rb_excel:
                        Log.d(tag, "Excellent was selected");
                        break;
                    case R.id.rb_good:
                        Log.d(tag, "Good was selected");
                        break;
                    case R.id.rb_poor:
                        Log.d(tag, "Poor was selected");
                        break;
                    default:
                        Log.d(tag, "Error");
                        break;
                }
            }
        });
    }

    private void setupSwitch() {
        sw = this.findViewById(R.id.sw);
        sw.setText(R.string.switch_off);
        sw.setOnCheckedChangeListener(this);
    }

    private void setupCheckboxes() {
        CheckBox cb = this.findViewById(R.id.cb_oreo);
        cb.setText(R.string.oreo);

        cb = this.findViewById(R.id.cb_nougat);
        cb.setText(R.string.nougat);

        cb = this.findViewById(R.id.cb_kitkat);
        cb.setText(R.string.kitkat);
    }

    public void checkBoxClickHandler(View v) {
        switch(v.getId()) {
            case R.id.cb_oreo:
                Log.i("INFO - Checkbox", "Oreo is " + (((CheckBox) v).isChecked() ?
                        "check" : "not checked"));
                break;
            case R.id.cb_kitkat:
                Log.i("INFO - Checkbox", "Kitkat is " + (((CheckBox) v).isChecked() ?
                        "check" : "not checked"));
                break;
            case R.id.cb_nougat:
                Log.i("INFO - Checkbox", "Nougat is " + (((CheckBox) v).isChecked() ?
                        "check" : "not checked"));
                break;
        }
    }


    private void setupButtons() {
        Button bb = this.findViewById(R.id.button_basic);
        bb.setText(R.string.button_basic);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com"));
                startActivity(i);
            }
        });

        ImageButton ib = this.findViewById(R.id.button_image1);
        ib.setImageResource(R.drawable.android);
        ib.setContentDescription(R.string.button_image + " ");
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(i);
            }
        });

        ib = this.findViewById(R.id.button_image2);
        ib.setImageResource(R.drawable.android);
        ib.setContentDescription(R.string.button_image + " ");
        ib.setBackground(null);

        ToggleButton tb = this.findViewById(R.id.button_toggle);
        tb.setText(null);
        tb.setTextOn("Awake!");
        tb.setTextOff("Sleeping");

    }

    private void setupMultiAutoCompleteTextView() {
        MultiAutoCompleteTextView mactv = this.findViewById(R.id.mactv);
        mactv.setHint(R.string.mactv_hint);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"English",
                        "Chinese", "Vietnamese", "Spanish"});
        mactv.setAdapter(aa);
        mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        mactv.requestFocus();
    }


    private void setAutoCompleteTextView() {
        AutoCompleteTextView actv = this.findViewById(R.id.actv);
        actv.setHint(R.string.actv_hint);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, new String[] {"English",
                    "Chinese", "Vietnamese", "Spanish"});
        actv.setAdapter(aa);
    }

    private void setEditText() {
        EditText et = this.findViewById(R.id.et);
        et.setHint(R.string.et_hint);
    }

    private void setTextView() {
        TextView tv = this.findViewById(R.id.tv);
        tv.setAutoLinkMask(Linkify.ALL);
        tv.setText(R.string.tv_text);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            sw.setText(R.string.switch_on);
        } else {
            sw.setText(R.string.switch_off);
        }

    }

    @Override
    public void onTimeFragmentInteraction(String className, int hour, int minute) {
        Log.d("INFO-Frag", className + " was done with data Hr: " +
                hour + " Min: " + minute);
        Button b = this.findViewById(R.id.button_time);
        b.setText("Time Picked: " + hour + ":" + minute);
    }

    @Override
    public void onDateFragmentInteraction(String className, int year, int month, int day) {
        Button b = this.findViewById(R.id.button_date);
        b.setText("Date Picked: "+ year + "/" + month + "/" + day);
    }
}
