package com.huehn.sense.beziertouch.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by huehn on 2019/1/23.
 */

public class LoveView extends LinearLayout{
    public LoveView(Context context) {
        super(context);
    }

    public LoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


}
