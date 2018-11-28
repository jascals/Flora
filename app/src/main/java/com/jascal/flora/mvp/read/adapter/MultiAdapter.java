package com.jascal.flora.mvp.read.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.jascal.flora.net.bean.gank.News;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/28 2:19 PM
 * @email jascal@163.com
 */
public class MultiAdapter extends RecyclerView.Adapter {
    private List<News> data = new ArrayList<>();
    private List<Interceptor> interceptors = new ArrayList<>();

    public void setData(List<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addInterceptor(Interceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    @Override
    public int getItemViewType(int position) {
        News news = data.get(position);
        for (Interceptor interceptor : interceptors) {
            if (interceptor.checkType(news)) {
                return interceptors.indexOf(interceptor);
            }
        }
        throw new RuntimeException("interceptor type error!");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return interceptors.get(viewType).onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = viewHolder.getItemViewType();
        Interceptor interceptor = interceptors.get(type);
        interceptor.onBindViewHolder(viewHolder, position, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
