package com.jascal.flora.mvp.profile;

public interface ProfileContract {

    interface View {
        void setPresenter(ProfileContract.Presenter presenter);
    }

    interface Presenter {
        void request();
    }
}
