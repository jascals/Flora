package com.jascal.flora.mvp.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
import com.jascal.ophelia_api.Ophelia;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 3:38 PM
 * @email jascal@163.com
 */
public class SettingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        Ophelia.bind(this, view);
        return view;
    }
}
