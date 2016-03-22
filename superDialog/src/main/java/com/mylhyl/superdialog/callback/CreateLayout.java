package com.mylhyl.superdialog.callback;

import android.view.View;

/**
 * Created by hupei on 2016/3/18 17:18.
 */
public interface CreateLayout {
    void buildHead();

    void buildMultipleBody();
    void buildSingleBody();

    void buildMultipleFooter();
    void buildSingleFooter();

    View buildView();
}
