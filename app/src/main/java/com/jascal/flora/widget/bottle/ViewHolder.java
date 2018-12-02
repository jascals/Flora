package com.jascal.flora.widget.bottle;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/29 4:28 PM
 * @email jascal@163.com
 */
public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public static final int HOLDER_HEADER = 0;
    public static final int HOLDER_THEME_SWITCHER = 1;
    public static final int HOLDER_LIST = 3;

    public ViewHolder(View itemView) {
        super(itemView);
    }

    public static ViewHolder create(ViewGroup viewHolder, int type, Handler handler) {
        switch (type) {
            case HOLDER_HEADER:
                return Header.create(viewHolder);
            case HOLDER_THEME_SWITCHER:
                return ThemeSwitcher.create(viewHolder, handler);
            case HOLDER_LIST:
                return List.create(viewHolder);
            default:
                return null;
        }
    }
}
