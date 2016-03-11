package com.mylhyl.superdialog.res.drawable;

import android.graphics.drawable.GradientDrawable;

import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * 4角圆提示框背景
 * Created by hupei on 2016/3/9 13:12.
 */
public class BgLayout extends GradientDrawable {
    public BgLayout() {
//        Orientation.TOP_BOTTOM, new int[]{ColorRes.bgDialog,ColorRes.bgDialogStart}
        super();
        setGradientType(RADIAL_GRADIENT);
        setColor(ColorRes.bgDialog);//内部填充颜色
        setCornerRadius(DimenRes.radius);//圆角半径
        setGradientRadius(360);
    }
}
