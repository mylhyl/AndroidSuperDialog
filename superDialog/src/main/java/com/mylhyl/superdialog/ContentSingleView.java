package com.mylhyl.superdialog;

import android.content.Context;
import android.util.AttributeSet;

import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;
import com.mylhyl.superdialog.view.SuperTextView;

/**
 * Created by hupei on 2016/3/8 19:30.
 */
class ContentSingleView extends SuperTextView {

    public ContentSingleView(Context context, SuperDialog.Builder builder) {
        super(context);
        initData(builder);
    }

    private void initData(SuperDialog.Builder builder) {
        setBackgroundColor(ColorRes.bgDialog);
        ProviderContent providerContent = builder.getProviderContent();
        if (providerContent == null) return;
        setText(providerContent.getText());
        setTextSize(providerContent.getTextSize());
        setTextColor(providerContent.getTextColor());
        int[] padding = providerContent.getPadding();
        setAutoPadding(padding[0], padding[1], padding[2], padding[3]);
    }
}
