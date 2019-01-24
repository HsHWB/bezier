package com.huehn.sense.beziertouch.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.huehn.sense.beziertouch.R;

import java.util.Random;

/**
 * Created by huehn on 2019/1/23.
 */

public class BezierLoveLayout extends RelativeLayout {
    private Context context;
    private Drawable[] drawable;
    private Interpolator[] interpolators = null;
    private Random random = new Random();
    private ImageView imageView;
    private int width = 0;
    private int height = 0;
    private int bubbleWidth = 0;
    private int bubbleHeight = 0;
    private float viewX = 0f;
    private float viewY = 0f;

    public BezierLoveLayout(Context context) {
        super(context);
        initView(context);
    }

    public BezierLoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BezierLoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        initDrawable();
        initInterpolator();
    }

    private void initDrawable(){
        drawable = new Drawable[]{this.context.getResources().getDrawable(R.drawable.ic_launcher_foreground)};
        bubbleWidth = drawable[0].getIntrinsicWidth();
        bubbleHeight = drawable[0].getIntrinsicHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.width = w;
        this.height = h;
    }

    public void onClick(){
        addImage();
    }

    private void addImage(){
        this.imageView = new ImageView(this.context);
        imageView.setImageDrawable(drawable[0]);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(bubbleWidth, bubbleHeight);
        this.viewX = (width - bubbleWidth) * 1.0f/ 2;
        this.viewY = (height - bubbleHeight) * 1.0f;
        layoutParams.leftMargin = (int) viewX;
        layoutParams.topMargin = (int) viewY;
        this.addView(this.imageView, layoutParams);
        startAnimation(imageView);
    }

    /**
     * 定义几个不同效果的插值器
     */
    private void initInterpolator(){
        interpolators = new Interpolator[4];
        interpolators[0] = new AccelerateDecelerateInterpolator();//在动画开始与结束的地方速率改变比较慢，在中间的时候加速
        interpolators[1] = new AccelerateInterpolator();//在动画开始的地方速率改变比较慢，然后开始加速
        interpolators[2] = new DecelerateInterpolator();//在动画开始的地方速率改变比较慢，然后开始减速
        interpolators[3] = new LinearInterpolator();//在动画的以均匀的速度改变
    }

    private void startAnimation(final ImageView bubble) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(bubble,"alpha",0.2f,1f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(bubble,"scaleX",0.2f,1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(bubble,"scaleY",0.2f,1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startBezierAnimation(bubble);
            }
        });
        animatorSet.setDuration(300);
        animatorSet.playTogether(alphaAnimator,scaleXAnimator,scaleYAnimator);
        animatorSet.start();
    }

    private void startBezierAnimation(final ImageView bubble) {
        //两个控制点随意生成
        PointF B = new PointF(random.nextInt(width/2) + width/4, random.nextInt(height/2) + height/2);
        PointF C = new PointF(random.nextInt(width/2) + width/4, random.nextInt(height/2));
        //起始点在这个布局的顶部中心点,结束节点在顶部x为任意，y这里设置0高度
        PointF A = new PointF(this.viewX, this.viewY);
        PointF D = new PointF(random.nextInt(width),0);

        BezierEvaluator bezierEvaluator = new BezierEvaluator(B,C);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierEvaluator,A,D);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(bubble);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF currentPoint = (PointF) valueAnimator.getAnimatedValue();
                bubble.setX(currentPoint.x);
                bubble.setY(currentPoint.y);
                bubble.setAlpha(1 - valueAnimator.getAnimatedFraction());
            }
        });
        valueAnimator.setTarget(bubble);
        valueAnimator.setInterpolator(interpolators[random.nextInt(interpolators.length)]);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}
