package com.jascal.flora.mvp.feed;

import android.content.Context;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.feed.model.FeedModel;
import com.jascal.flora.net.bean.tc.Feed;

import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 2:54 PM
 * @email jascal@163.com
 */
public class FeedPresenter extends BasePresenter implements FeedContract.Presenter {
    private FeedContract.View view;

    public FeedPresenter(FeedContract.View view) {
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
