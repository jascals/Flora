package com.jascal.flora.widget.bottle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jascal.flora.R;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 4:30 PM
 * @email jascal@163.com
 */
public class Header extends ViewHolder {
    private static final String[] sTitles = new String[]{"theme choice", "profile setting"};
    private ImageView icon;
    private TextView title;

    private Header(View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        title = itemView.findViewById(R.id.title);
    }

    public void setTitle(int position) {
        this.title.setText(sTitles[position]);
    }

    public static Header create(ViewGroup parent) {
        return new Header(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder_header, parent, false));
    }
}
