package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderContentSingle extends ProviderContent {
    @Override
    public String getItems() {
        return null;
    }

    @Override
    public ProviderContent.Mode getMode() {
        return ProviderContent.Mode.SINGLE;
    }

    public int[] getPadding() {
        return DimenRes.contentPadding;
    }

}
