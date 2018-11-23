package com.jascal.flora.mvp.read;

import com.jascal.flora.net.bean.gank.NewsResponse;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 3:15 PM
 * @email jascal@163.com
 */
public interface ReadContract {
    interface View {
        void setPresenter(ReadContract.Presenter presenter);

        void update(NewsResponse newsResponse);

        void error(String message);
    }

    interface Presenter {
        void getNews();
    }
}
