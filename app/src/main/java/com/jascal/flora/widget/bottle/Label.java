package com.jascal.flora.widget.bottle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.jascal.flora.R;
import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.main.MainActivity;
import com.jascal.flora.mvp.setting.SettingFragment;
import com.jascal.flora.widget.DrawableTextView;

import java.util.logging.Handler;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 4:49 PM
 * @email jascal@163.com
 */
public class Label extends ViewHolder {
    private DrawableTextView title;
    private TextView content;
    private Switch tap;

    public Label(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        tap = itemView.findViewById(R.id.tap);
        tap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
    }

    public static Label create(ViewGroup parent) {
        return new Label(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder_label, parent, false));
    }
}
