package com.jascal.flora.utils;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.lang.reflect.Field;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/22 8:14 PM
 * @email jascal@163.com
 */
public class InitializeHelper extends IntentService {
    public static final String ACTION_INIT_WHEN_APP_CREATE = "com.jascal.flora.action.create";
    public static Typeface typeface;

    public InitializeHelper() {
        super("InitializeHelper");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }


    private void performInit() {
        initFresco();
//        initFonts();
    }

    private void initFonts() {
//        TypefaceLoader.setDefaultFont(this,"DEFAULT", "fonts/littlevi.ttf");
        typeface = Typeface.createFromAsset(getAssets(), "fonts/satisfy.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, typeface);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initFresco() {
        ImagePipelineConfig controller = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
        Fresco.initialize(this, controller);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeHelper.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }
}
