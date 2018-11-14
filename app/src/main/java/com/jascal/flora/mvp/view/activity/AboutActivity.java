package com.jascal.flora.mvp.view.activity;

import android.content.Intent;

import com.jascal.flora.base.BaseActivity;

public class AboutActivity extends BaseActivity {

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }
}
