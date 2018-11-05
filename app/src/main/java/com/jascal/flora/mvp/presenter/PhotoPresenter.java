package com.jascal.flora.mvp.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.mvp.PhotoContract;
import com.jascal.flora.mvp.model.TensorModel;

public class PhotoPresenter extends BasePresenter implements PhotoContract.Presenter {
    private PhotoContract.View view;

    public static final int DRAK_MODEL = 0;

    public PhotoPresenter(PhotoContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void convert(Uri uri, Context context, int model) {
        switch (model) {
            case DRAK_MODEL:
                TensorModel tensorModel = new TensorModel(context);
                tensorModel.setCallback(new TensorModel.Callback() {
                    @Override
                    public void onSuccess(Bitmap result) {
                        view.setPhoto(result);
                    }

                    @Override
                    public void onFailure(Uri uri, Throwable throwable) {
                        view.errorMsg(throwable.getMessage());
                    }

                    @Override
                    public void onCancel(Uri uri) {
                        view.errorMsg("canceled.");
                    }
                });
                tensorModel.convert(uri);
                break;
        }
    }
}
