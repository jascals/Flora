package com.jascal.flora.net.service.gank;

import com.jascal.flora.net.bean.gank.NewsResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 2:57 PM
 * @email jascal@163.com
 */
public interface NewsService {

    @GET("today")
    Observable<NewsResponse> getTodayNews();
}
