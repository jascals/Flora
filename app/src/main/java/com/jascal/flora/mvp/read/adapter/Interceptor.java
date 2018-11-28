package com.jascal.flora.mvp.read.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jascal.flora.net.bean.gank.News;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/28 2:20 PM
 * @email jascal@163.com
 */
public interface Interceptor {

    boolean checkType(News news);

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, News news);
}
