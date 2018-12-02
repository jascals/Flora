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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

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

    @BindView(R.id.progress)
    ProgressBar progressBar;

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
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.d("fragmentManager", "onDrawerClosed");
                firstShow(waitingFragment);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        // restoreInstanceState when reStart if necessary
        if (getIntent().getBooleanExtra("navigation", false)) {
            drawerLayout.openDrawer(navigationView);
        }
        if (getIntent().getStringExtra("fragment").equals("SettingFragment")) {
            firstShow(settingFragment);
        } else {
            firstShow(feedFragment);
        }
        progressBar.setVisibility(View.INVISIBLE);
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
                if (feedFragment.isAdded()) {
                    turn(feedFragment);
                    drawerLayout.closeDrawer(navigationView);
                    break;
                }
                waitingFragment = feedFragment;
                drawerLayout.closeDrawer(navigationView);
                showProgress();
                break;
            case R.id.read:
                if (readFragment.isAdded()) {
                    turn(readFragment);
                    drawerLayout.closeDrawer(navigationView);
                    break;
                }
                waitingFragment = readFragment;
                drawerLayout.closeDrawer(navigationView);
                showProgress();
                break;
            case R.id.profile:
                if (profileFragment.isAdded()) {
                    turn(profileFragment);
                    drawerLayout.closeDrawer(navigationView);
                    break;
                }
                waitingFragment = profileFragment;
                drawerLayout.closeDrawer(navigationView);
                showProgress();
                break;
            case R.id.setting:
                if (settingFragment.isAdded()) {
                    turn(settingFragment);
                    drawerLayout.closeDrawer(navigationView);
                    break;
                }
                Log.d("fragmentManager", "click setting");
                waitingFragment = settingFragment;
                drawerLayout.closeDrawer(navigationView);
                showProgress();
                break;
        }
        return true;
    }

    /**
     * when the fragment is first showed, process into this method, to display animation smoothly.
     */
    private void showProgress() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(currentFragment);
        transaction.commit();
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * the fragment to be show, see {showProgress()}
     */
    private BaseFragment waitingFragment;

    /**
     * first show method, see {showProgress()}.
     */
    private void firstShow(BaseFragment fragment) {
        if (fragment.isAdded()) {
            return;
        }
        Log.d("fragmentManager", "add fragment:" + fragment.getClass().getSimpleName());
        SpHelper.getInstance(this).put(Config.SP_FRAGMENT, fragment.getTAG());
        FragmentTransaction transaction = manager.beginTransaction();
        progressBar.setVisibility(View.INVISIBLE);
        transaction.add(R.id.content, fragment, fragment.getClass().getSimpleName());
        waitingFragment = fragment;
        currentFragment = fragment;
        transaction.commit();
    }

    private BaseFragment currentFragment;

    private void turn(BaseFragment fragment) {
        SpHelper.getInstance(this).put(Config.SP_FRAGMENT, fragment.getTAG());
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(currentFragment).show(fragment);
        progressBar.setVisibility(View.INVISIBLE);
        currentFragment = fragment;
        transaction.commit();
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
