package com.mylhyl.superdialog;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;


import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.callback.ProviderFooterPositive;
import com.mylhyl.superdialog.callback.ProviderHeader;

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

    private Builder mBuilder;

    private SuperDialog(Builder builder) {
        super(builder);
        this.mBuilder = builder;
    }

    @Override
    public View createView() {
        ProviderContent providerContent = mBuilder.getProviderContent();
        if (providerContent != null && providerContent.getMode() == ProviderContent.Mode.MULTIPLE) {
            return new ItemsLayout(getContext(), mBuilder);
        }
        return new MessageLayout(getContext(), mBuilder);
    }

    public static class Builder extends BaseDialog.Builder<Builder> {
        private ProviderHeader mProviderHeader;
        private ProviderContent mProviderContent;
        private ProviderFooterNegative mFooterNegative;
        private ProviderFooterPositive mFooterPositive;

        public Builder(FragmentActivity activity) {
            super(activity);
        }

        public Builder setTitle(String title) {
            return setTitle(title, -1);
        }

        public Builder setTitle(String title, int textSize) {
            return setTitle(title, textSize, -1);
        }

        public Builder setTitle(final String title, final int textSize, final int height) {
            return setTitle(new ProviderHeader() {

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

        public Builder setMessage(String text) {
            setMessage(text, 0);
            return this;
        }

        public Builder setMessage(String text, int textColor) {
            setMessage(text, textColor, -1);
            return this;
        }

        public Builder setMessage(String text, int textColor, int textSize) {
            setMessage(text, textColor, textSize, null);
            return this;
        }

        public Builder setMessage(final String text, final int textColor, final int textSize, final int[] padding) {
            return setMessage(new ProviderContent() {
                @Override
                public String getText() {
                    return text;
                }

                @Override
                public Mode getMode() {
                    return Mode.SINGLE;
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
                public int[] getPadding() {
                    return padding != null ? padding : super.getPadding();
                }
            });
        }

        public Builder setItems(String[] items, OnItemClickListener listener) {
            setGravity(Gravity.BOTTOM);
            return setItems(items, 0, listener);
        }

        public Builder setItems(String[] items, int textSize, OnItemClickListener listener) {
            return setItems(items, 0, textSize, listener);
        }

        public Builder setItems(String[] items, int textColor, int textSize, OnItemClickListener listener) {
            return setItems(items, textColor, textSize, -1, listener);
        }

        public Builder setItems(final String[] items, final int textColor, final int textSize,
                                final int itemHeight, final OnItemClickListener listener) {
            return setMessage(new ProviderContent() {
                @Override
                public void dismiss() {
                    if (mDialogFragment != null)
                        mDialogFragment.dismiss();
                }

                @Override
                public String[] getItems() {
                    return items;
                }

                @Override
                public Mode getMode() {
                    return Mode.MULTIPLE;
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
                public OnItemClickListener getItemClickListener() {
                    return listener;
                }
            });
        }

        public Builder setNegativeButton(String text, OnClickNegativeListener listener) {
            return setNegativeButton(text, 0, listener);
        }

        public Builder setNegativeButton(String text, int textColor, OnClickNegativeListener listener) {
            return setNegativeButton(text, textColor, -1, -1, listener);
        }

        public Builder setNegativeButton(final String text, final int textColor, final int textSize,
                                         final int height, final OnClickNegativeListener listener) {
            return setNegativeButton(new ProviderFooterNegative() {
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
                public OnClickNegativeListener getOnNegativeListener() {
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

        public Builder setPositiveButton(String text, OnClickPositiveListener listener) {
            return setPositiveButton(text, 0, listener);
        }

        public Builder setPositiveButton(String text, int textColor, OnClickPositiveListener listener) {
            return setPositiveButton(text, textColor, -1, -1, listener);
        }

        public Builder setPositiveButton(final String text, final int textColor, final int textSize,
                                         final int height, final OnClickPositiveListener listener) {
            return setPositiveButton(new ProviderFooterPositive() {
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
                public OnClickPositiveListener getOnPositiveListener() {

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

        public Builder setTitle(ProviderHeader mProviderHeader) {
            this.mProviderHeader = mProviderHeader;
            return this;
        }

        public Builder setMessage(ProviderContent providerContent) {
            this.mProviderContent = providerContent;
            return this;
        }

        public Builder setNegativeButton(ProviderFooterNegative mFooterNegative) {
            this.mFooterNegative = mFooterNegative;
            return this;
        }

        public Builder setPositiveButton(ProviderFooterPositive mFooterPositive) {
            this.mFooterPositive = mFooterPositive;
            return this;
        }

        ProviderHeader getProviderHeader() {
            return mProviderHeader;
        }

        ProviderContent getProviderContent() {
            return mProviderContent;
        }

        ProviderFooterNegative getFooterNegative() {
            return mFooterNegative;
        }

        ProviderFooterPositive getFooterPositive() {
            return mFooterPositive;
        }

        public Dialog build() {
            checkBuilderParams();
            SuperDialog dialog = new SuperDialog(this);
            dialog.show(mActivity.getSupportFragmentManager(), "superDialog");
            return dialog.getDialog();
        }

    }
}
