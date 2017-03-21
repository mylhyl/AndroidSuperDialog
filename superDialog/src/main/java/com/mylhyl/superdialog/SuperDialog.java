package com.mylhyl.superdialog;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.view.Controller;

/**
 * Created by hupei on 2016/3/8 13:36.
 */
public final class SuperDialog extends BaseDialog {

    public interface OnClickNegativeListener {
        void onClick(View v);
    }

    public interface OnClickPositiveListener {
        void onClick(View v);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnClickPositiveInputListener {
        void onClick(String text, View v);
    }

    public interface ConfigDialog {
        void onConfig(Dialog dialog, Window window, WindowManager.LayoutParams wlp,
                      DisplayMetrics dm);
    }

    private Controller mController;

    private void setController(Builder builder) {
        this.mController = new Controller(builder.getContext(), builder.mParams);
        mController.apply();
        mParams = builder.mParams;
    }

    private void refreshProviderContent(Animation animation) {
        mController.refreshProviderContent(animation);
    }

    @Override
    public View createView() {
        if (mController == null) return null;
        return mController.createView();
    }

    public static class Builder extends BaseDialog.Builder<Builder> {
        private SuperDialog dialog;

        public Builder(FragmentActivity activity) {
            super(activity);
            dialog = new SuperDialog();
        }

        public Builder setTitle(String title) {
            return setTitle(title, -1);
        }

        public Builder setTitle(String title, int textSize) {
            return setTitle(title, textSize, -1);
        }

        public Builder setTitle(String title, int textSize, int height) {
            mParams.setTitle(title, textSize, height);
            return this;
        }

        public Builder setMessage(@NonNull String text) {
            return setMessage(text, 0);
        }

        public Builder setMessage(@NonNull String text, int textColor) {
            return setMessage(text, textColor, -1);
        }

        public Builder setMessage(@NonNull String text, int textColor, int textSize) {
            return setMessage(text, textColor, textSize, null);
        }

        public Builder setMessage(@NonNull final String text, final int textColor, final int textSize,
                                  final int[] padding) {
            mParams.setContentSingle(text, textColor, textSize, padding);
            return this;
        }

        public Builder setItems(@NonNull Object items, OnItemClickListener listener) {
            return setItems(items, 0, listener);
        }

        public Builder setItems(@NonNull Object items, int textSize, OnItemClickListener listener) {
            return setItems(items, 0, textSize, listener);
        }

        public Builder setItems(@NonNull Object items, int textColor, int textSize, OnItemClickListener
                listener) {
            return setItems(items, textColor, textSize, -1, listener);
        }

        /**
         * 列表 {@link Gravity#BOTTOM 默认}
         *
         * @param items      数据源 Array or Iterable
         * @param textColor  字体颜色
         * @param textSize   字体大小 px
         * @param itemHeight 列表 Item 高度 px
         * @param listener   列表点击事件
         * @return
         */
        public Builder setItems(@NonNull Object items, int textColor, int textSize,
                                int itemHeight, OnItemClickListener listener) {
            setGravity(Gravity.BOTTOM);
            mParams.setContentMultiple(items, textColor, textSize, itemHeight, listener);
            return this;
        }

        public Builder setInput(@NonNull String hint) {
            return setInput(hint, 0);
        }

        public Builder setInput(@NonNull String hint, int textSize) {
            return setInput(hint, ColorRes.title, textSize);
        }

        public Builder setInput(@NonNull String hint, int textColor, int textSize) {
            return setInput(hint, textColor, textSize, -1, null);
        }

        /**
         * 输入框
         *
         * @param hint
         * @param textColor   字体颜色
         * @param textSize    字体大小 px
         * @param inputHeight 输入框高度 px
         * @return
         */
        public Builder setInput(@NonNull String hint, int textColor, int textSize, int inputHeight, int[] margins) {
            mParams.setContentInput(hint, textColor, textSize, inputHeight, margins);
            return this;
        }

        public Builder setNegativeButton(String text, OnClickNegativeListener listener) {
            return setNegativeButton(text, 0, listener);
        }

        public Builder setNegativeButton(String text, int textColor, OnClickNegativeListener
                listener) {
            return setNegativeButton(text, textColor, -1, -1, listener);
        }

        public Builder setNegativeButton(String text, int textColor, int textSize,
                                         int height, OnClickNegativeListener listener) {
            mParams.setNegativeButton(text, textColor, textSize, height, listener);
            return this;
        }

        public Builder setPositiveButton(String text, OnClickPositiveListener listener) {
            return setPositiveButton(text, 0, listener);
        }

        public Builder setPositiveButton(String text, int textColor, OnClickPositiveListener
                listener) {
            return setPositiveButton(text, textColor, -1, -1, listener);
        }

        public Builder setPositiveButton(String text, int textColor, int textSize,
                                         int height, OnClickPositiveListener listener) {
            mParams.setPositiveButton(text, textColor, textSize, height, listener);
            return this;
        }

        public Builder setPositiveButton(String text, OnClickPositiveInputListener listener) {
            return setPositiveButton(text, 0, listener);
        }

        public Builder setPositiveButton(String text, int textColor, OnClickPositiveInputListener listener) {
            return setPositiveButton(text, textColor, -1, -1, listener);
        }

        public Builder setPositiveButton(String text, int textColor, int textSize, int height,
                                         OnClickPositiveInputListener listener) {
            mParams.setPositiveButton(text, textColor, textSize, height, listener);
            return this;
        }

        public DialogFragment build() {
            checkBuilderParams();
            dialog.setController(this);
            show();
            return dialog;
        }

        /**
         * 刷新内容
         */
        public void refreshContent() {
            dialog.refreshProviderContent(null);
        }

        /**
         * 刷新内容
         *
         * @param animation 刷新过程中，整个框的动画
         */
        public void refreshContent(Animation animation) {
            dialog.refreshProviderContent(animation);
        }

        public void show() {
            dialog.show(mActivity.getSupportFragmentManager(), "superDialog");
        }
    }
}
