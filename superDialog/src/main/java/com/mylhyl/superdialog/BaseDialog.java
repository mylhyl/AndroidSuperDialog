package com.mylhyl.superdialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mylhyl.superdialog.view.Controller;

/**
 * Created by hupei on 2016/3/8 13:25.
 */
abstract class BaseDialog extends DialogFragment {

    public abstract View createView();

    private Controller.Params mParams;

    protected BaseDialog(Controller.Params params) {
        mParams = params;
    }

    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = createView();
        builder.setView(view);
        Dialog dialog = builder.create();
        mParams.mDialogFragment = this;
        dialog.setCanceledOnTouchOutside(mParams.mCancelable);
        setDialogGravity(dialog);//设置对话框布局
        return dialog;
    }


    /**
     * 设置对话框底部显示
     *
     * @param dialog
     */
    private void setDialogGravity(Dialog dialog) {
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();//获取屏幕宽
        wlp.width = (int) (d.getWidth() * 0.9);//宽度按屏幕大小的百分比设置
        wlp.gravity = mParams.mGravity;
        //如果是底部显示，则向上移20
        if (wlp.gravity == Gravity.BOTTOM)
            wlp.y = 20;
        window.setAttributes(wlp);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        // 指定一个过渡动画
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();// 防止按home键后，show的时候调用父类中的commit方法异常
    }

    public static class Builder<T extends Builder> {
        protected FragmentActivity mActivity;
        protected Controller.Params mParams;

        Builder(FragmentActivity activity) {
            mActivity = activity;
            mParams = new Controller.Params();
        }

        public Context getContext() {
            return mActivity;
        }

        /**
         * 设置对话框位置
         * {@link Gravity#CENTER}
         *
         * @param gravity
         */
        public T setGravity(int gravity) {
            mParams.mGravity = gravity;
            return (T) this;
        }

        public T setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
            mParams.mAlpha = alpha;
            return (T) this;
        }

        public T setRadius(int mRadius) {
            mParams.mRadius = mRadius;
            return (T) this;
        }

        public T setCanceledOnTouchOutside(boolean cancel) {
            mParams.mCancelable = cancel;
            return (T) this;
        }

        void checkBuilderParams() {
            if (mParams.mProviderContent == null) {
                throw new IllegalArgumentException("message is empty, please set");
            }
        }
    }
}
