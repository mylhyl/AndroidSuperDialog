package com.mylhyl.superdialog.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.mylhyl.superdialog.auto.AutoLayoutConfig;

/**
 * Created by hupei on 2016/3/18 16:30.
 */
class AutoLinearLayout extends LinearLayout {
    public AutoLinearLayout(Context context) {
        this(context, null);
    }

    public AutoLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        AutoLayoutConfig.init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AutoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        AutoLayoutConfig.init(context);
    }
}
