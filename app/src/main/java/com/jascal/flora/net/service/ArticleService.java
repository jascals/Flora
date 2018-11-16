package com.jascal.flora.net.service;

import com.jascal.flora.net.bean.Article;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/16 4:46 PM
 * @email jascal@163.com
 */
public interface ArticleService {
    @GET("today?dev=1")
    Observable<RequestBody> getArticle();
}
