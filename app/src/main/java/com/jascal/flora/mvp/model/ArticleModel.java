package com.jascal.flora.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.net.bean.Article;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/16 4:38 PM
 * @email jascal@163.com
 */
public class ArticleModel {
    private Callback callback;

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

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void getArticle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String baseUrl = "https://interface.meiriyiwen.com/article/today?dev=1";
                    URL url = new URL(baseUrl);
                    HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setConnectTimeout(5 * 1000);
                    urlConn.setReadTimeout(5 * 1000);
                    urlConn.setUseCaches(true);
                    urlConn.setRequestMethod("GET");
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.addRequestProperty("Connection", "Keep-Alive");
                    urlConn.connect();
                    if (urlConn.getResponseCode() == 200) {
                        String result = streamToString(urlConn.getInputStream());
                        Article article = new Gson().fromJson(result, Article.class);
                        callback.onSuccess(article);
                        Log.d("article", "Get方式请求成功，result--->" + result);
                    } else {
                        callback.onFailure(urlConn.getResponseMessage());
                        Log.d("article", "Get方式请求失败");
                    }
                    urlConn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface Callback {
        void onSuccess(Article article);

        void onFailure(String message);
    }
}
