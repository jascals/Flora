package com.jascal.flora.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.mvp.MainContract;
import com.jascal.flora.mvp.presenter.MainPresenter;
import com.jascal.flora.mvp.view.adapter.FeedAdapter;
import com.jascal.flora.net.bean.Feed;
import com.jascal.flora.widget.DrawableTextView;
import com.jascal.flora.widget.SpaceItemDecoration;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_annotation.OnClick;
import com.jascal.ophelia_api.Ophelia;

import java.util.List;

public class MainActivity extends BaseActivity implements MainContract.view {
    private MainContract.presenter presenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation)
    NavigationView navigationView;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.title)
    DrawableTextView title;

    @OnClick(R.id.back)
    void openDrawer(View view) {
        drawerLayout.openDrawer(navigationView);

    }

    private void setNavItemListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // TODO item event
                item.setChecked(true);
                return true;
            }
        });
    }

    private void setHeadViewClick() {
        navigationView.getHeaderView(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO custom setting
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavItemListener();
        setHeadViewClick();
    }

    private void initData() {
        presenter.getShots(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ophelia.bind(this);
        new MainPresenter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initToolbar();
        initData();
    }

    @Override
    public void setPresenter(MainContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(List<Feed> feeds) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "feeds num is " + feeds.size(), Toast.LENGTH_SHORT).show();

        FeedAdapter feedAdapter = new FeedAdapter(feeds);
        recyclerView.setAdapter(feedAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
    }

    @Override
    public void error(String message) {
        Toast.makeText(getApplicationContext(), "get shots error:" + message, Toast.LENGTH_LONG).show();
    }
}
