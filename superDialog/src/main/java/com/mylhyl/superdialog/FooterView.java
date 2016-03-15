package com.mylhyl.superdialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.callback.ProviderFooterPositive;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.view.DividerView;
import com.mylhyl.superdialog.view.SuperTextView;

/**
 * 底部按钮
 * Created by hupei on 2016/3/8 19:10.
 */
class FooterView extends LinearLayout {

    public FooterView(Context context, SuperDialog.Builder builder) {
        super(context);
        init(builder);
    }

    private void init(SuperDialog.Builder builder) {
        setAlpha(builder.mAlpha);
        final ProviderFooterNegative footerNegative = builder.getFooterNegative();
        final ProviderFooterPositive footerPositive = builder.getFooterPositive();

        setOrientation(HORIZONTAL);

        int radius = builder.mRadius;
        if (footerNegative != null) {
            final SuperDialog.OnClickNegativeListener onNegativeListener = footerNegative.getOnNegativeListener();
            //取消
            SuperTextView mNegativeButton = new SuperTextView(getContext());
            mNegativeButton.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            mNegativeButton.setClickable(true);
            mNegativeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    footerNegative.dismiss();
                    if (onNegativeListener != null)
                        onNegativeListener.onClick(v);
                }
            });
            mNegativeButton.setText(footerNegative.getTitle());
            mNegativeButton.setTextSize(footerNegative.getTextSize());
            mNegativeButton.setTextColor(footerNegative.getTextColor());
            mNegativeButton.setHeight(footerNegative.getHeight());
            if (footerPositive != null)
                mNegativeButton.setBackgroundDrawable(new BgBtn(0, 0, 0, radius));
            else
                mNegativeButton.setBackgroundDrawable(new BgBtn(0, 0, radius, radius));
            addView(mNegativeButton);
        }

        //添加按钮之间的分隔线
        if (footerNegative != null && footerPositive != null) {
            DividerView dividerView = new DividerView(getContext());
            addView(dividerView);
        }
        if (footerPositive != null) {
            final SuperDialog.OnClickPositiveListener onPositiveListener = footerPositive.getOnPositiveListener();
            //确定
            SuperTextView mPositiveButton = new SuperTextView(getContext());
            mPositiveButton.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            mPositiveButton.setClickable(true);
            mPositiveButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    footerPositive.dismiss();
                    if (onPositiveListener != null)
                        onPositiveListener.onClick(v);
                }
            });
            mPositiveButton.setText(footerPositive.getTitle());
            mPositiveButton.setTextSize(footerPositive.getTextSize());
            mPositiveButton.setTextColor(footerPositive.getTextColor());
            mPositiveButton.setHeight(footerPositive.getHeight());
            if (footerNegative != null)
                mPositiveButton.setBackgroundDrawable(new BgBtn(0, 0, radius, 0));
            else
                mPositiveButton.setBackgroundDrawable(new BgBtn(0, 0, radius, radius));
            addView(mPositiveButton);
        }
    }
}
