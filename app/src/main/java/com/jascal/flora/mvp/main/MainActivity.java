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
import com.jascal.flora.mvp.profile.ProfileFragment;
import com.jascal.flora.mvp.read.ReadFragment;
import com.jascal.flora.mvp.setting.SettingActivity;
import com.jascal.flora.utils.ThemeUtils;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_annotation.OnClick;
import com.jascal.ophelia_api.Ophelia;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation)
    NavigationView navigationView;

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

    private FeedFragment feedFragment;
    private ReadFragment readFragment;
    private ProfileFragment profileFragment;
    private FragmentManager manager;

    private void initFragment() {
        feedFragment = new FeedFragment();
        readFragment = new ReadFragment();
        profileFragment = new ProfileFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, feedFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.darkness:
                SpHelper.getInstance(MainActivity.this).put(Config.SP_THEME_KEY, false);
                MainActivity.invoke(this, true);
                break;
            case R.id.lightness:
                SpHelper.getInstance(MainActivity.this).put(Config.SP_THEME_KEY, true);
                MainActivity.invoke(this, true);
                break;
            case R.id.home:
                manager.beginTransaction()
                        .replace(R.id.content, feedFragment)
                        .commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.read:
                manager.beginTransaction()
                        .replace(R.id.content, readFragment)
                        .commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.profile:
                manager.beginTransaction()
                        .replace(R.id.content, profileFragment)
                        .commit();
                drawerLayout.closeDrawer(navigationView);
                break;
            case R.id.setting:
                SettingActivity.invoke(this);
                break;
        }
        return true;
    }

    public static void invoke(BaseActivity activity, boolean isDragged) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("navigation", isDragged);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        activity.finish();
    }
}
