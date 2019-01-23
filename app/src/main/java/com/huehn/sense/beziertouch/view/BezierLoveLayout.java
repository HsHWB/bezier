package com.huehn.sense.beziertouch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by huehn on 2019/1/23.
 */

public class BezierLoveLayout extends RelativeLayout {
    private Context context;
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

    }
}
