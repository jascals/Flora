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
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.feed.FeedFragment;
import com.jascal.flora.mvp.profile.ProfileFragment;
import com.jascal.flora.mvp.read.ReadFragment;
import com.jascal.flora.mvp.setting.SettingFragment;
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
    private SettingFragment settingFragment;
    private FragmentManager manager;

    private void initFragment() {
        feedFragment = new FeedFragment();
        readFragment = new ReadFragment();
        profileFragment = new ProfileFragment();
        settingFragment = new SettingFragment();
        manager = getSupportFragmentManager();

        // restoreInstanceState when reStart if necessary
        if (getIntent().getBooleanExtra("navigation", false)) {
            drawerLayout.openDrawer(navigationView);
        }
        if (getIntent().getStringExtra("fragment").equals("SettingFragment")) {
            turn(settingFragment, false);
        } else {
            turn(feedFragment, false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
//            case R.id.darkness:
//                SpHelper.getInstance(MainActivity.this).put(Config.SP_THEME_KEY, false);
//                MainActivity.invoke(this, true, FeedFragment.class.getSimpleName());
//                break;
//            case R.id.lightness:
//                SpHelper.getInstance(MainActivity.this).put(Config.SP_THEME_KEY, true);
//                MainActivity.invoke(this, true, FeedFragment.class.getSimpleName());
//                break;
            case R.id.home:
                turn(feedFragment, true);
                break;
            case R.id.read:
                turn(readFragment, true);
                break;
            case R.id.profile:
                turn(profileFragment, true);
                break;
            case R.id.setting:
                turn(settingFragment, true);
                break;
        }
        return true;
    }

    private BaseFragment currentFragment;

    private void turn(BaseFragment fragment, boolean showAnim) {
        SpHelper.getInstance(this).put(Config.SP_FRAGMENT, fragment.getTAG());
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.content, fragment, fragment.getClass().getSimpleName());
        } else {
            transaction.hide(currentFragment).show(fragment);
        }
        currentFragment = fragment;
        transaction.commit();
        if (showAnim) {
            drawerLayout.closeDrawer(navigationView);
        }
    }

    public static void invoke(BaseActivity activity, boolean isDragged, String who) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("navigation", isDragged);
        intent.putExtra("fragment", who);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        activity.finish();
    }
}
