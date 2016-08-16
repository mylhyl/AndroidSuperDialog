package com.mylhyl.superdialog.res.drawable;

import android.graphics.drawable.ShapeDrawable;

import com.mylhyl.superdialog.view.Controller;

/**
 * 标题背景
 * Created by hupei on 2016/3/9 13:12.
 */
public class BgHeader extends ShapeDrawable {
    public BgHeader(Controller.Params params) {
        super();
        getPaint().setColor(params.mBackgroundColor);//内部填充颜色
        //左上，右上，圆角半径
        int radius = params.mRadius;
        setShape(new BgRoundRectShape(radius, radius, 0, 0).getRoundRectShape());
    }
}
