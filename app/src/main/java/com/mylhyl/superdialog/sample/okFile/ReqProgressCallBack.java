package com.mylhyl.superdialog.sample.okFile;

/**
 * Created by hupei on 2017/3/29.
 */

public interface ReqProgressCallBack<T>  extends ReqCallBack<T>{
    /**
     * 响应进度更新
     */
    void onProgress(long total, long current);
}
