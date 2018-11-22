package com.jascal.flora.mvp.photo;

import android.content.Context;
import android.net.Uri;

public interface PhotoContract {

    interface View {
        void setPresenter(PhotoContract.Presenter presenter);

        void setPhoto(Uri uri);

        void errorMsg(String msg);
    }

    interface Presenter {
        void convert(Uri uri, Context context, int model);
    }
}
