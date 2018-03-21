package com.fbsum.shadowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;


/**
 * Created by xin on 3/7/18.
 */

public class ShadowLayout extends FrameLayout {

    public ShadowLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShadowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private static final String DEFAULT_SHADOW_COLOR = "#ECECEC";
    private static final int DEFAULT_SHADOW_OFFSET = 1;

    private Paint shadowPaint;
    private RectF shadowRect;
    private int shadowColor;
    private int shadowRadius;
    private int shadowCorner;
    private int shadowLeftOffset;
    private int shadowTopOffset;
    private int shadowRightOffset;
    private int shadowBottomOffset;

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout);
        shadowColor = typedArray.getColor(R.styleable.ShadowLayout_sl_shadow_color, Color.parseColor(DEFAULT_SHADOW_COLOR));
        shadowRadius = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_radius, dip2px(8));
        shadowCorner = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_corner, dip2px(0));
        shadowLeftOffset = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_left_offset, DEFAULT_SHADOW_OFFSET);
        shadowTopOffset = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_top_offset, DEFAULT_SHADOW_OFFSET);
        shadowRightOffset = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_right_offset, DEFAULT_SHADOW_OFFSET);
        shadowBottomOffset = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_bottom_offset, DEFAULT_SHADOW_OFFSET);
        typedArray.recycle();

        shadowColor = getDarkerColor(shadowColor);

        shadowPaint = new Paint();
        shadowPaint.setColor(Color.WHITE);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setShadowLayer(shadowRadius, 0, 0, shadowColor);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (shadowRect == null) {
            shadowRect = new RectF();
            shadowRect.left = getPaddingLeft() + shadowLeftOffset;
            shadowRect.top = getPaddingTop() + shadowTopOffset;
            shadowRect.right = getWidth() - getPaddingRight() - shadowRightOffset;
            shadowRect.bottom = getHeight() - getPaddingBottom() - shadowBottomOffset;
        }
        canvas.drawRoundRect(shadowRect, shadowCorner, shadowCorner, shadowPaint);
        super.dispatchDraw(canvas);
    }

    private static int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        return Color.HSVToColor(hsv);
    }

    private float dip2px(float dpValue) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        float scale = dm.density;
        return (int) (dpValue * scale + 0.5F);
    }

    public void setShadowColor(@ColorInt int color) {
        this.shadowColor = getDarkerColor(color);
        shadowPaint.setShadowLayer(shadowRadius, 0, 0, shadowColor);
        invalidate();
    }

    public void setShadowRadius(int depth) {
        shadowRadius = depth;
        shadowPaint.setShadowLayer(shadowRadius, 0, 0, shadowColor);
        invalidate();
    }

    public void setShadowCorner(int round) {
        shadowCorner = round;
        invalidate();
    }

    public void setShadowLeftOffset(int offset) {
        shadowLeftOffset = offset;
        shadowRect.left = getPaddingLeft() + shadowLeftOffset;
        invalidate();
    }

    public void setShadowRightOffset(int offset) {
        shadowRightOffset = offset;
        shadowRect.right = getWidth() - getPaddingRight() - shadowRightOffset;
        invalidate();
    }

    public void setShadowTopOffset(int offset) {
        shadowTopOffset = offset;
        shadowRect.top = getPaddingTop() + shadowTopOffset;
        invalidate();
    }

    public void setShadowBottomOffset(int offset) {
        shadowBottomOffset = offset;
        shadowRect.bottom = getHeight() - getPaddingBottom() - shadowBottomOffset;
        invalidate();
    }
}

