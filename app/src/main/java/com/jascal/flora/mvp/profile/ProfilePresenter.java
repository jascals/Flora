package com.jascal.flora.mvp.profile;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void request() {

    }
}
