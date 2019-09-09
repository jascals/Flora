package com.jascal.tensor.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;

import com.jascal.tensor.IFactory;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.util.Arrays;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/12/5 9:03 PM
 * @email jascal@163.com
 */
public class Factory extends IFactory.Stub {
    private static final String MODEL_FILE = "models/stylize_quantized.pb";
    private static final String INPUT_NODE = "input";
    private static final String STYLE_NODE = "style_num";
    private static final int NUM_STYLES = 26;
    private static final String OUTPUT_NODE = "transformer/expand/conv3/conv/Sigmoid";
    private Context context;

    public Factory(Context context) {
        this.context = context;
    }

    private int desiredSize = 1024;
    private final float[] styleVals = new float[NUM_STYLES];
    private int[] intValues = new int[desiredSize * desiredSize];
    private float[] floatValues = new float[desiredSize * desiredSize * 3];

    @Override
    public Bitmap stylizeImage(Bitmap bitmap, int model) {

        Log.d("launched", "stylized in tensor module");
        TensorFlowInferenceInterface inferenceInterface = new TensorFlowInferenceInterface(context.getAssets(), MODEL_FILE);
        bitmap = Bitmap.createScaledBitmap(bitmap, desiredSize, desiredSize, false);
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        for (int i = 0; i < intValues.length; ++i) {
            final int val = intValues[i];
            floatValues[i * 3] = ((val >> 16) & 0xFF) / 255.0f;
            floatValues[i * 3 + 1] = ((val >> 8) & 0xFF) / 255.0f;
            floatValues[i * 3 + 2] = (val & 0xFF) / 255.0f;
        }
        for (int i = 0; i < NUM_STYLES; ++i) {
            styleVals[i] = 0f;
        }
        styleVals[model] = 1f;

        // Copy the input data into TensorFlow.
        Log.d("tensor", "Width: " + bitmap.getWidth() + ", Height: " + bitmap.getHeight());
        inferenceInterface.feed(
                INPUT_NODE, floatValues, 1, bitmap.getWidth(), bitmap.getHeight(), 3);
        inferenceInterface.feed(STYLE_NODE, styleVals, NUM_STYLES);

        inferenceInterface.run(new String[]{OUTPUT_NODE}, false);
        inferenceInterface.fetch(OUTPUT_NODE, floatValues);

        for (int i = 0; i < intValues.length; ++i) {
            intValues[i] =
                    0xFF000000
                            | (((int) (floatValues[i * 3] * 255)) << 16)
                            | (((int) (floatValues[i * 3 + 1] * 255)) << 8)
                            | ((int) (floatValues[i * 3 + 2] * 255));
        }

        bitmap.setPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        Log.d("launched", "return bitmap");
        return bitmap;
    }
}
