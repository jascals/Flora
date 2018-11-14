package com.jascal.flora.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.utils.ThemeUtils;
import com.jascal.flora.widget.Draggable.DraggableSquareView;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_api.Ophelia;

public class ProfileActivity extends BaseActivity {
    private int imageStatus;
    private boolean isModify;

    @BindView(R.id.drag_square)
    DraggableSquareView dragSquare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Ophelia.bind(this);
        Fresco.initialize(this);

        initToolbar();
        initData();
    }

    private void initData() {
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 0) {
            dragSquare.fillItemImage(imageStatus, data.getData().toString(), isModify);
        }
    }

    public void pickImage(int status, boolean draggable) {
        this.imageStatus = status;
        this.isModify = draggable;
        startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), 0);
    }
}
