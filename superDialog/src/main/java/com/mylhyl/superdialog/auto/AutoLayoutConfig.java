package com.mylhyl.superdialog.auto;

import android.content.Context;

/**
 * Created by hupei on 2016/3/8 17:32.
 */
public class AutoLayoutConfig {
    private static AutoLayoutConfig sInstance;

    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int mScreenWidth;
    private int mScreenHeight;

    private int mDesignWidth = 1080;
    private int mDesignHeight = 1920;

    private float mScale;

    private AutoLayoutConfig() {
    }

    public static void init(Context context ) {
        if (sInstance == null) {
            sInstance = new AutoLayoutConfig();
            sInstance.initInternal(context, new AutoScaleAdapter(context));
        }
    }

    public static AutoLayoutConfig getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Must init before using.");
        }
        return sInstance;
    }

    private void initInternal(Context context, AutoScaleAdapter scaleAdapter) {
        checkParams();
        int[] size = AutoUtils.getRealScreenSize(context);
        mScreenWidth = size[0];
        mScreenHeight = size[1];

        if (mScreenWidth > mScreenHeight) {//横屏状态下，宽高互换，按竖屏模式计算scale
            mScreenWidth = mScreenWidth + mScreenHeight;
            mScreenHeight = mScreenWidth - mScreenHeight;
            mScreenWidth = mScreenWidth - mScreenHeight;
        }

        float deviceScale = (float) mScreenHeight / mScreenWidth;
        float designScale = (float) mDesignHeight / mDesignWidth;
        if (deviceScale <= designScale) {//高宽比小于等于标准比（较标准屏宽一些），以高为基准计算scale（以短边计算），否则以宽为基准计算scale
            mScale = (float) mScreenHeight / mDesignHeight;
        } else {
            mScale = (float) mScreenWidth / mDesignWidth;
        }

        if (scaleAdapter != null) {
            mScale = scaleAdapter.adapt(mScale, mScreenWidth, mScreenHeight);
        }
    }

    private void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
        }
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getDesignWidth() {
        return mDesignWidth;
    }

    public int getDesignHeight() {
        return mDesignHeight;
    }

    public float getScale() {
        return mScale;
    }
}
