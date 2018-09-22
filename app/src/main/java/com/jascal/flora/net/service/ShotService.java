package com.jascal.flora.net.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ShotService {

    @GET("user/shots")
    Observable<ResponseBody> getShots(@Query("access_token") String taken);

    @GET("popular_shots")
    Observable<ResponseBody> getPopular(@Query("access_token") String taken);

    @GET("shots/{id}")
    Observable<ResponseBody> getShot(@Path("id") int shot_id, @Query("access_token") String taken);

}
