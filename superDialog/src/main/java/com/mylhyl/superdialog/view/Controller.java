package com.mylhyl.superdialog.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.callback.CreateLayout;
import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.callback.ProviderContentInput;
import com.mylhyl.superdialog.callback.ProviderContentMultiple;
import com.mylhyl.superdialog.callback.ProviderContentProgress;
import com.mylhyl.superdialog.callback.ProviderContentSingle;
import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.callback.ProviderFooterPositive;
import com.mylhyl.superdialog.callback.ProviderFooterPositiveInput;
import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;

import java.io.Serializable;

/**
 * Created by hupei on 2016/3/18 16:18.
 */
public final class Controller {
    private Context mContext;
    private Params mParams;
    private CreateLayout createLayout;

    public Controller(Params params) {
        this.mContext = params.mActivity.getApplicationContext();
        this.mParams = params;
    }

    public void apply() {
        createLayout = new CreateLayoutImpl(mContext, mParams);
        if (mParams.mProviderHeader != null) {
            createLayout.buildHead();
        }
        ProviderContent providerContent = mParams.mProviderContent;
        if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.MULTIPLE) {
            createLayout.buildMultipleBody();
            if (mParams.mFooterNegative != null || mParams.mFooterPositive != null) {
                createLayout.buildMultipleFooter();
            }

        } else if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.SINGLE) {
            createLayout.buildSingleBody();
            //没有确定与取消不添加视图
            if (mParams.mFooterNegative != null || mParams.mFooterPositive != null)
                createLayout.buildSingleFooter();
        } else if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.INPUT) {
            View inputBody = createLayout.buildInputBody();
            if (mParams.mFooterNegative != null || mParams.mFooterPositive != null)
                createLayout.buildInputFooter(inputBody);
        } else if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.PROGRESSBAR) {
            createLayout.buildProgressBody();
            if (mParams.mFooterNegative != null)
                createLayout.buildSingleFooter();
        }
    }

    public View createView() {
        return createLayout.buildView();
    }

    public void refreshProviderContent(Animation animation) {
        ProviderContent providerContent = mParams.mProviderContent;
        if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.MULTIPLE) {
            BodyMultipleView multipleBody = (BodyMultipleView) createLayout.findMultipleBody();
            if (multipleBody != null) multipleBody.refreshItems();
        } else if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.SINGLE) {
            BodySingleView singleBody = (BodySingleView) createLayout.findSingleBody();
            if (singleBody != null) singleBody.refreshText();
        }
        if (animation != null)
            createView().startAnimation(animation);
    }

    public void refreshProviderContentProgress(int max, int progress) {
        ProviderContent providerContent = mParams.mProviderContent;
        if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.PROGRESSBAR) {
            BodyProgressView bodyProgressView = (BodyProgressView) createLayout.findProgressBody();
            if (bodyProgressView != null) bodyProgressView.setProgress(max, progress);
        }
    }

    public static class Params implements Serializable {
        public DialogFragment mDialogFragment;
        public FragmentActivity mActivity;
        public ProviderHeader mProviderHeader;
        public ProviderContent mProviderContent;
        public ProviderFooterNegative mFooterNegative;
        public ProviderFooterPositive mFooterPositive;
        public int mGravity = Gravity.CENTER;
        public boolean mCanceledOnTouchOutside = true;
        public boolean mCancelable = true;
        public int mRadius = DimenRes.radius;
        public float mAlpha = 1f;
        public int mBackgroundColor = ColorRes.bgDialog;
        public float mWidth = 0.9f;
        public int[] mPadding;
        public int mItemsBottomMargin = 10;
        public View mAsDropDownAnchor = null;
        public int mAtLocationGravity = Gravity.NO_GRAVITY;
        public int mAnimStyle, x, y;
        public boolean isDimEnabled = true;
        public SuperDialog.ConfigDialog mConfigDialog;

        public Params(FragmentActivity activity) {
            this.mActivity = activity;
        }

        private void setProviderHeader(ProviderHeader providerHeader) {
            this.mProviderHeader = providerHeader;
        }

        private void setProviderContent(ProviderContent providerContent) {
            this.mProviderContent = providerContent;
        }

        private void setProviderFooterNegative(ProviderFooterNegative footerNegative) {
            this.mFooterNegative = footerNegative;
        }

        private void setProviderFooterPositive(ProviderFooterPositive footerPositive) {
            this.mFooterPositive = footerPositive;
        }

        public void setTitle(final String title, final int textColor, final int textSize, final int height) {
            setProviderHeader(new ProviderHeader() {

                @Override
                public String getTitle() {
                    return title;
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : super.getTextColor();
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }

                @Override
                public int getHeight() {
                    return height > 0 ? height : super.getHeight();
                }

            });
        }

        /**
         * 设置文本内容
         *
         * @param text      文本
         * @param textColor 文本颜色
         * @param textSize  文本大小
         * @param padding   文本内间距
         */
        public void setContentSingle(final String text, final int textColor, final int textSize, final int[] padding) {
            setProviderContent(new ProviderContentSingle() {

                @Override
                public int[] getPadding() {
                    return padding != null ? padding : DimenRes.contentPadding;
                }

                @Override
                public String getItems() {
                    return text;
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : super.getTextColor();
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }

            });
        }

        /**
         * 设置多项文本内容
         *
         * @param items      list or string[]
         * @param textColor
         * @param textSize
         * @param itemHeight 每项高度
         * @param listener
         */
        public void setContentMultiple(final Object items, final int textColor, final int textSize, final int
                itemHeight,
                                       final SuperDialog.OnItemClickListener listener) {
            setProviderContent(new ProviderContentMultiple() {
                @Override
                public void dismiss() {
                    if (mDialogFragment != null)
                        mDialogFragment.dismiss();
                }

                @Override
                public int getItemHeight() {
                    return itemHeight > 0 ? itemHeight : DimenRes.headerHeight;
                }

                @Override
                public SuperDialog.OnItemClickListener getItemClickListener() {
                    return listener;
                }

                @Override
                public Object getItems() {
                    return items;
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : super.getTextColor();
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }
            });
        }

        /**
         * 设置输入框内容
         *
         * @param hint
         * @param textColor
         * @param textSize
         * @param inputHeight 输入框的高度
         * @param margins     输入框外边距
         */
        public void setContentInput(final String hint, final int textColor, final int textSize, final int inputHeight
                , final int[] margins) {
            setProviderContent(new ProviderContentInput() {

                @Override
                public int[] getMargins() {
                    return margins != null ? margins : DimenRes.contentInputMargins;
                }

                @Override
                public int getInputHeight() {
                    return inputHeight > 0 ? inputHeight : DimenRes.inputHeight;
                }

                @Override
                public int getHintTextColor() {
                    return ColorRes.content;
                }

                @Override
                public String getItems() {
                    return hint;
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : ColorRes.title;
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }
            });
        }

        public void setContentProgress(@DrawableRes final int progressDrawable, final int height, final int[] margins) {
            setProviderContent(new ProviderContentProgress() {

                @Override
                public int[] getMargins() {
                    return margins != null ? margins : DimenRes.contentProgressMargins;
                }

                @Override
                public Drawable getProgressDrawable() {
                    return progressDrawable != 0 ? mActivity.getResources().getDrawable(progressDrawable) : null;
                }

                @Override
                public int getHeight() {
                    return height != 0 ? height : 10;
                }
            });
        }

        public void setNegativeButton(final String text, final int textColor, final int textSize, final int height,
                                      final SuperDialog.OnClickNegativeListener listener) {
            setProviderFooterNegative(new ProviderFooterNegative() {
                @Override
                public String getTitle() {
                    return text;
                }

                @Override
                public void dismiss() {
                    if (mDialogFragment != null)
                        mDialogFragment.dismiss();
                }

                @Override
                public SuperDialog.OnClickNegativeListener getOnNegativeListener() {
                    return listener;
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : ColorRes.negativeButton;
                }

                @Override
                public int getHeight() {
                    return height > 0 ? height : super.getHeight();
                }
            });
        }

        public void setPositiveButton(final String text, final int textColor, final int textSize, final int height,
                                      final SuperDialog.OnClickPositiveListener listener) {
            setProviderFooterPositive(new ProviderFooterPositive() {
                @Override
                public String getTitle() {
                    return text;
                }

                @Override
                public void dismiss() {
                    if (mDialogFragment != null)
                        mDialogFragment.dismiss();
                }

                @Override
                public SuperDialog.OnClickPositiveListener getOnPositiveListener() {
                    return listener;
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : super.getTextColor();
                }

                @Override
                public int getHeight() {
                    return height > 0 ? height : super.getHeight();
                }
            });
        }


        public void setPositiveInputButton(final String text, final int textColor, final int textSize, final int height,
                                           final SuperDialog.OnClickPositiveInputListener listener) {
            setProviderFooterPositive(new ProviderFooterPositiveInput() {
                @Override
                public String getTitle() {
                    return text;
                }

                @Override
                public void dismiss() {
                    if (mDialogFragment != null)
                        mDialogFragment.dismiss();
                }

                @Override
                public SuperDialog.OnClickPositiveInputListener getOnPositiveInputListener() {
                    return listener;
                }

                @Override
                public int getTextSize() {
                    return textSize > 0 ? textSize : super.getTextSize();
                }

                @Override
                public int getTextColor() {
                    return textColor != 0 ? textColor : super.getTextColor();
                }

                @Override
                public int getHeight() {
                    return height > 0 ? height : super.getHeight();
                }
            });
        }
    }
}
