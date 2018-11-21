package com.jascal.flora.mvp.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.databinding.ActivityArticleBinding;
import com.jascal.flora.mvp.ReadContract;
import com.jascal.flora.mvp.presenter.ReadPresenter;
import com.jascal.flora.net.bean.Article;
import com.jascal.flora.utils.ThemeUtils;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/21 11:11 AM
 * @email jascal@163.com
 */
public class ReadActivity extends BaseActivity implements ReadContract.View {
    private ReadContract.Presenter presenter;
    private ActivityArticleBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtils.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        new ReadPresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article);

        presenter.getArticle(this);
    }

    public static void start(BaseActivity activity) {
        Intent intent = new Intent(activity, ReadActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void setPresenter(ReadContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setData(Article article) {
        Log.d("article", "in activity: Success! " + article.toString());
        binding.setArticle(article.getData());
    }

    @Override
    public void onError(String msg) {
        Log.d("article", "in activity: Failed! " + msg);
    }
}
