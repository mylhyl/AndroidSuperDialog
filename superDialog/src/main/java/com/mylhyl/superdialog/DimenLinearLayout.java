package com.mylhyl.superdialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.mylhyl.superdialog.auto.AutoLayoutConfig;

/**
 * Created by hupei on 2016/3/8 18:06.
 */
class DimenLinearLayout extends LinearLayout {
    public DimenLinearLayout(Context context) {
        this(context, null);
    }

    public DimenLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DimenLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        AutoLayoutConfig.init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DimenLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        AutoLayoutConfig.init(context);
    }
}
