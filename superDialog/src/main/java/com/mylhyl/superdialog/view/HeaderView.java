package com.mylhyl.superdialog.view;

import android.content.Context;
import android.graphics.Color;

import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.drawable.BgHeader;

/**
 * Created by hupei on 2016/3/8 19:29.
 */
class HeaderView extends SuperTextView {

    public HeaderView(Context context, Controller.Params params) {
        super(context);
        initData(params);
    }

    private void initData(Controller.Params params) {
        ProviderHeader providerHeader = params.mProviderHeader;
        setText(providerHeader.getTitle());
        setTextSize(providerHeader.getTextSize());
        setHeight(providerHeader.getHeight());
        setTextColor(providerHeader.getTextColor());
        //背景
        setBackgroundDrawable(new BgHeader(params));
    }
}
