package com.jascal.flora.mvp.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.feed.FeedFragment;
import com.jascal.flora.mvp.profile.ProfileActivity;
import com.jascal.flora.mvp.setting.SettingActivity;
import com.jascal.flora.utils.ThemeUtils;
import com.jascal.flora.widget.DrawableTextView;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_annotation.OnClick;
import com.jascal.ophelia_api.Ophelia;

public class MainActivity extends BaseActivity implements MainContract.View,
        NavigationView.OnNavigationItemSelectedListener {
    private MainContract.Presenter presenter;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation)
    NavigationView navigationView;

    @BindView(R.id.title)
    DrawableTextView title;

    @OnClick(R.id.back)
    void openDrawer(View view) {
        drawerLayout.openDrawer(navigationView);
    }

    @OnClick(R.id.github)
    void openGithub(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(com.jascal.flora.net.Config.GITHUB));
        intent.setAction(Intent.ACTION_VIEW);
        this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ophelia.bind(this);
        new MainPresenter(this);
        initToolbar();
        initFragment();

        // restoreInstanceState if necessary
        if (getIntent().getBooleanExtra("navigation", false)) {
            drawerLayout.openDrawer(navigationView);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getHeaderView(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO custom setting
            }
        });
    }

    private void initFragment() {
        FeedFragment feedFragment = new FeedFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, feedFragment);
        transaction.commit();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.darkness:
                SpHelper.getInstance(MainActivity.this).put(Config.SP_THEME_KEY, false);
                MainActivity.reStart(this);
                break;
            case R.id.lightness:
                SpHelper.getInstance(MainActivity.this).put(Config.SP_THEME_KEY, true);
                MainActivity.reStart(this);
                break;
            case R.id.profile:
                ProfileActivity.start(this);
                break;
            case R.id.read:
                break;
            case R.id.setting:
                SettingActivity.start(this);
                break;
        }
        return true;
    }

    public static void reStart(MainActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("navigation", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        activity.finish();
    }
}
