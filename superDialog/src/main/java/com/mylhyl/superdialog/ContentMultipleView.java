package com.mylhyl.superdialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.view.SuperTextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hupei on 2016/3/8 19:30.
 */
class ContentMultipleView extends ListView {

    public ContentMultipleView(Context context, SuperDialog.Builder builder) {
        super(context);
        initData(builder);
    }

    private void initData(SuperDialog.Builder builder) {
        setAlpha(builder.mAlpha);
        setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        setSelector(new ColorDrawable(Color.TRANSPARENT));
        final ProviderContent providerContent = builder.getProviderContent();
        if (providerContent == null) return;
        setDivider(new ColorDrawable(ColorRes.divider));
        setDividerHeight(1);
        setAdapter(new ItemAdapter(builder));
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                providerContent.dismiss();
                if (providerContent.getItemClickListener() != null)
                    providerContent.getItemClickListener().onItemClick(position);
            }
        });
    }

    class ItemAdapter<T> extends BaseAdapter {
        class ViewHolder {
            TextView item;
        }

        private List<T> mItems;
        private int Radius;
        private ProviderContent mProviderContent;
        private ProviderHeader mProviderHeader;

        public ItemAdapter(SuperDialog.Builder builder) {
            this.Radius = builder.mRadius;
            this.mProviderContent = builder.getProviderContent();
            this.mProviderHeader = builder.getProviderHeader();
            Object entity = mProviderContent.getItems();
            if (entity instanceof Iterable) {
                this.mItems = (List<T>) entity;
            } else if (entity.getClass().isArray()) {
                this.mItems = Arrays.asList((T[]) entity);
            }else {
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
            if (position == 0 && mProviderHeader == null)
                viewHolder.item.setBackgroundDrawable(new BgBtn(Radius, Radius, 0, 0));
            else if (position == getCount() - 1)
                viewHolder.item.setBackgroundDrawable(new BgBtn(0, 0, Radius, Radius));
            else
                viewHolder.item.setBackgroundDrawable(new BgBtn(0, 0, 0, 0));
            viewHolder.item.setText(String.valueOf(getItem(position).toString()));
            return convertView;
        }
    }
}
