package com.trungdang.practice.gridviewdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Trung Dang on 3/6/2018.
 */

class ImageAdapter extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();
    private int convertViewCount = 0;
    private Context mContext;
    private LayoutInflater mInflater;
    private int[] images = {R.drawable.android, R.drawable.debian, R.drawable.ios,
        R.drawable.linux, R.drawable.mint, R.drawable.osx, R.drawable.raspbian,
        R.drawable.redhat, R.drawable.ubuntu, R.drawable.windows};
    private Bitmap[] b_images = new Bitmap[images.length];
    private Bitmap[] b_images_thumbs = new Bitmap[images.length];

    public ImageAdapter(Context context) {
        Log.v(TAG, "Constructing ImageAdapter with context: " +
                context.getClass().getSimpleName());
        this.mContext = context;
        mInflater = LayoutInflater.from(context);

        for (int i = 0; i < images.length; i++) {
            b_images[i] = BitmapFactory.decodeResource(context.getResources(), images[i]);
            b_images_thumbs[i] = Bitmap.createScaledBitmap(b_images[i],
                    100, 100, false);
        }
    }

    @Override
    public int getCount() {
        Log.v(TAG, "in getCount()");
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        Log.v(TAG, "in getItem() for position: " + i);
        return b_images[i];
    }

    @Override
    public long getItemId(int i) {
        Log.v(TAG, "in getItemId() for id: " + i);
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Log.v(TAG, "in getView() for position: " + position +
            " convertView is " +
                ((convertView == null) ? "null":"being recycled"));
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_grid_view, null);
            convertViewCount++;
            Log.v(TAG, convertViewCount + " convertViews have been created");
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setImageBitmap(b_images_thumbs[position]);
        holder.image.setImageResource(images[position]);

        return convertView ;
    }

    /**
     * Hold an image view. Don't have to repeatedly look up
     * the Views.
     */
    static class ViewHolder {
        ImageView image;
    }
}

