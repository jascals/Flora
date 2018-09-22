package com.jascal.flora.mvp;

import android.content.Context;

public interface OAuthContract {
    interface view {
        void setPresenter(OAuthContract.presenter presenter);

        void turn();

        void showError();

        void update(String shots);

        void error(String message);

    }

    interface presenter {
        void oAuth(Context context);

        void getShots(Context context);
    }
}
