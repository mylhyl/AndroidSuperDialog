package com.mylhyl.superdialog.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mylhyl.superdialog.auto.AutoUtils;

/**
 * Created by hupei on 2016/3/8 14:07.
 */
public class SuperTextView extends TextView {

    public SuperTextView(Context context) {
        this(context, null);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
    }

    @Override
    public void setHeight(int pixels) {
        int dimenHeight = AutoUtils.scaleValue(pixels);
        super.setHeight(dimenHeight);
    }

    @Override
    public void setTextSize(float size) {
        int dimenTextSize = AutoUtils.scaleValue((int) size);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenTextSize);
    }

    public void setAutoPadding(int left, int top, int right, int bottom) {
        int dimenLeft = AutoUtils.scaleValue(left);
        int dimenTop = AutoUtils.scaleValue(top);
        int dimenRight = AutoUtils.scaleValue(right);
        int dimenBottom = AutoUtils.scaleValue(bottom);
        super.setPadding(dimenLeft, dimenTop, dimenRight, dimenBottom);
    }
}
