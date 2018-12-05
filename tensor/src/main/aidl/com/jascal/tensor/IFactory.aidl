// IFactory.aidl
package com.jascal.tensor;
import android.graphics.Bitmap;

// Declare any non-default types here with import statements

interface IFactory {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    Bitmap stylizeImage(in Bitmap bitmap, int model);
}
