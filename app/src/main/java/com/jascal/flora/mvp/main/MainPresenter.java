package com.jascal.flora.mvp.main;

import android.content.Context;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.main.model.FeedModel;
import com.jascal.flora.net.bean.tc.Feed;

import java.util.List;

public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getShots(Context context) {
        String taken = (String) SpHelper.getInstance(context).get("access_token", "null");

        FeedModel feedModel = new FeedModel();
        feedModel.setTaken(taken);
        feedModel.setCallback(new FeedModel.Callback() {
            @Override
            public void onSuccess(List<Feed> result) {
                view.update(result);

            }

            @Override
            public void onFailure(String message) {
                view.error(message);
            }
        });
        feedModel.getFeed();
    }
}
