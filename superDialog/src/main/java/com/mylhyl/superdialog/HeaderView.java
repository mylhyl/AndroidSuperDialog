package com.mylhyl.superdialog;

import android.content.Context;

import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.drawable.BgHeader;
import com.mylhyl.superdialog.view.SuperTextView;

/**
 * Created by hupei on 2016/3/8 19:29.
 */
class HeaderView extends SuperTextView {

    public HeaderView(Context context, ProviderHeader providerHeader) {
        super(context);
        initData(providerHeader);
    }

    private void initData(ProviderHeader providerHeader) {
        setText(providerHeader.getTitle());
        setTextSize(providerHeader.getTextSize());
        setHeight(providerHeader.getHeight());
        //背景
        setBackgroundDrawable(new BgHeader());
    }
}
