package com.jascal.flora.widget.StyledGrid;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/26 3:33 PM
 * @email jascal@163.com
 */
public class ImageBox extends android.support.v7.widget.AppCompatImageView {
    private float value = 0.0f;
    private boolean hilighted = false;

    private final Paint boxPaint;
    private final Paint linePaint;

    public ImageBox(final Context context) {
        super(context);
        value = 0.0f;

        boxPaint = new Paint();
        boxPaint.setColor(Color.BLACK);
        boxPaint.setAlpha(128);

        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(10.0f);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final float y = (1.0f - value) * canvas.getHeight();

        // If all sliders are zero, don't bother shading anything.
//        if (!allZero) {
//            canvas.drawRect(0, 0, canvas.getWidth(), y, boxPaint);
//        }

        if (value > 0.0f) {
            canvas.drawLine(0, y, canvas.getWidth(), y, linePaint);
        }

        if (hilighted) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), linePaint);
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    public void setValue(final float value) {
        this.value = value;
        postInvalidate();
    }

    public void setHilighted(final boolean highlighted) {
        this.hilighted = highlighted;
        this.postInvalidate();
    }
}
