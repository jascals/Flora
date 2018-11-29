package com.jascal.flora.widget.bottle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jascal.flora.R;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 5:16 PM
 * @email jascal@163.com
 */
public class List extends ViewHolder {
    private TextView setting;
    private TextView download;

    public List(View itemView) {
        super(itemView);
        setting = itemView.findViewById(R.id.progress_setting);
        download = itemView.findViewById(R.id.download);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
    }

    public static List create(ViewGroup parent) {
        return new List(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder_list, parent, false));
    }
}
