package com.jascal.flora.mvp.model;

import android.util.Log;

import com.jascal.flora.net.service.ArticleService;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/16 4:38 PM
 * @email jascal@163.com
 */
public class ArticleModel {

    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("article", e.toString());
            return null;
        }
    }

    public void getArticle() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String baseUrl = "https://interface.meiriyiwen.com/article/today?dev=1";
//                    URL url = new URL(baseUrl);
//                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//                    urlConn.setConnectTimeout(5 * 1000);
//                    urlConn.setReadTimeout(5 * 1000);
//                    urlConn.setUseCaches(true);
//                    urlConn.setRequestMethod("GET");
//                    urlConn.setRequestProperty("Content-Type", "application/json");
//                    urlConn.addRequestProperty("Connection", "Keep-Alive");
//                    urlConn.connect();
//                    if (urlConn.getResponseCode() == 200) {
//                        String result = streamToString(urlConn.getInputStream());
//                        Log.e("article", "Get方式请求成功，result--->" + result);
//                    } else {
//                        Log.e("article", "Get方式请求失败");
//                    }
//                    urlConn.disconnect();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        Retrofit build = new Retrofit.Builder()
                .baseUrl("https://interface.meiriyiwen.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
        ArticleService articleService = build.create(ArticleService.class);
        Call<ResponseBody> call = articleService.getArticle();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("article", "success " + response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("article", "failed " + t.toString());
            }
        });

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://interface.meiriyiwen.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(client)
//                .build();
//
//        ArticleService articleService = retrofit.create(ArticleService.class);
//        articleService.getArticle().enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d("article", "success " + response.toString());
//                Log.d("article", "success " + response.code());
//                Log.d("article", "success " + response.toString());
//                try {
//                    Log.d("article", "success " + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//            }

//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("article", "error " + t.getMessage());
//                Log.d("article", "error " + t.getLocalizedMessage());
//            }
//        });

//        articleService.getArticle()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("article", "error " + e.getMessage());
//                        Log.d("article", "error " + e.toString());
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody article) {
//                        Log.d("article", "success " + article.toString());
//                    }
//                });
    }
}
