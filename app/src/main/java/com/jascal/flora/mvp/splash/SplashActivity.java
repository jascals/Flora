package com.jascal.flora.mvp.splash;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.mvp.feed.FeedFragment;
import com.jascal.flora.mvp.main.MainActivity;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 9:07 PM
 * @email jascal@163.com
 */
public class SplashActivity extends BaseActivity {
    private android.os.Handler mHandler = new android.os.Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new DelayRunnable(), 2000);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
    }

    class DelayRunnable implements Runnable {
        @Override
        public void run() {
            MainActivity.invoke(SplashActivity.this, false, FeedFragment.class.getSimpleName());
        }
    }
}
