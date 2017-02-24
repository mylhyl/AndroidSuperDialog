package com.mylhyl.superdialog.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.res.values.ColorRes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hupei on 2016/3/8 19:30.
 */
class BodyMultipleView extends ListView {
    private Controller.Params mParams;
    private ItemAdapter mAdapter;

    public BodyMultipleView(Context context, Controller.Params params) {
        super(context);
        this.mParams = params;
        initData();
    }

    private void initData() {
        mAdapter = new ItemAdapter(mParams);
        setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                .MATCH_PARENT));
        setSelector(new ColorDrawable(Color.TRANSPARENT));
        final ProviderContent providerContent = mParams.mProviderContent;
        if (providerContent == null) return;
        setDivider(new ColorDrawable(ColorRes.divider));
        setDividerHeight(1);
        setAdapter(mAdapter);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                providerContent.dismiss();
                if (providerContent.getItemClickListener() != null)
                    providerContent.getItemClickListener().onItemClick(position);
            }
        });
    }

    public void refreshItems() {
        mAdapter.notifyDataSetChanged();
    }

    class ItemAdapter<T> extends BaseAdapter {
        class ViewHolder {
            TextView item;
        }

        private List<T> mItems;
        private int mRadius;
        private int mBackgroundColor;
        private ProviderContent mProviderContent;
        private ProviderHeader mProviderHeader;

        public ItemAdapter(Controller.Params params) {
            this.mRadius = params.mRadius;
            this.mBackgroundColor = params.mBackgroundColor;
            this.mProviderContent = params.mProviderContent;
            this.mProviderHeader = params.mProviderHeader;
            Object entity = mProviderContent.getItems();
            if (entity != null && entity instanceof Iterable) {
                this.mItems = (List<T>) entity;
            } else if (entity != null && entity.getClass().isArray()) {
                this.mItems = Arrays.asList((T[]) entity);
            } else {
                throw new IllegalArgumentException("entity must be an Array or an Iterable.");
            }
        }

        @Override
        public int getCount() {
            if (mItems != null)
                return mItems.size();
            return 0;
        }

        @Override
        public T getItem(int position) {
            if (mItems != null)
                return mItems.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                SuperTextView textView = new SuperTextView(getContext());
                textView.setTextSize(mProviderContent.getTextSize());
                textView.setTextColor(mProviderContent.getTextColor());
                textView.setHeight(mProviderContent.getItemHeight());
                viewHolder.item = textView;
                convertView = textView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //top
            if (position == 0 && mProviderHeader == null) {
                if (getCount() == 1)
                    viewHolder.item.setBackgroundDrawable(new BgBtn(mRadius, mRadius, mRadius,
                            mRadius, mBackgroundColor));
                else
                    viewHolder.item.setBackgroundDrawable(new BgBtn(mRadius, mRadius, 0, 0,
                            mBackgroundColor));
            }
            //bottom
            else if (position == getCount() - 1)
                viewHolder.item.setBackgroundDrawable(new BgBtn(0, 0, mRadius, mRadius,
                        mBackgroundColor));

                //middle
            else
                viewHolder.item.setBackgroundDrawable(new BgBtn(0, 0, 0, 0, mBackgroundColor));

            viewHolder.item.setText(String.valueOf(getItem(position).toString()));
            return convertView;
        }
    }
}
