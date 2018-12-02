package com.jascal.flora.widget.bottle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.jascal.flora.R;
import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.setting.SettingFragment;
import com.jascal.flora.widget.DrawableTextView;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 4:49 PM
 * @email jascal@163.com
 */
public class ThemeSwitcher extends ViewHolder {
    private DrawableTextView title;
    private TextView content;
    private Switch tap;

    private ThemeSwitcher(View itemView, final Handler handler) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        tap = itemView.findViewById(R.id.tap);
        boolean isChecked = (boolean) SpHelper.getInstance(itemView.getContext()).get(Config.SP_THEME_KEY, true);
        tap.setChecked(isChecked);
        tap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("themeSwitcher", "change state: " + isChecked);
                Message message = Message.obtain();
                message.what = SettingFragment.MSG_THEME_CHANGE;
                message.obj = isChecked;
                handler.sendMessage(message);
            }
        });
    }

    public static ThemeSwitcher create(ViewGroup parent, Handler handler) {
        return new ThemeSwitcher(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder_label, parent, false), handler);
    }
}
