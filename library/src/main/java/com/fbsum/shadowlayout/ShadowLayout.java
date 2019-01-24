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
    private boolean shadowAutoDarken;
    private int shadowBlur;
    private int shadowRound;
    private int shadowOffsetLeft;
    private int shadowOffsetTop;
    private int shadowOffsetRight;
    private int shadowOffsetBottom;
    private int shadowOffsetDx;
    private int shadowOffsetDy;
    private int shadowPaintColor;

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout);
        shadowColor = typedArray.getColor(R.styleable.ShadowLayout_sl_shadow_color, Color.parseColor(DEFAULT_SHADOW_COLOR));
        shadowAutoDarken = typedArray.getBoolean(R.styleable.ShadowLayout_sl_shadow_auto_darken, true);
        shadowBlur = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_blur, dip2px(8));
        shadowRound = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_round, dip2px(0));
        shadowOffsetLeft = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_offset_left, DEFAULT_SHADOW_OFFSET);
        shadowOffsetTop = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_offset_top, DEFAULT_SHADOW_OFFSET);
        shadowOffsetRight = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_offset_right, DEFAULT_SHADOW_OFFSET);
        shadowOffsetBottom = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_offset_bottom, DEFAULT_SHADOW_OFFSET);
        shadowOffsetDx = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_offset_dx, 0);
        shadowOffsetDy = (int) typedArray.getDimension(R.styleable.ShadowLayout_sl_shadow_offset_dy, 0);
        typedArray.recycle();

        if (shadowAutoDarken) {
            shadowPaintColor = getDarkerColor(shadowColor);
        } else {
            shadowPaintColor = shadowColor;
        }

        shadowPaint = new Paint();
        shadowPaint.setColor(Color.WHITE);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (shadowRect == null) {
            shadowRect = new RectF();
            shadowRect.left = getPaddingLeft() + shadowOffsetLeft;
            shadowRect.top = getPaddingTop() + shadowOffsetTop;
            shadowRect.right = getWidth() - getPaddingRight() - shadowOffsetRight;
            shadowRect.bottom = getHeight() - getPaddingBottom() - shadowOffsetBottom;
        }
        canvas.drawRoundRect(shadowRect, shadowRound, shadowRound, shadowPaint);
        super.dispatchDraw(canvas);
    }

    private float dip2px(float dpValue) {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        float scale = dm.density;
        return (int) (dpValue * scale + 0.5F);
    }

    private static int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        return Color.HSVToColor(hsv);
    }

    public void setShadowColor(@ColorInt int color) {
        shadowColor = color;
        if (shadowAutoDarken) {
            shadowPaintColor = getDarkerColor(shadowColor);
        } else {
            shadowPaintColor = shadowColor;
        }
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);
        invalidate();
    }

    public void setShadowAutoDarken(boolean darken) {
        shadowAutoDarken = darken;
        if (shadowAutoDarken) {
            shadowPaintColor = getDarkerColor(shadowColor);
        } else {
            shadowPaintColor = shadowColor;
        }
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);
        invalidate();
    }

    public void setShadowBlur(int blur) {
        shadowBlur = blur;
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);
        invalidate();
    }

    public void setShadowRound(int round) {
        shadowRound = round;
        invalidate();
    }

    public void setShadowOffsetDx(int offset) {
        shadowOffsetDx = offset;
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);
        invalidate();
    }

    public void setShadowOffsetDy(int offset) {
        shadowOffsetDy = offset;
        shadowPaint.setShadowLayer(shadowBlur, shadowOffsetDx, shadowOffsetDy, shadowPaintColor);
        invalidate();
    }

    public void setShadowOffsetLeft(int offset) {
        shadowOffsetLeft = offset;
        shadowRect.left = getPaddingLeft() + shadowOffsetLeft;
        invalidate();
    }

    public void setShadowOffsetRight(int offset) {
        shadowOffsetRight = offset;
        shadowRect.right = getWidth() - getPaddingRight() - shadowOffsetRight;
        invalidate();
    }

    public void setShadowOffsetTop(int offset) {
        shadowOffsetTop = offset;
        shadowRect.top = getPaddingTop() + shadowOffsetTop;
        invalidate();
    }

    public void setShadowOffsetBottom(int offset) {
        shadowOffsetBottom = offset;
        shadowRect.bottom = getHeight() - getPaddingBottom() - shadowOffsetBottom;
        invalidate();
    }
}

