package com.jascal.flora.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.OAuthContract;
import com.jascal.flora.mvp.model.OAuthModel;
import com.jascal.flora.mvp.model.ShotsModel;
import com.jascal.flora.net.bean.Taken;

public class OAuthPresenter extends BasePresenter implements OAuthContract.presenter {
    private OAuthContract.view view;

    public OAuthPresenter(OAuthContract.view view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void oAuth(final Context context) {
        String code = (String) SpHelper.getInstance(context).get("code", "null");

        OAuthModel oAuthModel = new OAuthModel();
        oAuthModel.setCode(code);
        oAuthModel.setCallback(new OAuthModel.Callback() {
            @Override
            public void onSuccess(Taken taken) {
                SpHelper.getInstance(context).put("access_token", taken.getAccess_token());
                Log.d("dribbble", taken.getAccess_token());
                view.turn();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context, "get taken failed...", Toast.LENGTH_LONG).show();
                view.showError();
            }
        });
        oAuthModel.execute();
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
        shotsModel.get();
    }


}
