package com.mylhyl.superdialog.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import com.mylhyl.superdialog.auto.AutoUtils;

/**
 * Created by hupei on 2017/3/21
 */
public class SuperEditText extends EditText {
    private Paint mPaint;
    public SuperEditText(Context context) {
        this(context, null);
    }

    public SuperEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SuperEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        requestFocus();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setGravity(Gravity.TOP | Gravity.LEFT);
        mPaint = new Paint();
        // 将边框设为黑色
        mPaint.setColor(android.graphics.Color.BLACK);
        mPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        // 画TextView的4个边
        canvas.drawLine(0, 0, this.getWidth() - 1, 0, mPaint);
        canvas.drawLine(0, 0, 0, this.getHeight() - 1, mPaint);
        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
                this.getHeight() - 1, mPaint);
        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
                this.getHeight() - 1, mPaint);
        super.onDraw(canvas);
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
}
