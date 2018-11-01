package com.jascal.flora.mvp.presenter;

import com.jascal.flora.mvp.PhotoContract;

import javax.inject.Inject;

public class PhotoPresenter implements PhotoContract.Presenter {
    private PhotoContract.View view;

    @Inject
    public PhotoPresenter(PhotoContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
}
