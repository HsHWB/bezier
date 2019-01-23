package com.huehn.sense.beziertouch.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.huehn.sense.beziertouch.R;

/**
 * Created by huehn on 2019/1/23.
 */

public class BezierTouchView extends RelativeLayout {
    private Context context;
    public Path path;
    public Paint paint;
    private boolean isQuad = false;
    private float controlX = 0;
    private float controlY = 0;
    private float endX = 0;
    private float endY = 0;

    public BezierTouchView(Context context) {
        super(context);
        initView(context);
    }

    public BezierTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BezierTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        this.context = context;
        this.path = new Path();
        this.paint = new Paint();
        this.paint.setColor(context.getResources().getColor(R.color.colorAccent));
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(5.0f);
        setWillNotDraw(false);
    }

    public void setQuad(boolean quad) {
        isQuad = quad;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.path, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                controlX = event.getX();
                controlY = event.getY();
                System.out.println("huehn draw down : x : " + controlX + "   y : " + controlY);
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isQuad){
                    endX = (controlX + event.getX()) / 2;
                    endY = (controlY + event.getY()) / 2;
                    path.quadTo(controlX, controlY, endX, endY);
                    controlX = event.getX();
                    controlY = event.getY();
                    System.out.println("quad");
                }else {
                    path.lineTo(event.getX(), event.getY());
                    System.out.println("line");
                }
                postInvalidate();
                System.out.println("huehn draw move : x : " + event.getX() + "   y : " + event.getY());
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}
