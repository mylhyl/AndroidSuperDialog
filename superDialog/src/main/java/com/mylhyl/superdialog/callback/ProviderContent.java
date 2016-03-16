package com.mylhyl.superdialog.callback;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * Created by hupei on 2016/3/10 15:09.
 */
public abstract class ProviderContent {
    public enum Mode {
        SINGLE, MULTIPLE
    }

    public SuperDialog.OnItemClickListener getItemClickListener() {
        return null;
    }

    public String getText() {
        return "内容";
    }

    public Object getItems() {
        return null;
    }

    public Mode getMode() {
        return Mode.SINGLE;
    }

    public int getTextSize() {
        return DimenRes.contentTextSize;
    }

    public int getTextColor() {
        return ColorRes.content;
    }

    public int[] getPadding() {
        return DimenRes.contentPadding;
    }

    public int getItemHeight() {
        return DimenRes.headerHeight;
    }

    public void dismiss() {
    }
}
