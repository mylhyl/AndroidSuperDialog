package com.mylhyl.superdialog;

import android.content.Context;

import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.drawable.BgHeader;
import com.mylhyl.superdialog.view.SuperTextView;

/**
 * Created by hupei on 2016/3/8 19:29.
 */
class HeaderView extends SuperTextView {

    public HeaderView(Context context, SuperDialog.Builder builder) {
        super(context);
        initData(builder);
    }

    private void initData(SuperDialog.Builder builder) {
        ProviderHeader providerHeader = builder.getProviderHeader();
        setText(providerHeader.getTitle());
        setTextSize(providerHeader.getTextSize());
        setHeight(providerHeader.getHeight());
        setAlpha(builder.mAlpha);
        //背景
        setBackgroundDrawable(new BgHeader(builder.mRadius));
    }
}
