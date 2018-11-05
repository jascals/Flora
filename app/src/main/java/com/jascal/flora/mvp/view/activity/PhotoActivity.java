package com.jascal.flora.mvp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.databinding.ActivityPhotoBinding;
import com.jascal.flora.mvp.PhotoContract;
import com.jascal.flora.mvp.presenter.PhotoPresenter;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.Feed;
import com.jascal.ophelia_annotation.OnClick;
import com.jascal.ophelia_api.Ophelia;

public class PhotoActivity extends BaseActivity implements PhotoContract.View {
    private PhotoContract.Presenter presenter;
    private ActivityPhotoBinding binding;
    private Feed feed;
    private Uri uri;

    @OnClick(R.id.back)
    void back(View view) {
        this.finish();
    }

    @OnClick(R.id.share)
    void share(View view) {
        // TODO
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Ophelia.bind(this);
        new PhotoPresenter(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        initToolbar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        feed = intent.getParcelableExtra("feed");
        uri = Uri.parse(Config.BASE_IMAGE_PATH + feed.getImages().get(0).getUser_id() +
                Config.BASE_IMAGE_TAIL + feed.getImages().get(0).getImg_id() + ".jpg");
        binding.setFeed(feed);
        binding.photo.setImageURI(uri);
        binding.convertbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert(uri, PhotoActivity.this.getApplicationContext(), PhotoPresenter.DRAK_MODEL);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setPhoto(Bitmap bitmap) {
        binding.result.setImageBitmap(bitmap);
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PhotoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
