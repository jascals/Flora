package com.jascal.flora.mvp.view.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.databinding.ActivityPhotoBinding;
import com.jascal.flora.mvp.PhotoContract;
import com.jascal.flora.mvp.presenter.PhotoPresenter;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.Feed;
import com.jascal.flora.utils.ThemeUtils;

public class PhotoActivity extends BaseActivity implements PhotoContract.View, View.OnClickListener {
    private PhotoContract.Presenter presenter;
    private ActivityPhotoBinding binding;
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        new PhotoPresenter(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        initToolbar();
        initData();

        String[] PERMISSIONS = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"};
        int permission = ContextCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 1) return;
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission Granted TODO
        } else {
            // Permission Denied TODO
        }
    }

    private void initData() {
        Intent intent = getIntent();
        Feed feed = intent.getParcelableExtra("feed");
        uri = Uri.parse(Config.BASE_IMAGE_PATH + feed.getImages().get(0).getUser_id() +
                Config.BASE_IMAGE_TAIL + feed.getImages().get(0).getImg_id() + ".jpg");
        binding.setFeed(feed);
        binding.setSite(feed.getSite());

        binding.photo.setImageURI(uri);
        binding.icon.setImageURI(Uri.parse(feed.getSite().getIcon()));
        binding.convertbt.setOnClickListener(this);
        binding.reset.setOnClickListener(this);
        binding.back.setOnClickListener(this);
        binding.share.setOnClickListener(this);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setPhoto(Uri uri) {
        Log.d("photo uri", uri.toString());
        binding.photo.setImageURI(uri);
    }

    public void resetPhoto() {
        Log.d("photo uri", uri.toString());
        binding.photo.setImageURI(uri);
    }

    public static void start(BaseActivity activity, Feed feed) {
        Intent intent = new Intent();
        intent.setClass(activity, PhotoActivity.class);
        intent.putExtra("feed", feed);
        activity.startActivity(intent);
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PhotoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.share:
                //TODO
                break;
            case R.id.convertbt:
                presenter.convert(uri, PhotoActivity.this.getApplicationContext(), PhotoPresenter.DRAK_MODEL);
                break;
            case R.id.reset:
                resetPhoto();
                break;
        }
    }
}
