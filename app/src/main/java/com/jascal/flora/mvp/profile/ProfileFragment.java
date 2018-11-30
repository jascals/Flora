package com.jascal.flora.mvp.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jascal.flora.R;
import com.jascal.flora.base.BaseFragment;
import com.jascal.flora.widget.Draggable.DraggableItemView;
import com.jascal.flora.widget.Draggable.DraggableSquareView;
import com.jascal.ophelia_annotation.BindView;
import com.jascal.ophelia_api.Ophelia;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 4:23 PM
 * @email jascal@163.com
 */
public class ProfileFragment extends BaseFragment implements DraggableItemView.Callback{
    private int imageStatus;
    private boolean isModify;

    @BindView(R.id.drag_square)
    DraggableSquareView dragSquare;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        Ophelia.bind(this, view);
        Fresco.initialize(getActivity());

        dragSquare.setCallback(this);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            dragSquare.fillItemImage(imageStatus, data.getData().toString(), isModify);
        }
    }

    @Override
    public void pickImage(int status, boolean draggable) {
        this.imageStatus = status;
        this.isModify = draggable;
        startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), 0);
    }
}
