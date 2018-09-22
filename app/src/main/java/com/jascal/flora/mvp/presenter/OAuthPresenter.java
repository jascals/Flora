package com.jascal.flora.mvp.presenter;

import android.content.Context;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.OAuthContract;
import com.jascal.flora.mvp.model.ShotsModel;

public class OAuthPresenter extends BasePresenter implements OAuthContract.presenter {
    private OAuthContract.view view;

    public OAuthPresenter(OAuthContract.view view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getShots(Context context) {
        String taken = (String) SpHelper.getInstance(context).get("access_token", "null");

        ShotsModel shotsModel = new ShotsModel();
        shotsModel.setTaken(taken);
        shotsModel.setCallback(new ShotsModel.Callback() {
            @Override
            public void onSuccess(String result) {
                view.update(result);
            }

            @Override
            public void onFailure(String message) {
                view.error(message);
            }
        });
        shotsModel.getFeed();
    }


}
