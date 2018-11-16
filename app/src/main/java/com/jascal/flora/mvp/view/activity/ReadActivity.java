package com.jascal.flora.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.mvp.model.ArticleModel;

public class ReadActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        new ArticleModel().getArticle();
    }

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, ReadActivity.class);
        activity.startActivity(intent);
    }
}
