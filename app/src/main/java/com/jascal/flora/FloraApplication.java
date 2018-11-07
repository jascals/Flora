package com.jascal.flora;

import android.app.Application;
import android.graphics.Typeface;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.lang.reflect.Field;

public class FloraApplication extends Application {
    public static Typeface typeface;

    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
        initFonts();
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
}
