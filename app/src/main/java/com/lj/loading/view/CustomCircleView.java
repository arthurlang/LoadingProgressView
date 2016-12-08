package com.lj.loading.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lj.loading.R;

/**
 * Description
 * Created by langjian on 2016/12/6.
 * Version
 */

public class CustomCircleView extends View {
    public static final int DEFAULT_RADIUS = 16;
    private final Context mContext;
    private float mRadius;
    private Paint mPaint;

    private float mRadiusX;
    private float mRadiusY;

    public CustomCircleView(Context context) {
        this(context, null);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.CustomCircleView);
        mRadius = array.getInt(R.styleable.CustomCircleView_radius, DEFAULT_RADIUS);
        initView();
    }

    private void initView() {
        setWillNotDraw(false);
        if(this.mPaint == null){
            this.mPaint = new Paint();
        }
        this.mPaint.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mRadiusX, mRadiusY, mRadius, mPaint);
    }

    public void setRadius(float radiusOfcircle1) {
        if(mRadius != radiusOfcircle1){
            mRadius = radiusOfcircle1;
            invalidate();
        }
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadiusX(float radiusX) {
        mRadiusX = radiusX;
    }

    public void setRadiusY(float radiusY) {
        mRadiusY = radiusY;
    }

    public void setPaintColor(int paintColor) {
        int currentColor = this.mPaint.getColor();
        if(currentColor != paintColor){
            if(this.mPaint != null){
                this.mPaint.setColor(paintColor);
            }
        }
    }
}
