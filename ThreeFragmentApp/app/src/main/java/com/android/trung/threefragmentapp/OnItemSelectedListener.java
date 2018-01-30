package com.android.trung.threefragmentapp;

import android.os.Bundle;

/**
 * Created by trung on 1/29/2018.
 */
@Preample(
        date = "01/30/2018" ,
        version = "1.0",
        lastModified = "01/30/2018" ,
        modifiedBy = "Trung",
        reviewers = {"Trung"}
)
interface OnItemSelectedListener {
    @SuppressWarnings("SameReturnValue")
    void onButtonSelected(String text, int frag, Bundle savedInstanceState);

}
