package com.jascal.flora.cache.sp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.jascal.flora.cache.Config;

import java.util.Map;

public class SpHelper {
    private static SpHelper spHelper = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static SpHelper getInstance(Context context) {
        if (spHelper == null) {
            spHelper = new SpHelper(context);
        }
        return spHelper;
    }

    @SuppressLint("CommitPrefEdits")
    private SpHelper(Context context) {
        this.sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(Config.SP_NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}
