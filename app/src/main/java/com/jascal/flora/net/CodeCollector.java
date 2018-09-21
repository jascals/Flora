package com.jascal.flora.net;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jascal.flora.cache.sp.SpHelper;

public class CodeCollector extends WebViewClient {
    private Context context;

    public CodeCollector(Context context) {
        this.context = context;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (url.contains("code")) {
            String[] a = url.split("=");

            SpHelper.getInstance(context).put("code", a[a.length - 1]);
            Log.d("dribbble response", "code=" + a[a.length - 1]);
        }
    }
}
