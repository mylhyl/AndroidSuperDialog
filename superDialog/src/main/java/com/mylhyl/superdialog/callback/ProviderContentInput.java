package com.mylhyl.superdialog.callback;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderContentInput extends ProviderContent {


    public abstract int[] getMargins();

    public abstract int getInputHeight();

    public abstract int getHintTextColor();

    @Override
    public String getItems() {
        return null;
    }


    @Override
    public Mode getMode() {
        return Mode.INPUT;
    }


}
