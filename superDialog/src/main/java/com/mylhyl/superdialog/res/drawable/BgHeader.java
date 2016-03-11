package com.mylhyl.superdialog.res.drawable;

import android.graphics.drawable.ShapeDrawable;

import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * 标题背景
 * Created by hupei on 2016/3/9 13:12.
 */
public class BgHeader extends ShapeDrawable {
    public BgHeader() {
        super();
        getPaint().setColor(ColorRes.bgDialog);//内部填充颜色
        setShape(new BgRoundRectShape(DimenRes.radius, DimenRes.radius, 0, 0).getRoundRectShape());//左上，右上，圆角半径
    }
}
