package com.mylhyl.superdialog.res.drawable;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;

import com.mylhyl.superdialog.res.values.ColorRes;

/**
 * 带圆角按钮背景
 * Created by hupei on 2016/3/9 13:02.
 */
public class BgBtn extends StateListDrawable {
    public BgBtn(int leftTop, int rightTop, int rightBottom, int leftBottom, int backgroundColor) {
        //按下
        ShapeDrawable drawablePress = new ShapeDrawable(
                new BgRoundRectShape(leftTop, rightTop, rightBottom, leftBottom).getRoundRectShape());
        drawablePress.getPaint().setColor(ColorRes.buttonPress);
        //默认
        ShapeDrawable defaultDrawable = new ShapeDrawable(
                new BgRoundRectShape(leftTop, rightTop, rightBottom, leftBottom).getRoundRectShape());
        defaultDrawable.getPaint().setColor(backgroundColor);

        addState(new int[]{android.R.attr.state_pressed}, drawablePress);
        addState(new int[]{-android.R.attr.state_pressed}, defaultDrawable);
    }
}
