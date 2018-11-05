package com.jascal.flora.mvp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

public interface PhotoContract {

    interface View {
        void setPresenter(PhotoContract.Presenter presenter);

        void setPhoto(Bitmap bitmap);

        void errorMsg(String msg);
    }

    interface Presenter {
        void convert(Uri uri, Context context, int model);
    }
}
