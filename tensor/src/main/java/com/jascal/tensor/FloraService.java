package com.jascal.tensor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jascal.tensor.impl.Factory;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/12/5 9:02 PM
 * @email jascal@163.com
 */
public class FloraService extends Service {
    private IFactory.Stub factory = new Factory(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return factory;
    }
}
