package com.jascal.flora.mvp.read;

import com.jascal.flora.base.BasePresenter;
import com.jascal.flora.mvp.read.model.ReadModel;
import com.jascal.flora.net.bean.gank.NewsResponse;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 3:17 PM
 * @email jascal@163.com
 */
public class ReadPresenter extends BasePresenter implements ReadContract.Presenter {
    private ReadContract.View view;

    ReadPresenter(ReadContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void getNews() {
        new ReadModel().setCallback(new ReadModel.Callback() {
            @Override
            public void onSuccess(NewsResponse newsResponse) {
                view.update(newsResponse);
            }

            @Override
            public void onFail(String message) {
                view.error(message);
            }
        }).getNews();
    }
}
