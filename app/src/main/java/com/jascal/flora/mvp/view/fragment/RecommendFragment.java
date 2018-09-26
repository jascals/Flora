package com.jascal.flora.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.mvp.RecommendContract;
import com.jascal.flora.mvp.presenter.RecommendPresenter;
import com.jascal.flora.mvp.view.adapter.FeedAdapter;
import com.jascal.flora.net.bean.Feed;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_api.Ophelia;

import java.util.List;

public class RecommendFragment extends BaseFragment implements RecommendContract.view {
    private RecommendContract.presenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        Ophelia.bind(this, view);
        new RecommendPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        presenter.getShots(getContext());
    }

    @Override
    public void setPresenter(RecommendContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(List<Feed> feeds) {
        FeedAdapter feedAdapter = new FeedAdapter(feeds);
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(getContext(), "get shots error:" + message, Toast.LENGTH_LONG).show();
    }
}
