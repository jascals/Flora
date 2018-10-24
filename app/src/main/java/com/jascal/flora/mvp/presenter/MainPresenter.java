package com.jascal.flora.mvp.presenter;

import android.content.Context;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.MainContract;
import com.jascal.flora.mvp.model.FeedModel;
import com.jascal.flora.net.bean.Feed;

import java.util.List;

public class MainPresenter extends BasePresenter implements MainContract.presenter {
    private MainContract.view view;

    public MainPresenter(MainContract.view view) {
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
