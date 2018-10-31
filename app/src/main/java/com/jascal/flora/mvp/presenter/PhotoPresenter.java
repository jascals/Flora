package com.jascal.flora.mvp.presenter;

import com.jascal.flora.mvp.PhotoContract;

public class PhotoPresenter implements PhotoContract.Presenter {
    private PhotoContract.View view;

    public PhotoPresenter(PhotoContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
}
