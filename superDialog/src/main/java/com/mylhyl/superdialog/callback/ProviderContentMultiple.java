package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderContentMultiple extends ProviderContent {

    public SuperDialog.OnItemClickListener getItemClickListener() {
        return null;
    }

    @Override
    public Object getItems() {
        return null;
    }

    @Override
    public ProviderContent.Mode getMode() {
        return ProviderContent.Mode.MULTIPLE;
    }

    public int getItemHeight() {
        return DimenRes.headerHeight;
    }
}
