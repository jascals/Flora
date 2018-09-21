package com.jascal.flora.mvp;

import android.content.Context;

public interface OAuthContract {
    interface view {
        void setPresenter(OAuthContract.presenter presenter);

        void turn();

        void showError();
    }

    interface presenter {
        void oAuth(Context context);
    }
}
