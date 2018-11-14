package com.jascal.flora.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.utils.ThemeUtils;
import com.jascal.ophelia_api.Ophelia;

public class ProfileActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_profile);
        Ophelia.bind(this);
    }

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }

}
