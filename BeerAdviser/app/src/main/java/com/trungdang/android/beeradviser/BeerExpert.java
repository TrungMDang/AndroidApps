package com.trungdang.android.beeradviser;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {

    public static List<String> getBrands(Context ctx, String color) {
        List<String> brands = new ArrayList<>();
        switch (color) {
            case "dark":
                brands.add(ctx.getResources()
                        .getString(R.string.lotr_beer_mordor));
                break;
            case "light":
                brands.add(ctx.getResources()
                        .getString(R.string.lotr_beer_rohan));
                brands.add(ctx.getResources()
                        .getString(R.string.shire_beer));
                break;
            case "brown":
                brands.add(ctx.getResources()
                        .getString(R.string.lotr_beer_rohan));
                brands.add(ctx.getResources()
                        .getString(R.string.lotr_beer_gondor));
                break;
            case "amber":
                brands.add(ctx.getResources()
                        .getString(R.string.skyrim_beer));
                break;
        }
        return brands;
    }
}
