package com.mylhyl.superdialog.view;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.callback.CreateLayout;
import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.callback.ProviderFooterPositive;
import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;

/**
 * Created by hupei on 2016/3/18 16:18.
 */
public class Controller {
    private Context mContext;
    private Params mParams;
    private CreateLayout createLayout;

    public Controller(Context context, Params params) {
        this.mContext = context;
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
        } else {
            createLayout.buildSingleBody();
            //没有确定与取消不添加视图
            if (mParams.mFooterNegative != null || mParams.mFooterPositive != null)
                createLayout.buildSingleFooter();
        }
    }

    public View createView() {
        return createLayout.buildView();
    }

    public static class Params {
        public DialogFragment mDialogFragment;
        public ProviderHeader mProviderHeader;
        public ProviderContent mProviderContent;
        public ProviderFooterNegative mFooterNegative;
        public ProviderFooterPositive mFooterPositive;
        public int mGravity = Gravity.CENTER;
        public boolean mCancelable = true;
        public int mRadius = DimenRes.radius;
        public float mAlpha = 1f;
        public int mBackgroundColor = ColorRes.bgDialog;

        public void setTitle(ProviderHeader mProviderHeader) {
            this.mProviderHeader = mProviderHeader;
        }

        public void setMessage(ProviderContent providerContent) {
            this.mProviderContent = providerContent;
        }

        public void setNegativeButton(ProviderFooterNegative mFooterNegative) {
            this.mFooterNegative = mFooterNegative;
        }

        public void setPositiveButton(ProviderFooterPositive mFooterPositive) {
            this.mFooterPositive = mFooterPositive;
        }

        public void setTitle(final String title, final int textSize, final int height) {
            setTitle(new ProviderHeader() {

                @Override
                public String getTitle() {
                    return title;
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

        public void setMessage(final String text, final int textColor, final int textSize, final
        int[] padding) {
            setMessage(text, textColor, textSize, -1, padding, null);
        }

        public void setMessage(final Object items, final int textColor, final int textSize,
                               final int itemHeight, final SuperDialog.OnItemClickListener
                                       listener) {
            setMessage(items, textColor, textSize, itemHeight, null, listener);
        }

        private void setMessage(final Object items, final int textColor, final int textSize,
                                final int itemHeight, final int[] padding, final SuperDialog
                .OnItemClickListener listener) {
            setMessage(new ProviderContent() {
                @Override
                public void dismiss() {
                    if (mDialogFragment != null)
                        mDialogFragment.dismiss();
                }

                @Override
                public Object getItems() {
                    return items;
                }

                @Override
                public Mode getMode() {
                    return items instanceof String ? Mode.SINGLE : Mode.MULTIPLE;
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
                public int getItemHeight() {
                    return itemHeight > 0 ? itemHeight : super.getItemHeight();
                }

                @Override
                public int[] getPadding() {
                    return padding != null ? padding : super.getPadding();
                }

                @Override
                public SuperDialog.OnItemClickListener getItemClickListener() {
                    return listener;
                }
            });
        }

        public void setNegativeButton(final String text, final int textColor, final int textSize,
                                      final int height, final SuperDialog.OnClickNegativeListener
                                              listener) {
            setNegativeButton(new ProviderFooterNegative() {
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
                    return textColor != 0 ? textColor : super.getTextColor();
                }

                @Override
                public int getHeight() {
                    return height > 0 ? height : super.getHeight();
                }
            });
        }

        public void setPositiveButton(final String text, final int textColor, final int textSize,
                                      final int height, final SuperDialog.OnClickPositiveListener
                                              listener) {
            setPositiveButton(new ProviderFooterPositive() {
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

    }
}
