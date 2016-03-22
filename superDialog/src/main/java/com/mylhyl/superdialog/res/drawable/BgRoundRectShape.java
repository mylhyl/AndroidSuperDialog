package com.mylhyl.superdialog.res.drawable;

import android.graphics.drawable.shapes.RoundRectShape;

/**
 * 圆角
 * Created by hupei on 2016/3/9 15:24.
 */
class BgRoundRectShape {
    private RoundRectShape mRoundRectShape;

    public BgRoundRectShape(int leftTop, int rightTop, int rightBottom, int leftBottom) {
        float outerRadii[] = new float[8];
        if (leftTop > 0) {
            outerRadii[0] = leftTop;
            outerRadii[1] = leftTop;
        }
        if (rightTop > 0) {
            outerRadii[2] = rightTop;
            outerRadii[3] = rightTop;
        }
        if (rightBottom > 0) {
            outerRadii[4] = rightBottom;
            outerRadii[5] = rightBottom;
        }
        if (leftBottom > 0) {
            outerRadii[6] = leftBottom;
            outerRadii[7] = leftBottom;
        }
        mRoundRectShape = new RoundRectShape(outerRadii, null, null);
    }

    public RoundRectShape getRoundRectShape() {
        return mRoundRectShape;
    }
}
