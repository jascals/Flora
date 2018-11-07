package com.jascal.flora.mvp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jascal.flora.base.BaseModel;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class TensorModel implements BaseModel {
    private static final String MODEL_FILE = "models/stylize_quantized.pb";
    private static final String INPUT_NODE = "input";
    private static final String STYLE_NODE = "style_num";
    private static final int NUM_STYLES = 26;
    private static final String OUTPUT_NODE = "transformer/expand/conv3/conv/Sigmoid";
    private Callback callback;
    private TensorFlowInferenceInterface inferenceInterface;

    public TensorModel(Context context) {
        inferenceInterface = new TensorFlowInferenceInterface(context.getAssets(), MODEL_FILE);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void convert(final Uri uri) {
        Log.d("convert", "start");
        ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        ImageRequest imageRequest = requestBuilder.build();
        DataSource<CloseableReference<CloseableImage>> dataSource = ImagePipelineFactory.getInstance().getImagePipeline().fetchDecodedImage(imageRequest, null);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(@Nullable final Bitmap bitmap) {
                if (callback == null) return;
                Log.d("convert", bitmap == null ? "onNewResultImpl null" : "onNewResultImpl start");
                if (bitmap != null && !bitmap.isRecycled()) {
                    final Bitmap temp = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("convert", "newSingleThreadExecutor start stylized");
                            stylizeImage(temp);

                        }
                    }).start();
                }
            }

            @Override
            protected void onFailureImpl(DataSource dataSource) {
                Log.d("convert", "onFailureImpl");

                if (callback == null) return;
                Throwable throwable = null;
                if (dataSource != null) {
                    throwable = dataSource.getFailureCause();
                }
                callback.onFailure(uri, throwable);
            }

            @Override
            public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
                super.onCancellation(dataSource);
                Log.d("convert", "onCancellation");

                if (callback == null) return;
                callback.onCancel(uri);
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }


    private int desiredSize = 512;
    private final float[] styleVals = new float[NUM_STYLES];
    private int[] intValues = new int[desiredSize * desiredSize];
    private float[] floatValues = new float[desiredSize * desiredSize * 3];

    private void stylizeImage(Bitmap bitmap) {
        bitmap = Bitmap.createScaledBitmap(bitmap, desiredSize, desiredSize, false);
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        for (int i = 0; i < intValues.length; ++i) {
            final int val = intValues[i];
            floatValues[i * 3] = ((val >> 16) & 0xFF) / 255.0f;
            floatValues[i * 3 + 1] = ((val >> 8) & 0xFF) / 255.0f;
            floatValues[i * 3 + 2] = (val & 0xFF) / 255.0f;
        }
        for (int i = 0; i < NUM_STYLES; ++i) {
            styleVals[i] = 0.1f * i / NUM_STYLES;
        }

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
        Log.d("convert", "postResult bitmap");
        postResult(bitmap);
    }

    private void postResult(final Bitmap result) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.d("convert", "callback.onsuccess");
                callback.onSuccess(result);
            }
        });
    }

    public interface Callback {
        void onSuccess(Bitmap result);

        void onFailure(Uri uri, Throwable throwable);

        void onCancel(Uri uri);
    }
}
