package com.jascal.flora.mvp.model;

import android.util.Log;

import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.Article;
import com.jascal.flora.net.service.ArticleService;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
 * @data on 2018/11/16 4:38 PM
 * @email jascal@163.com
 */
public class ArticleModel {

    public void getArticle() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_ARTICLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        ArticleService articleService = retrofit.create(ArticleService.class);
        articleService.getArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RequestBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("article", "error "+e.getMessage());
                        Log.d("article", "error "+e.toString());
                    }

                    @Override
                    public void onNext(RequestBody article) {
                        Log.d("article", "success "+article.toString());

                    }
                });
    }
}
