package com.jascal.flora.mvp;

import com.jascal.flora.net.bean.Article;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/21 11:08 AM
 * @email jascal@163.com
 */
public interface ReadContract {
    interface View {
        void setPresenter(ReadContract.Presenter presenter);

        void setData(Article article);

        void onError(String msg);
    }

    interface Presenter {
        void getArticle();
    }
}
