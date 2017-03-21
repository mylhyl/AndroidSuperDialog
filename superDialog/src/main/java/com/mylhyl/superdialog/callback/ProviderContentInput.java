package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderContentInput extends ProviderContent {
    @Override
    public String getItems() {
        return null;
    }

    @Override
    public Mode getMode() {
        return Mode.INPUT;
    }

    public int getInputHeight() {
        return DimenRes.inputHeight;
    }

    public int[] getMargins() {
        return DimenRes.contentMargins;
    }

}
