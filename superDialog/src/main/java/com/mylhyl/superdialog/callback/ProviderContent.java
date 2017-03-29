package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * Created by hupei on 2016/3/10 15:09.
 */
public abstract class ProviderContent {
    public enum Mode {
        SINGLE, MULTIPLE, INPUT, PROGRESSBAR
    }

    public abstract Mode getMode();

    public Object getItems() {
        return null;
    }

    public int getTextSize() {
        return DimenRes.contentTextSize;
    }

    public int getTextColor() {
        return ColorRes.content;
    }
}
