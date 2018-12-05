package com.jascal.flora.mvp.photo;

import android.content.Context;
import android.net.Uri;

import com.jascal.tensor.IFactory;

public interface PhotoContract {

    interface View {
        void setPresenter(PhotoContract.Presenter presenter);

        void setPhoto(Uri uri);

        void errorMsg(String msg);
    }

    interface Presenter {
        void convert(Uri uri, IFactory factory, Context context, int model);
    }
}
