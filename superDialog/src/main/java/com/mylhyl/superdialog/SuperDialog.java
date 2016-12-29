package com.mylhyl.superdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mylhyl.superdialog.view.Controller;

/**
 * Created by hupei on 2016/3/8 13:36.
 */
public final class SuperDialog extends BaseDialog {
    private static final String SAVED_BUILDER = "super.dialog:Builder";

    public interface OnClickNegativeListener {
        void onClick(View v);
    }

    public interface OnClickPositiveListener {
        void onClick(View v);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface ConfigDialog {
        void onConfig(Dialog dialog, Window window, WindowManager.LayoutParams wlp,
                      DisplayMetrics dm);
    }

    private Controller mController;
    private Builder mBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mBuilder = (Builder) savedInstanceState.getSerializable(SAVED_BUILDER);
            setController(mBuilder);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mController != null) {
            outState.putSerializable(SAVED_BUILDER, mBuilder);
        }
    }

    private void setController(Builder builder) {
        this.mBuilder = builder;
        this.mController = new Controller(builder.getContext(), builder.mParams);
        mController.apply();
        mParams = builder.mParams;
    }

    @Override
    public View createView() {
        if (mController == null) return null;
        return mController.createView();
    }

    public static class Builder extends BaseDialog.Builder<Builder> {

        public Builder(FragmentActivity activity) {
            super(activity);
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

        public Builder setMessage(String text) {
            return setMessage(text, 0);
        }

        public Builder setMessage(String text, int textColor) {
            return setMessage(text, textColor, -1);
        }

        public Builder setMessage(String text, int textColor, int textSize) {
            return setMessage(text, textColor, textSize, null);
        }

        public Builder setMessage(final String text, final int textColor, final int textSize,
                                  final int[] padding) {
            mParams.setMessage(text, textColor, textSize, padding);
            return this;
        }


        public Builder setItems(Object items, OnItemClickListener listener) {
            return setItems(items, 0, listener);
        }

        public Builder setItems(Object items, int textSize, OnItemClickListener listener) {
            return setItems(items, 0, textSize, listener);
        }

        public Builder setItems(Object items, int textColor, int textSize, OnItemClickListener
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
        public Builder setItems(Object items, int textColor, int textSize,
                                int itemHeight, OnItemClickListener listener) {
            setGravity(Gravity.BOTTOM);
            mParams.setMessage(items, textColor, textSize, itemHeight, listener);
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

        public DialogFragment build() {
            checkBuilderParams();
            SuperDialog dialog = new SuperDialog();
            dialog.setController(this);
            dialog.show(mActivity.getSupportFragmentManager(), "superDialog");
            return dialog;
        }
    }
}
