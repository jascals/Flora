package com.jascal.tensor;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/12/5 8:43 PM
 * @email jascal@163.com
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("launched", "main");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.finish();
    }
}
