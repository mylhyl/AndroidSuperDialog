package com.mylhyl.superdialog.res.drawable;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.Gravity;

/**
 * 代码进度条
 * Created by hupei on 2017/3/29.
 */
public final class Progress {
    private static final int colorsBg[] = {0xffBEBEBE, 0xffF5F5F5};
    private static final int colorsSecondaryProgress[] = {0xff85B0E9, 0xff165CBC};
    private static final int colorsProgress[] = {0xff85B0E9, 0xff165CBC};
    private LayerDrawable mLayerDrawable;

    public Progress() {
        GradientDrawable background = createBackground();
        ClipDrawable secondaryProgress = createSecondaryProgress();
        ClipDrawable progress = createProgress();
        Drawable[] layers = {background, secondaryProgress, progress};
        mLayerDrawable = new LayerDrawable(layers);
        mLayerDrawable.setId(0, android.R.id.background);
        mLayerDrawable.setId(1, android.R.id.secondaryProgress);
        mLayerDrawable.setId(2, android.R.id.progress);
    }

    private GradientDrawable createBackground() {
/*     <item android:id="@android:id/background">
            <shape>
                <corners android:radius="5dip" />

                <gradient
                android:angle="270"
                android:centerY="0.75"
                android:endColor="#F5F5F5"
                android:startColor="#BEBEBE" />
            </shape>
        </item>*/

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(5);
        drawable.setGradientCenter(0.5f, 0.75f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setColors(colorsBg);
        }
        return drawable;
    }

    private ClipDrawable createSecondaryProgress() {
/*      <item android:id="@android:id/secondaryProgress">
            <clip>
                <shape>
                    <corners android:radius="0dip" />

                    <gradient
                    android:angle="270"
                    android:centerY="0.75"
                    android:endColor="#165CBC"
                    android:startColor="#85B0E9" />
                    </shape>
            </clip>
        </item>*/
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientCenter(0.5f, 0.75f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setColors(colorsSecondaryProgress);
        }
        ClipDrawable clipDrawable = new ClipDrawable(drawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
        return clipDrawable;
    }

    private ClipDrawable createProgress() {
/*      <item android:id="@android:id/progress">
            <clip>
                <shape>
                    <corners android:radius="5dip" />

                    <gradient
                    android:angle="270"
                    android:centerY="0.75"
                    android:endColor="#165CBC"
                    android:startColor="#85B0E9" />
                </shape>
            </clip>
        </item>*/

        //android:angle 默认是Orientation.TOP_BOTTOM，见源码 GradientDrawableGradient_angle 处
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(5);//android:radius
        drawable.setGradientCenter(0.5f, 0.75f);//x默认是0.5，android:centerY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setColors(colorsProgress);//见源码 updateGradientDrawableGradient
        }
        ClipDrawable clipDrawable = new ClipDrawable(drawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
        return clipDrawable;
    }

    public LayerDrawable getLayerDrawable() {
        return mLayerDrawable;
    }
}
