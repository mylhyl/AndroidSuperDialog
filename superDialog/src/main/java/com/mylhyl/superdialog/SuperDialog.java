package com.mylhyl.superdialog;

import android.app.Dialog;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

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
        this.mController = new Controller(builder.mParams);
        mController.apply();
        mParams = builder.mParams;
    }

    private void refreshProviderContent(Animation animation) {
        mController.refreshProviderContent(animation);
    }

    public void refreshProviderContentProgress(int max, int progress) {
        mController.refreshProviderContentProgress(max, progress);
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

        /**
         * 设置标题
         *
         * @param title 标题文本
         * @return
         */
        public Builder setTitle(@NonNull String title) {
            return setTitle(title, 0);
        }

        /**
         * 设置标题
         *
         * @param title     标题文本
         * @param textColor 标题文本字体颜色
         * @return
         */
        public Builder setTitle(@NonNull String title, @ColorInt int textColor) {
            return setTitle(title, textColor, -1);
        }

        /**
         * 设置标题
         *
         * @param title     标题文本
         * @param textColor 标题文本字体颜色
         * @param textSize  标题文本字体大小
         * @return
         */
        public Builder setTitle(@NonNull String title, @ColorInt int textColor, int textSize) {
            return setTitle(title, textColor, textSize, -1);
        }

        /**
         * 设置标题
         *
         * @param title     标题文本
         * @param textColor 标题文本字体颜色
         * @param textSize  标题文本字体大小
         * @param height    标题高度
         * @return
         */
        public Builder setTitle(@NonNull String title, @ColorInt int textColor, int textSize, int height) {
            mParams.setTitle(title, textColor, textSize, height);
            return this;
        }

        /**
         * 设置内容
         *
         * @param text 内容文本
         * @return
         */
        public Builder setMessage(@NonNull String text) {
            return setMessage(text, 0);
        }

        /**
         * 设置内容
         *
         * @param text      内容文本
         * @param textColor 内容文本字体颜色
         * @return
         */
        public Builder setMessage(@NonNull String text, @ColorInt int textColor) {
            return setMessage(text, textColor, -1);
        }

        /**
         * 设置内容
         *
         * @param text      内容文本
         * @param textColor 内容文本字体颜色
         * @param textSize  内容文本字体大小
         * @return
         */
        public Builder setMessage(@NonNull String text, @ColorInt int textColor, int textSize) {
            return setMessage(text, textColor, textSize, null);
        }

        /**
         * 设置内容
         *
         * @param text      内容文本
         * @param textColor 内容文本字体颜色
         * @param textSize  内容文本字体大小
         * @param padding   内容文本内间距{int left, int top, int right, int bottom}
         * @return
         */
        public Builder setMessage(@NonNull final String text, @ColorInt final int textColor, final int textSize,
                                  final int[] padding) {
            mParams.setContentSingle(text, textColor, textSize, padding);
            return this;
        }

        /**
         * 设置列表内容
         *
         * @param items    数据源 Array or Iterable
         * @param listener
         * @return
         */
        public Builder setItems(@NonNull Object items, OnItemClickListener listener) {
            return setItems(items, -1, listener);
        }

        /**
         * 设置列表内容
         *
         * @param items    数据源 Array or Iterable
         * @param textSize 数据源字体大小
         * @param listener
         * @return
         */
        public Builder setItems(@NonNull Object items, int textSize, OnItemClickListener listener) {
            return setItems(items, 0, textSize, listener);
        }

        /**
         * 设置列表内容
         *
         * @param items     数据源 Array or Iterable
         * @param textColor 数据源字体颜色
         * @param textSize  数据源字体大小
         * @param listener
         * @return
         */
        public Builder setItems(@NonNull Object items, @ColorInt int textColor, int textSize, OnItemClickListener
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
        public Builder setItems(@NonNull Object items, @ColorInt int textColor, int textSize, int itemHeight,
                                OnItemClickListener listener) {
            setGravity(Gravity.BOTTOM);
            mParams.setContentMultiple(items, textColor, textSize, itemHeight, listener);
            return this;
        }

        /**
         * 设置输入框
         *
         * @param hint {@link android.widget.EditText#setHint(CharSequence) 输入内容的提示语}
         * @return
         */
        public Builder setInput(@NonNull String hint) {
            return setInput(hint, 0);
        }

        /**
         * 设置输入框
         *
         * @param hint     {@link android.widget.EditText#setHint(CharSequence) 输入内容的提示语}
         * @param textSize 字体大小
         * @return
         */
        public Builder setInput(@NonNull String hint, int textSize) {
            return setInput(hint, 0, textSize);
        }

        /**
         * 设置输入框
         *
         * @param hint      {@link android.widget.EditText#setHint(CharSequence) 输入内容的提示语}
         * @param textColor 字体颜色
         * @param textSize  字体大小
         * @return
         */
        public Builder setInput(@NonNull String hint, @ColorInt int textColor, int textSize) {
            return setInput(hint, textColor, textSize, -1, null);
        }

        /**
         * 输入框
         *
         * @param hint        {@link android.widget.EditText#setHint(CharSequence) 输入内容的提示语}
         * @param textColor   字体颜色
         * @param textSize    字体大小 px
         * @param inputHeight 输入框高度 px
         * @return
         */
        public Builder setInput(@NonNull String hint, @ColorInt int textColor, int textSize, int inputHeight, int[]
                margins) {
            mParams.setContentInput(hint, textColor, textSize, inputHeight, margins);
            return this;
        }

        /**
         * 设置进度条模式
         *
         * @param progressDrawableId 进度样式，默认0
         * @return
         */
        public Builder setProgress(@DrawableRes int progressDrawableId) {
            return setProgress(progressDrawableId, 0);
        }

        public Builder setProgress(@DrawableRes int progressDrawableId, int height) {
            return setProgress(progressDrawableId, height, null);
        }

        public Builder setProgress(@DrawableRes int progressDrawableId, int height, int[] margins) {
            mParams.setContentProgress(progressDrawableId, height, margins);
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

        public Builder setPositiveInputButton(String text, @NonNull OnClickPositiveInputListener listener) {
            return setPositiveInputButton(text, 0, listener);
        }

        public Builder setPositiveInputButton(String text, int textColor, OnClickPositiveInputListener listener) {
            return setPositiveInputButton(text, textColor, -1, -1, listener);
        }

        public Builder setPositiveInputButton(String text, int textColor, int textSize, int height,
                                              OnClickPositiveInputListener listener) {
            mParams.setPositiveInputButton(text, textColor, textSize, height, listener);
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

        /**
         * 设置进度条刻度
         *
         * @param max
         * @param progress
         */
        public void refreshProgress(int max, int progress) {
            dialog.refreshProviderContentProgress(max, progress);
        }

        public void show() {
            dialog.show(mParams.mActivity.getSupportFragmentManager(), "superDialog");
        }
    }
}
