package com.mylhyl.superdialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.mylhyl.superdialog.res.values.ColorRes;

/**
 * 分隔线，默认垂直
 * Created by hupei on 2016/3/8 15:12.
 */
class DividerView extends View {
    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        setBackgroundColor(ColorRes.divider);
    }

    /**
     * 将分隔线为水平分隔
     */
    public void setVertical() {
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
    }
}
