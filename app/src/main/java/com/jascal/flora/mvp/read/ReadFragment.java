package com.jascal.flora.mvp.read;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
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
        new ReadPresenter(this).getNews();
        return view;
    }

    @Override
    public void setPresenter(ReadContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(NewsResponse newsResponse) {
        progressBar.setVisibility(View.INVISIBLE);
        Log.d("read", "success in fragment:" + newsResponse.toString());
        Log.d("read", "success in fragment:" + newsResponse.getCategory().toString());
        Log.d("read", "success in fragment:" + newsResponse.getResults().getAndroid().toString());
    }

    @Override
    public void error(String message) {
        Log.d("read", "fail in fragment:" + message);
    }
}
