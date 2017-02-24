package com.mylhyl.superdialog.view;

import android.content.Context;

import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.res.drawable.BgBtn;

/**
 * Created by hupei on 2016/3/8 19:30.
 */
class BodySingleView extends SuperTextView {
    private Controller.Params mParams;

    public BodySingleView(Context context, Controller.Params params) {
        super(context);
        mParams = params;
        initData();
    }

    private void initData() {
        if (mParams.mProviderHeader != null && mParams.mFooterNegative == null
                && mParams.mFooterPositive == null) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(0, 0, radius, radius, mParams.mBackgroundColor));
        } else if (mParams.mProviderHeader == null && (mParams.mFooterNegative != null
                || mParams.mFooterPositive != null)) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(radius, radius, 0, 0, mParams.mBackgroundColor));
        } else if (mParams.mFooterNegative == null && mParams.mFooterPositive == null
                && mParams.mProviderHeader == null) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(radius, radius, radius, radius, mParams
                    .mBackgroundColor));
        } else {
            setBackgroundColor(mParams.mBackgroundColor);
        }
        ProviderContent providerContent = mParams.mProviderContent;
        if (providerContent == null) return;
        setText((String) providerContent.getItems());
        setTextSize(providerContent.getTextSize());
        setTextColor(providerContent.getTextColor());
        int[] padding = providerContent.getPadding();
        setAutoPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public void refreshText() {
        ProviderContent providerContent = mParams.mProviderContent;
        if (providerContent == null) return;
        setText((String) providerContent.getItems());
    }
}
