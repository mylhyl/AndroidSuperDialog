package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.res.values.ColorRes;

/**
 * Created by hupei on 2016/3/10 15:05.
 */
public abstract class ProviderFooterPositive extends ProviderFooter {
    public abstract SuperDialog.OnClickPositiveListener getOnPositiveListener();

    public int getTextColor() {
        return ColorRes.positiveButton;
    }
}
