package com.mylhyl.superdialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.callback.ProviderFooterPositive;
import com.mylhyl.superdialog.callback.ProviderFooterPositiveInput;
import com.mylhyl.superdialog.res.drawable.BgBtn;


/**
 * 底部按钮
 * Created by hupei on 2016/3/8 19:10.
 */
class FooterView extends LinearLayout {
    private ProviderFooterPositive mFooterPositive;
    private SuperTextView mPositiveButton;

    public FooterView(Context context, Controller.Params params) {
        super(context);
        init(params);
    }

    private void init(Controller.Params params) {
        final ProviderFooterNegative footerNegative = params.mFooterNegative;
        mFooterPositive = params.mFooterPositive;

        setOrientation(HORIZONTAL);

        int radius = params.mRadius;
        if (footerNegative != null) {
            final SuperDialog.OnClickNegativeListener onNegativeListener = footerNegative.getOnNegativeListener();
            //取消
            SuperTextView mNegativeButton = new SuperTextView(getContext());
            mNegativeButton.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT, 1));
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
            if (mFooterPositive != null)
                mNegativeButton.setBackgroundDrawable(new BgBtn(0, 0, 0, radius, params.mBackgroundColor));
            else
                mNegativeButton.setBackgroundDrawable(new BgBtn(0, 0, radius, radius, params.mBackgroundColor));
            addView(mNegativeButton);
        }

        //添加按钮之间的分隔线
        if (footerNegative != null && mFooterPositive != null) {
            DividerView dividerView = new DividerView(getContext());
            addView(dividerView);
        }
        if (mFooterPositive != null) {
            final SuperDialog.OnClickPositiveListener onPositiveListener = mFooterPositive.getOnPositiveListener();
            //确定
            mPositiveButton = new SuperTextView(getContext());
            mPositiveButton.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.WRAP_CONTENT, 1));
            mPositiveButton.setClickable(true);
            if (onPositiveListener != null)
                mPositiveButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFooterPositive.dismiss();
                        if (onPositiveListener != null)
                            onPositiveListener.onClick(v);
                    }
                });
            mPositiveButton.setText(mFooterPositive.getTitle());
            mPositiveButton.setTextSize(mFooterPositive.getTextSize());
            mPositiveButton.setTextColor(mFooterPositive.getTextColor());
            mPositiveButton.setHeight(mFooterPositive.getHeight());
            if (footerNegative != null)
                mPositiveButton.setBackgroundDrawable(new BgBtn(0, 0, radius, 0, params.mBackgroundColor));
            else
                mPositiveButton.setBackgroundDrawable(new BgBtn(0, 0, radius, radius, params.mBackgroundColor));
            addView(mPositiveButton);
        }
    }

    public void setOnClickPositiveInputListener(final BodyInputView inputView) {
        if (mFooterPositive != null && mFooterPositive instanceof ProviderFooterPositiveInput) {
            ProviderFooterPositiveInput positiveInput = (ProviderFooterPositiveInput) mFooterPositive;
            final SuperDialog.OnClickPositiveInputListener onPositiveInputListener = positiveInput
                    .getOnPositiveInputListener();
            mPositiveButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = inputView.getInputText();
                    if(!TextUtils.isEmpty(text))
                    mFooterPositive.dismiss();
                    if (onPositiveInputListener != null)
                        onPositiveInputListener.onClick(text,v);
                }
            });
        }
    }
}
