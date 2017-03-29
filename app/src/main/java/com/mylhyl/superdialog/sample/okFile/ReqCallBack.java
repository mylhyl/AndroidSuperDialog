package com.mylhyl.superdialog.sample.okFile;

/**
 * Created by hupei on 2017/3/29.
 */

public interface ReqCallBack<T> {
    /**
     * 响应成功
     */
    void onReqSuccess(T result);

    /**
     * 响应失败
     */
    void onReqFailed(String errorMsg);
}
