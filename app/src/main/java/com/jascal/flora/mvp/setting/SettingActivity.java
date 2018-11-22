package com.jascal.flora.mvp.setting;

import android.content.Intent;

import com.jascal.flora.base.BaseActivity;

public class SettingActivity extends BaseActivity {

    public static void invoke(BaseActivity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }
}
