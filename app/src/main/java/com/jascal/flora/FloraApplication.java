package com.jascal.flora;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.jascal.flora.utils.TypefaceLoader;

public class FloraApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
        initFonts();
    }

    private void initFonts() {
        TypefaceLoader.setDefaultFont(this,"DEFAULT", "fonts/littlevi.ttf");
    }

    private void initFresco() {
        ImagePipelineConfig controller = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
        Fresco.initialize(this, controller);
    }
}
