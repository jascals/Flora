package com.jascal.flora.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.jascal.flora.R;
import com.jascal.flora.cache.Config;
import com.jascal.flora.cache.sp.SpHelper;

public class ThemeUtils {
    public static final int LIGHT_MODEL = 0;
    public static final int DARK_MODEL = 1;

    public ThemeUtils() {
    }

    public static void setTheme(@NonNull Activity activity) {
        boolean isLight = (boolean) SpHelper.getInstance(activity).get(Config.SP_THEME_KEY, true);
        activity.setTheme(isLight ? R.style.lightness : R.style.darkness);
    }

    public static void reCreate(@NonNull final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.recreate();
            }
        });

    }
}
