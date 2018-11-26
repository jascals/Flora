package com.jascal.flora.widget.StyledGrid;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/26 4:42 PM
 * @email jascal@163.com
 */
public class StylizedGridView extends GridView {
    public StylizedGridView(Context context) {
        super(context);
    }

    public StylizedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StylizedGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StylizedGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
