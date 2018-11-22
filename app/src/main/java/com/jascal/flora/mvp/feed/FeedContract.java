package com.jascal.flora.mvp.feed;

import android.content.Context;

import com.jascal.flora.net.bean.tc.Feed;

import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 2:53 PM
 * @email jascal@163.com
 */
public interface FeedContract {

    interface View {
        void setPresenter(FeedContract.Presenter presenter);

        void update(List<Feed> feeds);

        void error(String message);

    }

    interface Presenter {
        void getShots(Context context);
    }

}
