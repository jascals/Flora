package com.jascal.flora.mvp.binding;

import com.jascal.flora.mvp.presenter.PhotoPresenter;

import dagger.Component;

@Component(modules = PhotoModule.class)
public interface PhotoComponent {
    PhotoPresenter getPresenter();
}
