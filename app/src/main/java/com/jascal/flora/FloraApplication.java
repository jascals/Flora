package com.jascal.flora;

import android.app.Application;

import com.jascal.flora.utils.InitializeHelper;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 8:14 PM
 * @email jascal@163.com
 */
public class FloraApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitializeHelper.start(this);
    }
}
