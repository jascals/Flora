package com.jascal.flora.mvp.view.activity;

import android.content.Intent;

import com.jascal.flora.base.BaseActivity;

public class ReadActivity extends BaseActivity {

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, ReadActivity.class);
        activity.startActivity(intent);
    }
}
