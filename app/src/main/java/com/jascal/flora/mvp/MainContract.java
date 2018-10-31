package com.jascal.flora.mvp;

import android.content.Context;

import com.jascal.flora.net.bean.Feed;

import java.util.List;

public interface MainContract {

    interface View {
        void setPresenter(MainContract.Presenter presenter);

        void update(List<Feed> feeds);

        void error(String message);

    }

    interface Presenter {
        void getShots(Context context);
    }

}
