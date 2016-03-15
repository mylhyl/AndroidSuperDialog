package com.mylhyl.superdialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.view.SuperTextView;

/**
 * Created by hupei on 2016/3/8 13:40.
 */
final class ItemsLayout extends DimenLinearLayout {

    public ItemsLayout(Context context, SuperDialog.Builder builder) {
        super(context);
        init(builder);
    }

    private void init(SuperDialog.Builder builder) {
        setOrientation(VERTICAL);

        if (builder.getProviderHeader() != null) {
            HeaderView titleView = new HeaderView(getContext(), builder);
            addView(titleView);
        }
        ContentMultipleView contentView = new ContentMultipleView(getContext(), builder);
        addView(contentView, new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));

        //底部按钮
        final ProviderFooterNegative footerNegative = builder.getFooterNegative();
        if (footerNegative != null) {
            final SuperDialog.OnClickNegativeListener onNegativeListener = footerNegative.getOnNegativeListener();
            //取消
            SuperTextView footerView = new SuperTextView(getContext());
            footerView.setAlpha(builder.mAlpha);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            layoutParams.topMargin = 10;
            footerView.setLayoutParams(layoutParams);
            footerView.setClickable(true);
            footerView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    footerNegative.dismiss();
                    if (onNegativeListener != null)
                        onNegativeListener.onClick(v);
                }
            });
            footerView.setText(footerNegative.getTitle());
            footerView.setTextSize(footerNegative.getTextSize());
            footerView.setTextColor(footerNegative.getTextColor());
            footerView.setHeight(footerNegative.getHeight());
            int radius = builder.mRadius;
            footerView.setBackgroundDrawable(new BgBtn(radius, radius, radius, radius));
            addView(footerView);
        }
    }
}
