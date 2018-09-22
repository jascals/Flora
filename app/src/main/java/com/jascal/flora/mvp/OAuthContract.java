package com.jascal.flora.mvp;

import android.content.Context;

public interface OAuthContract {
    interface view {
        void setPresenter(OAuthContract.presenter presenter);

        void update(String shots);

        void error(String message);

    }

    interface presenter {
        void getShots(Context context);
    }
}
