package com.huehn.sense.beziertouch.view;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by huehn on 2019/1/23.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF controlBPoint;
    private PointF controlCPoint;

    public BezierEvaluator(PointF controlBPoint, PointF controlCPoint) {
        this.controlBPoint = controlBPoint;
        this.controlCPoint = controlCPoint;
    }

    @Override
    public PointF evaluate(float fraction, PointF startAValue, PointF endDValue) {
        PointF evaluatedPoint = new PointF();
        evaluatedPoint.x = (float) (startAValue.x*(1-fraction)*(1-fraction)*(1-fraction) + controlBPoint.x*3*Math.pow(1-fraction, 2)*fraction + controlCPoint.x*3*(1-fraction)*Math.pow(fraction, 2) +endDValue.x*Math.pow(fraction, 3));
        evaluatedPoint.y = (float) (startAValue.y*(1-fraction)*(1-fraction)*(1-fraction) + controlBPoint.y*3*Math.pow(1-fraction, 2)*fraction + controlCPoint.y*3*(1-fraction)*Math.pow(fraction, 2) +endDValue.y*Math.pow(fraction, 3));
        return evaluatedPoint;
    }
}
