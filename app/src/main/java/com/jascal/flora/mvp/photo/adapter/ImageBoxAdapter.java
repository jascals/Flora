package com.jascal.flora.mvp.photo.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jascal.flora.mvp.photo.PhotoActivity;
import com.jascal.flora.widget.StyledGrid.ImageBox;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/26 3:45 PM
 * @email jascal@163.com
 */
public class ImageBoxAdapter extends BaseAdapter {
    private final int NUM_STYLES = 26;
    private final ImageBox[] items = new ImageBox[NUM_STYLES];

    public ImageBoxAdapter(PhotoActivity photoActivity) {
        for (int i = 0; i < NUM_STYLES; ++i) {
            final ImageBox box = new ImageBox(photoActivity);
            final Bitmap bm = getBitmapFromAsset(photoActivity, "models/thumbnails/style" + i + ".jpg");
            box.setImageBitmap(bm);
            items[i] = box;
        }
    }

    @Override
    public int getCount() {
        return NUM_STYLES;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            return convertView;
        }
        return (View) getItem(position);
    }

    private static Bitmap getBitmapFromAsset(Context context, String filePath) {
        final AssetManager assetManager = context.getAssets();

        Bitmap bitmap = null;
        try {
            final InputStream inputStream = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (final IOException e) {
            Log.d("stylized", "Error opening bitmap!", e);
        }

        return bitmap;
    }
}
