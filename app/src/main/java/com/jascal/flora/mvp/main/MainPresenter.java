package com.jascal.flora.mvp.main;

import com.jascal.flora.base.BasePresenter;

public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
}
