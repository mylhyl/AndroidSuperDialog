package com.mylhyl.superdialog.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.mylhyl.superdialog.SuperDialog;
import com.mylhyl.superdialog.callback.CreateLayout;
import com.mylhyl.superdialog.callback.ProviderFooterNegative;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.res.values.ColorRes;

/**
 * Created by hupei on 2016/3/18 16:27.
 */
class CreateLayoutImpl implements CreateLayout {
    private Context mContext;
    private Controller.Params mParams;
    private LinearLayout mRoot;


    public CreateLayoutImpl(Context context, Controller.Params params) {
        this.mContext = context;
        this.mParams = params;
        this.mRoot = new AutoLinearLayout(context);
        this.mRoot.setOrientation(LinearLayout.VERTICAL);
        this.mRoot.setAlpha(params.mAlpha);
    }

    @Override
    public void buildHead() {
        HeaderView titleView = new HeaderView(mContext, mParams);
        mRoot.addView(titleView);
    }

    @Override
    public void buildMultipleBody() {
        mRoot.addView(new BodyMultipleView(mContext, mParams),
                new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void buildSingleBody() {
        mRoot.addView(new BodySingleView(mContext, mParams));
    }

    @Override
    public void buildMultipleFooter() {
        final ProviderFooterNegative footerNegative = mParams.mFooterNegative;
        final SuperDialog.OnClickNegativeListener onNegativeListener = footerNegative.getOnNegativeListener();
        //取消
        SuperTextView footerView = new SuperTextView(mContext);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.topMargin = 10;
        footerView.setLayoutParams(layoutParams);
        footerView.setClickable(true);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                footerNegative.dismiss();
                if (onNegativeListener != null)
                    onNegativeListener.onClick(v);
            }
        });
        footerView.setText(footerNegative.getTitle());
        footerView.setTextSize(footerNegative.getTextSize());
        footerView.setTextColor(footerNegative.getTextColor());
        footerView.setHeight(footerNegative.getHeight());
        int radius = mParams.mRadius;
        footerView.setBackgroundDrawable(new BgBtn(radius, radius, radius, radius, ColorRes.bgDialog));
        mRoot.addView(footerView);
    }

    @Override
    public void buildSingleFooter() {
        //添加消息与底部分隔线
        DividerView dividerViewV = new DividerView(mContext);
        dividerViewV.setVertical();
        mRoot.addView(dividerViewV);
        mRoot.addView(new FooterView(mContext, mParams));
    }

    @Override
    public View buildView() {
        return mRoot;
    }
}
