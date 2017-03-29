package com.mylhyl.superdialog.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mylhyl.superdialog.callback.ProviderContentProgress;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.res.drawable.Progress;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by hupei on 2017/3/28
 */
class BodyProgressView extends AutoLinearLayout {
    private ProgressBar mProgressBar;
    private Handler mViewUpdateHandler;
    private Controller.Params mParams;

    public BodyProgressView(Context context, Controller.Params params) {
        super(context);
        mParams = params;
        initData();
    }

    private void initData() {
        setOrientation(LinearLayout.VERTICAL);

        if (mParams.mProviderHeader != null && mParams.mFooterNegative == null
                && mParams.mFooterPositive == null) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(0, 0, radius, radius, mParams.mBackgroundColor));
        } else if (mParams.mProviderHeader == null && (mParams.mFooterNegative != null
                || mParams.mFooterPositive != null)) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(radius, radius, 0, 0, mParams.mBackgroundColor));
        } else if (mParams.mFooterNegative == null && mParams.mFooterPositive == null
                && mParams.mProviderHeader == null) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(radius, radius, radius, radius, mParams
                    .mBackgroundColor));
        } else {
            setBackgroundColor(mParams.mBackgroundColor);
        }
        ProviderContentProgress providerContent = (ProviderContentProgress) mParams.mProviderContent;
        if (providerContent == null) return;
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, providerContent.getHeight());
        int[] margins = providerContent.getMargins();
        params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        //进度条
        mProgressBar = new ProgressBar(getContext());
        setFieldValue(mProgressBar, "mOnlyIndeterminate", new Boolean(false));
        mProgressBar.setIndeterminate(false);

        Drawable progressDrawable = providerContent.getProgressDrawable();
        if (progressDrawable == null) {//库自带
            LayerDrawable layerDrawable = new Progress().getLayerDrawable();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Drawable d = getMethod("tileify", mProgressBar, new Object[]{layerDrawable, false});
                mProgressBar.setProgressDrawable(d);
            } else {
                mProgressBar.setProgressDrawableTiled(layerDrawable);
            }
        } else {//使用者自定义xml
            mProgressBar.setProgressDrawable(progressDrawable);
        }

        addView(mProgressBar, params);

        final SuperTextView textView = new SuperTextView(getContext());
        textView.setTextSize(providerContent.getTextSize());
        textView.setTextColor(providerContent.getTextColor());
        addView(textView);

        mViewUpdateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int progress = mProgressBar.getProgress();
                int max = mProgressBar.getMax();
                int percent = (int) (((float) progress / (float) max) * 100);
                textView.setText(percent + "%");
            }
        };
    }

    public void setProgress(int max, int progress) {
        mProgressBar.setMax(max);
        mProgressBar.setProgress(progress);
        mProgressBar.setSecondaryProgress(progress + 10);
        onProgressChanged();
    }

    private void onProgressChanged() {
        if (mViewUpdateHandler != null && !mViewUpdateHandler.hasMessages(0)) {
            mViewUpdateHandler.sendEmptyMessage(0);
        }
    }

    private static Drawable getMethod(String MethodName, Object o, Object[] paras) {
        Drawable newDrawable = null;
        try {
            Class c[] = new Class[2];
            c[0] = Drawable.class;
            c[1] = boolean.class;
            Method method = ProgressBar.class.getDeclaredMethod(MethodName, c);
            method.setAccessible(true);
            newDrawable = (Drawable) method.invoke(o, paras);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newDrawable;
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    private static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        makeAccessible(field);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("unchecked")
    protected static Field getDeclaredField(final Class clazz, final String fieldName) {
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 强制转换fileld可访问.
     */
    protected static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }
}
