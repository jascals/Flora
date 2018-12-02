package com.jascal.flora.mvp.setting.adapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jascal.flora.widget.bottle.Header;
import com.jascal.flora.widget.bottle.ThemeSwitcher;
import com.jascal.flora.widget.bottle.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 5:25 PM
 * @email jascal@163.com
 */
public class GroupAdapter extends RecyclerView.Adapter {
    private List<Integer> viewHolderTypes = new ArrayList<>();
    private SparseArrayCompat<Integer> titleIndex = new SparseArrayCompat<>();
    private Handler handler;

    public GroupAdapter(Handler handler) {
        this.handler = handler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        return ViewHolder.create(viewGroup, type, handler);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof Header) {
            ((Header) viewHolder).setRes(titleIndex.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return viewHolderTypes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewHolderTypes.get(position);
    }

    public void addViewHolderType(int... type) {
        for (int i = 0; i < type.length; i++) {
            if (type[i] == ViewHolder.HOLDER_HEADER) {
                titleIndex.put(i, titleIndex.size());
            }
            viewHolderTypes.add(type[i]);
        }
        notifyDataSetChanged();
    }

}
