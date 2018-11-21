package com.jascal.flora.mvp.presenter;

import com.jascal.flora.mvp.ReadContract;
import com.jascal.flora.mvp.model.ArticleModel;
import com.jascal.flora.net.bean.Article;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/21 11:11 AM
 * @email jascal@163.com
 */
public class ReadPresenter implements ReadContract.Presenter, ArticleModel.Callback{
    private ReadContract.View view;

    public ReadPresenter(ReadContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getArticle() {
        ArticleModel model = new ArticleModel();
        model.setCallback(this);
        model.getArticle();
    }

    @Override
    public void onSuccess(Article article) {
        view.setData(article);
    }

    @Override
    public void onFailure(String message) {

    }
}
