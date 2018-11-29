package com.jascal.flora.mvp.setting;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.mvp.setting.adapter.GroupAdapter;
import com.jascal.flora.widget.bottle.ViewHolder;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_api.Ophelia;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 3:38 PM
 * @email jascal@163.com
 */
public class SettingFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        Ophelia.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setSmoothScrollbarEnabled(true);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                final int padding = getResources().getDimensionPixelOffset(R.dimen.padding_half);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                final int position = layoutParams.getViewLayoutPosition();
                if (position == 0) {
                    outRect.left = outRect.top = outRect.right = padding;
                    outRect.bottom = padding >> 1;
                } else if (position == state.getItemCount() - 1) {
                    outRect.left = outRect.bottom = outRect.right = padding;
                    outRect.top = padding >> 1;
                } else {
                    outRect.left = outRect.right = padding;
                    outRect.top = outRect.bottom = padding >> 1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        GroupAdapter adapter = new GroupAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addViewHolderType(
                ViewHolder.HOLDER_HEADER,
                ViewHolder.HOLDER_LABEL,
                ViewHolder.HOLDER_HEADER,
                ViewHolder.HOLDER_LIST
        );
    }
}
