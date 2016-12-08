package com.lj.loading;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lj.loading.view.LoadingView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private LoadingView mCanvasView;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAnimation();
        startAnimation();
    }

    private void startAnimation() {
        if(mAnimationDrawable != null){
            mAnimationDrawable.start();
        }

        if(mCanvasView != null){
            mCanvasView.startAnimation();
        }
    }

    private void initAnimation() {
        mAnimationDrawable = (AnimationDrawable)mImageView.getDrawable();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.animation_list_loading_view);
        mCanvasView = (LoadingView)findViewById(R.id.canvas_loading_view);
    }
}
