package com.jascal.flora.net.service.tc;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/16 4:46 PM
 * @email jascal@163.com
 */
public interface ArticleService {
    @POST("article/today?dev=1")
    @Headers("Content-Type:application/json")
    Call<ResponseBody> getArticle();
}
