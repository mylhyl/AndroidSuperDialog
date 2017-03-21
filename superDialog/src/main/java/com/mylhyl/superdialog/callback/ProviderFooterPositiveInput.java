package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.res.values.ColorRes;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderFooterPositiveInput extends ProviderFooterPositive {
    @Override
    public final SuperDialog.OnClickPositiveListener getOnPositiveListener() {
        return null;
    }

    public abstract SuperDialog.OnClickPositiveInputListener getOnPositiveInputListener();
}
