package com.jascal.flora.mvp.feed;

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
import android.widget.Toast;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.mvp.feed.adapter.FeedAdapter;
import com.jascal.flora.mvp.feed.listener.RecyclerListener;
import com.jascal.flora.mvp.photo.PhotoActivity;
import com.jascal.flora.net.bean.tc.Feed;
import com.jascal.flora.widget.SpaceItemDecoration;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_api.Ophelia;

import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 2:51 PM
 * @email jascal@163.com
 */
public class FeedFragment extends BaseFragment implements FeedContract.View, RecyclerListener.OnItemClickListener {
    private FeedContract.Presenter presenter;
    private List<Feed> feeds;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, null);
        Ophelia.bind(this, view);

        new FeedPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        presenter.getShots(this.getContext());
        return view;
    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update(List<Feed> feeds) {
        this.feeds = feeds;
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this.getContext(), "feeds num is " + feeds.size(), Toast.LENGTH_SHORT).show();

        FeedAdapter feedAdapter = new FeedAdapter(feeds);
        recyclerView.setAdapter(feedAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        recyclerView.addOnItemTouchListener(new RecyclerListener(this.getContext(), recyclerView, this));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this.getContext(), "get shots error:" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        showImage(feeds.get(position));
    }

    private void showImage(Feed feed) {
        PhotoActivity.invoke(this.getActivity(), feed);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        // TODO
        Log.d("recyclerView", "long click");
    }
}
