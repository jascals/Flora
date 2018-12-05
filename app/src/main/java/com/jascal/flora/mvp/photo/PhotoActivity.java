package com.jascal.flora.mvp.photo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.databinding.ActivityPhotoBinding;
import com.jascal.flora.mvp.photo.adapter.ImageBoxAdapter;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.tc.Feed;
import com.jascal.flora.utils.ThemeUtils;
import com.jascal.tensor.IFactory;

import java.util.HashMap;
import java.util.Map;

public class PhotoActivity extends BaseActivity implements PhotoContract.View, View.OnClickListener {
    private PhotoContract.Presenter presenter;
    private ActivityPhotoBinding binding;

    private Uri originPath;
    private Map<Integer, Uri> resultMap;
    private int styleIndex;
    private boolean isRunning = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        new PhotoPresenter(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        initToolbar();
        initData();
        initAIDL();

        String[] PERMISSIONS = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"};
        int permission = ContextCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
    }

    private IFactory factory;

    private void initAIDL() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAKERSERVICE");
        intent.setPackage("com.jascal.tensor");
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            factory = IFactory.Stub.asInterface(iBinder);
            if (factory != null) {
                Toast.makeText(PhotoActivity.this, "ready", Toast.LENGTH_SHORT).show();
//                try {
//                    factory.stylizeImage(null, 0);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
            } else {
                Log.d("launched", "factory==null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
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

    @SuppressLint("UseSparseArrays")
    private void initData() {
        Intent intent = getIntent();
        Feed feed = intent.getParcelableExtra("feed");
        originPath = Uri.parse(Config.BASE_IMAGE_PATH + feed.getImages().get(0).getUser_id() +
                Config.BASE_IMAGE_TAIL + feed.getImages().get(0).getImg_id() + ".jpg");
        binding.setFeed(feed);
        binding.setSite(feed.getSite());

        binding.progress.setVisibility(View.INVISIBLE);
        binding.photo.setImageURI(originPath);
        binding.icon.setImageURI(Uri.parse(feed.getSite().getIcon()));
        binding.reset.setOnClickListener(this);
        binding.back.setOnClickListener(this);
        binding.share.setOnClickListener(this);

        binding.grid.setAdapter(new ImageBoxAdapter(this));
        binding.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isRunning) return;
                styleIndex = position;
                Uri uri = resultMap.get(position);
                if (uri != null) {
                    binding.photo.setImageURI(uri);
                } else {
                    isRunning = true;
                    binding.progress.setVisibility(View.VISIBLE);
                    presenter.convert(originPath, factory, PhotoActivity.this.getApplicationContext(), position);
                }
            }
        });

        resultMap = new HashMap<>();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setPhoto(Uri uri) {
        Log.d("photo uri", uri.toString());
        isRunning = false;
        resultMap.put(styleIndex, uri);
        binding.photo.setImageURI(uri);
        binding.progress.setVisibility(View.INVISIBLE);
    }

    /**
     * invoke method
     */
    public static void invoke(Activity activity, Feed feed) {
        Intent intent = new Intent();
        intent.setClass(activity, PhotoActivity.class);
        intent.putExtra("feed", feed);
        activity.startActivity(intent);
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        isRunning = false;
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
            case R.id.reset:
                Log.d("photo uri", originPath.toString());
                binding.photo.setImageURI(originPath);
                break;
        }
    }
}
