package com.jascal.flora.mvp.setting;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseActivity;
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;
import com.jascal.flora.mvp.main.MainActivity;
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
    public static final int MSG_THEME_CHANGE = 0;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d("themeSwitcher", "get msg: " + msg.obj.toString());
            switch (msg.what) {
                case MSG_THEME_CHANGE:
                    boolean isChecked = (boolean) msg.obj;
                    if (isChecked) {
                        // light
                        SpHelper.getInstance(SettingFragment.this.getContext()).put(Config.SP_THEME_KEY, true);
                        MainActivity.invoke((BaseActivity) SettingFragment.this.getActivity(), false, SettingFragment.class.getSimpleName());
                        break;
                    } else {
                        // dark
                        SpHelper.getInstance(SettingFragment.this.getContext()).put(Config.SP_THEME_KEY, false);
                        MainActivity.invoke((BaseActivity) SettingFragment.this.getActivity(), false, SettingFragment.class.getSimpleName());
                        break;
                    }
            }
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        Ophelia.bind(this, view);

        initView();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
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
        GroupAdapter adapter = new GroupAdapter(handler);
        recyclerView.setAdapter(adapter);
        adapter.addViewHolderType(
                ViewHolder.HOLDER_HEADER,
                ViewHolder.HOLDER_THEME_SWITCHER,
                ViewHolder.HOLDER_HEADER,
                ViewHolder.HOLDER_LIST
        );
    }
}
