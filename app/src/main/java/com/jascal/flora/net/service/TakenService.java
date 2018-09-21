package com.jascal.flora.net.service;


import com.jascal.flora.net.bean.Taken;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface TakenService {

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<Taken> getTaken(@Field("client_id") String client_id,
                               @Field("client_secret") String client_secret,
                               @Field("code") String code);
}
