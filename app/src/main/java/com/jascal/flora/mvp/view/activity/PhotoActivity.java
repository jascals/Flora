package com.jascal.flora.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;

public class PhotoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        initToolbar();
    }

    private void initToolbar() {
        
    }
}
