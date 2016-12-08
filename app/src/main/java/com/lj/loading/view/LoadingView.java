package com.lj.loading.view;

import static com.lj.loading.view.CustomCircleView.DEFAULT_RADIUS;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.lj.loading.R;

/**
 * Description
 * Created by langjian on 2016/12/5.
 * Version
 */

public class LoadingView extends LinearLayout {
    private static final long DEFAULT_DURATION = 1200;
    private final Context mContent;
    private CustomCircleView mCircle1;
    private CustomCircleView mCircle2;
    private CustomCircleView mCircle3;

    public float mRadiusOfcircle1;
    public float mRadiusOfcircle2;
    public float mRadiusOfcircle3;
    private ValueAnimator mBig2SmallAnimator;
    private ValueAnimator mSmall2BigAnimator;
    private ValueAnimator mColorGradientAnimator2;
    private ValueAnimator mColorGradientAnimator;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContent = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContent).inflate(R.layout.custom_loading_layout,this,true);

        mCircle1 = (CustomCircleView)findViewById(R.id.circle1);
        mCircle2 = (CustomCircleView)findViewById(R.id.circle2);
        mCircle3 = (CustomCircleView)findViewById(R.id.circle3);

        initRadius();

        iniAnimators();
    }

    private void initRadius() {
        mCircle1.post(new Runnable() {
            @Override
            public void run() {
                mCircle1.setRadiusX(mCircle1.getMeasuredWidth()/2);
                mCircle1.setRadiusY(mCircle1.getMeasuredHeight()/2);
            }
        });

        mCircle2.post(new Runnable() {
            @Override
            public void run() {
                mCircle2.setRadiusX(mCircle2.getMeasuredWidth()/2);
                mCircle2.setRadiusY(mCircle2.getMeasuredHeight()/2);
            }
        });

        mCircle3.post(new Runnable() {
            @Override
            public void run() {
                mCircle3.setRadiusX(mCircle3.getMeasuredWidth()/2);
                mCircle3.setRadiusY(mCircle3.getMeasuredHeight()/2);
            }
        });
    }

    private void iniAnimators() {

        mBig2SmallAnimator = ValueAnimator.ofFloat(0.6f,1).setDuration(DEFAULT_DURATION);
        mBig2SmallAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mBig2SmallAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mBig2SmallAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadiusOfcircle1 = ((float)animation.getAnimatedValue()) * DEFAULT_RADIUS;
                mRadiusOfcircle3 = mRadiusOfcircle1;
                mCircle1.setRadius(mRadiusOfcircle1);
                mCircle3.setRadius(mRadiusOfcircle3);
            }
        });

        mSmall2BigAnimator = ValueAnimator.ofFloat(1,0.6f).setDuration(DEFAULT_DURATION);
        mSmall2BigAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mSmall2BigAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mSmall2BigAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadiusOfcircle2 = ((float)animation.getAnimatedValue()) * DEFAULT_RADIUS;
                mCircle2.setRadius(mRadiusOfcircle2);
            }
        });

        int darkColor = getContext().getResources().getColor(R.color.colorLight);
        int lightColor = getContext().getResources().getColor(R.color.colorDark);
        mColorGradientAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),darkColor, lightColor).setDuration(DEFAULT_DURATION);
        mColorGradientAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mColorGradientAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mColorGradientAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCircle1.setPaintColor((int)animation.getAnimatedValue());
                mCircle3.setPaintColor((int)animation.getAnimatedValue());
            }
        });

        mColorGradientAnimator2 = ValueAnimator.ofObject(new ArgbEvaluator(),lightColor, darkColor).setDuration(DEFAULT_DURATION);
        mColorGradientAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        mColorGradientAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        mColorGradientAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCircle2.setPaintColor((int)animation.getAnimatedValue());
            }
        });
    }

    public void startAnimation() {
        if(mBig2SmallAnimator != null){
            mBig2SmallAnimator.start();
        }
        if(mSmall2BigAnimator != null){
            mSmall2BigAnimator.start();
        }
        if(mColorGradientAnimator != null){
            mColorGradientAnimator.start();
        }
        if(mColorGradientAnimator2 != null){
            mColorGradientAnimator2.start();
        }
    }
}
