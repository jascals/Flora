package com.jascal.flora.net.service.tc;

import com.jascal.flora.net.bean.tc.Response;

import retrofit2.http.GET;
import rx.Observable;

public interface FeedService {

    @GET("feed-app")
    Observable<Response> getFeedList();

}
