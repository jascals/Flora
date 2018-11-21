package com.jascal.flora.mvp.presenter;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.ReadContract;
import com.jascal.flora.mvp.model.ArticleModel;
import com.jascal.flora.net.bean.Article;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/21 11:11 AM
 * @email jascal@163.com
 */
public class ReadPresenter implements ReadContract.Presenter {
    private ReadContract.View view;

    public ReadPresenter(ReadContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getArticle(final Context context) {
        String data = (String) SpHelper.getInstance(context).get(Config.SP_ARTICLE_KEY, "");
        if (data.equals("")) {
            Log.d("article from", "request,sp null");
            request(context);
        } else {
            Article article = decoding(data);
            if (article != null && article.getData().getDate().getCurr().equals(getToday())) {
                Log.d("article from", "sp");
                view.setData(article);
            } else {
                Log.d("article from", article.getData().getDate().getCurr());
                Log.d("article from", "request, date diff");
                request(context);
            }
        }
    }

    private void request(final Context context) {
        ArticleModel model = new ArticleModel();
        model.setCallback(new ArticleModel.Callback() {
            @Override
            public void onSuccess(Article article) {
                view.setData(article);
                SpHelper.getInstance(context).put(Config.SP_ARTICLE_KEY, encoding(article));
            }

            @Override
            public void onFailure(String message) {

            }
        });
        model.getArticle();
    }

    private String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINESE);
        Date date = new Date();
        Log.d("article from", sdf.format(date));
        return sdf.format(date);
    }

    private String encoding(Parcelable parcelable) {
        Parcel p = Parcel.obtain();
        parcelable.writeToParcel(p, 0);
        byte[] bytes = p.marshall();
        p.recycle();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private Article decoding(String data) {
        Parcel parcel = Parcel.obtain();
        byte[] bytes = Base64.decode(data, Base64.DEFAULT);
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        Article article = Article.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return article;
    }
}
