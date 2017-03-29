package com.mylhyl.superdialog.callback;

import android.view.View;

/**
 * Created by hupei on 2016/3/18 17:18.
 */
public interface CreateLayout {
    void buildHead();

    void buildMultipleBody();

    void buildSingleBody();

    View buildInputBody();

    void buildProgressBody();

    void buildMultipleFooter();

    void buildSingleFooter();

    void buildInputFooter(View view);

    View buildView();

    View findMultipleBody();

    View findSingleBody();

    View findProgressBody();
}
