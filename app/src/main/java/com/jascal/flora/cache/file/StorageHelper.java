package com.jascal.flora.cache.file;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageHelper {

    public static Uri saveBitmap(Context context, Bitmap bitmap, String bitmapName) {
        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) {
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitmapName;
        } else {
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitmapName;
        }
        Log.d("path", fileName);
        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)) {
                outputStream.flush();
                outputStream.close();
                Log.d("path", file.getAbsolutePath());
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitmapName, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
        return Uri.fromFile(file);
    }
}
