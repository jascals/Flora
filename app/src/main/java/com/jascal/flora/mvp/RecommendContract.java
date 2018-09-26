package com.jascal.flora.mvp;

import android.content.Context;

import com.jascal.flora.net.bean.Feed;

import java.util.List;

public interface RecommendContract {

    interface view {
        void setPresenter(RecommendContract.presenter presenter);

        void update(List<Feed> feeds);

        void error(String message);

    }

    interface presenter {
        void getShots(Context context);
    }

}
