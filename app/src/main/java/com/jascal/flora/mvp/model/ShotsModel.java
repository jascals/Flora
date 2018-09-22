package com.jascal.flora.mvp.model;

import com.jascal.flora.base.BaseModel;
import com.jascal.flora.net.bean.Response;
import com.jascal.flora.net.service.ShotService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.jascal.flora.net.Config.BASE_URL;

public class ShotsModel implements BaseModel {
    private String taken;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private Callback callback;

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }


    public void getFeed() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        ShotService shotService = retrofit.create(ShotService.class);

        shotService.getFeedList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onSuccess(response.toString());
                    }
                });
    }

    public interface Callback {
        void onSuccess(String result);

        void onFailure(String message);
    }
}
