package com.jascal.flora.mvp.model;

import com.jascal.flora.base.BaseModel;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.Taken;
import com.jascal.flora.net.service.TakenService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OAuthModel implements BaseModel {
    private Callback callback;
    private String code;

    public void execute() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.TAKEN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        TakenService takenService = retrofit.create(TakenService.class);

        takenService.getTaken(Config.CLIENT_ID, Config.CLIENT_SECRET, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Taken>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(Taken taken) {
                        callback.onSuccess(taken);
                    }
                });
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public interface Callback {
        void onSuccess(Taken taken);

        void onFailure(String message);
    }
}
