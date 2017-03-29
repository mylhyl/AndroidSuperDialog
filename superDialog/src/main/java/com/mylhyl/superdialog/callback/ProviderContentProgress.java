package com.mylhyl.superdialog.callback;

import android.graphics.drawable.Drawable;

/**
 * Created by hupei on 2017/3/28
 */
public abstract class ProviderContentProgress extends ProviderContent {

    public abstract int[] getMargins();

    public abstract Drawable getProgressDrawable();

    public abstract int getHeight();

    @Override
    public final String getItems() {
        return null;
    }

    @Override
    public final Mode getMode() {
        return Mode.PROGRESSBAR;
    }
}
