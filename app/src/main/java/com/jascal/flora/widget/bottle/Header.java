package com.jascal.flora.widget.bottle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jascal.flora.R;
import com.jascal.flora.widget.DrawableTextView;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 4:30 PM
 * @email jascal@163.com
 */
public class Header extends ViewHolder {
    private static final String[] titles = new String[]{"theme choice", "profile setting"};
    private static final int[] icons = new int[]{R.mipmap.ic_theme, R.mipmap.ic_list};
    private DrawableTextView drawableTextView;

    private Header(View itemView) {
        super(itemView);
        drawableTextView = itemView.findViewById(R.id.title);
    }

    public void setRes(int position) {
        this.drawableTextView.setBitmap(icons[position], DrawableTextView.LEFT);
        this.drawableTextView.setText(titles[position]);
    }

    public static Header create(ViewGroup parent) {
        return new Header(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder_header, parent, false));
    }
}
