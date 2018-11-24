package com.jascal.flora.mvp.read.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 4:23 PM
 * @email jascal@163.com
 */
public class NewsAdapter extends RecyclerView.Adapter {
    public static final int TYPE_POSTER = 0;//Beauty
    public static final int TYPE_TITLE = 1;//
    public static final int TYPE_CONTENT_WITH_IMAGE = 2; //A, I, App
    public static final int TYPE_CONTENT_NO_IMAGE = 3; //Video, Expand, Things

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
