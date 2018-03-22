package com.trungdang.practice.fragmentnavigationdrawer.Util;

import android.content.Context;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Trung Dang on 3/9/2018.
 */

public class JsonParser {

    private JsonParser() {
    }

    /**
     * Get the input as string from a JSON file specified by filePath.
     *
     * @param filePath The relative path of the JSON file.
     * @return A JSON string
     */
    @Nullable
    public static String getJSONInput(Context context, String filePath) {
        String jsonIn = null;
        try {
            InputStream in = context.getAssets().open(filePath);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            jsonIn = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonIn;
    }

}
