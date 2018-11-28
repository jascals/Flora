package com.jascal.flora.mvp.read.adapter.Impl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jascal.flora.R;
import com.jascal.flora.mvp.read.adapter.Interceptor;
import com.jascal.flora.net.bean.gank.News;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/28 2:51 PM
 * @email jascal@163.com
 */
public class LinkedInterceptor implements Interceptor {
    @Override
    public boolean checkType(News news) {
        return news.getImages().size() == 0 && !(news.getType().equals("福利"));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_no_image, viewGroup, false);
        return new LinkedHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, News news) {
        LinkedHolder linkedHolder = (LinkedHolder) viewHolder;
        linkedHolder.desc.setText(news.getDesc());
        linkedHolder.info.setText(news.getWho() + " created on " + news.getCreatedAt());
    }

    static class LinkedHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView info;

        LinkedHolder(@NonNull View itemView) {
            super(itemView);
            this.desc = itemView.findViewById(R.id.item_desc);
            this.info = itemView.findViewById(R.id.item_info);
        }
    }
}
