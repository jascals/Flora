package com.jascal.flora.mvp.read.model;

import android.util.Log;

import com.jascal.flora.base.BaseModel;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.gank.NewsResponse;
import com.jascal.flora.net.service.gank.NewsService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 3:22 PM
 * @email jascal@163.com
 */
public class ReadModel implements BaseModel {
    private Callback callback;

    public ReadModel setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public void getNews() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_GANK)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        NewsService newsService = retrofit.create(NewsService.class);
        newsService.getTodayNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != callback) {
                            callback.onFail(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        if (null != callback) {
                            callback.onSuccess(newsResponse);
                        }
                    }
                });
    }

    public interface Callback {
        void onSuccess(NewsResponse newsResponse);

        void onFail(String message);
    }
}
