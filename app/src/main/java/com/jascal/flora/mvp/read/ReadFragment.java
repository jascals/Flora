package com.jascal.flora.mvp.read;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.mvp.read.adapter.Impl.LinkedInterceptor;
import com.jascal.flora.mvp.read.adapter.Impl.MultiImageInterceptor;
import com.jascal.flora.mvp.read.adapter.Impl.SingleImageInterceptor;
import com.jascal.flora.mvp.read.adapter.MultiAdapter;
import com.jascal.flora.mvp.read.adapter.Tin;
import com.jascal.flora.net.bean.gank.NewsResponse;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_api.Ophelia;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/23 3:00 PM
 * @email jascal@163.com
 */
public class ReadFragment extends BaseFragment implements ReadContract.View {
    private ReadContract.Presenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, null);
        Ophelia.bind(this, view);
        new ReadPresenter(this);

        initView();
        initData();
        return view;
    }

    private void initData() {
        presenter.getNews();
    }

    private MultiAdapter multiAdapter;

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);
        multiAdapter = new MultiAdapter();
        multiAdapter.addInterceptor(new LinkedInterceptor());
        multiAdapter.addInterceptor(new SingleImageInterceptor());
        multiAdapter.addInterceptor(new MultiImageInterceptor());
        recyclerView.setAdapter(multiAdapter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setPresenter(ReadContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(NewsResponse newsResponse) {
        Log.d("read_", "success in fragment:" + newsResponse.getResults().getAndroid().toString());
        progressBar.setVisibility(View.INVISIBLE);
//        multiAdapter.setData(newsResponse.getResults().getAndroid());
        Tin tin = new Tin.Builder()
                .setDate(newsResponse.getResults().getAndroid())
                .append(newsResponse.getResults().getIOS())
                .append(newsResponse.getResults().getApp())
//                .append(newsResponse.getResults().getExpand())
//                .append(newsResponse.getResults().getThings())
                .append(newsResponse.getResults().getBeauty())
//                .append(newsResponse.getResults().getVideo())
                .build();
        Log.d("read_", "tin size is " + tin.getNews().size());
        multiAdapter.setData(tin.getNews());
    }

    @Override
    public void error(String message) {
        Log.d("read_", "fail in fragment:" + message);
    }
}
