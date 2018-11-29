package com.jascal.flora.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    private String TAG = this.getClass().getSimpleName();

    public String getTAG() {
        return TAG;
    }
}
