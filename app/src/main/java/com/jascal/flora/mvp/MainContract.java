package com.jascal.flora.mvp;

import android.content.Context;

public interface MainContract {
    interface view {
        void setPresenter(MainContract.presenter presenter);

        void update(String shots);

        void error(String message);

    }

    interface presenter {
        void getShots(Context context);
    }
}
