package com.mylhyl.superdialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mylhyl.superdialog.callback.ProviderContent;
import com.mylhyl.superdialog.callback.ProviderHeader;
import com.mylhyl.superdialog.res.drawable.BgBtn;
import com.mylhyl.superdialog.res.drawable.BgLayout;
import com.mylhyl.superdialog.res.values.ColorRes;
import com.mylhyl.superdialog.res.values.DimenRes;
import com.mylhyl.superdialog.view.DividerView;
import com.mylhyl.superdialog.view.SuperTextView;

/**
 * Created by hupei on 2016/3/8 19:30.
 */
class ContentMultipleView extends ListView {

    public ContentMultipleView(Context context, SuperDialog.Builder builder) {
        super(context);
        initData(builder);
    }

    private void initData(SuperDialog.Builder builder) {
        setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        setSelector(new BgLayout());
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

    class ItemAdapter extends BaseAdapter {
        class ViewHolder {
            TextView item;
        }

        private String[] items;
        private ProviderContent providerContent;
        private ProviderHeader providerHeader;

        public ItemAdapter(SuperDialog.Builder builder) {
            this.providerContent = builder.getProviderContent();
            this.providerHeader = builder.getProviderHeader();
            this.items = providerContent.getItems();
        }

        @Override
        public int getCount() {
            if (items != null)
                return items.length;
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (items != null)
                return items[position];
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
                textView.setTextSize(providerContent.getTextSize());
                textView.setTextColor(providerContent.getTextColor());
                textView.setHeight(providerContent.getItemHeight());
                viewHolder.item = textView;
                convertView = textView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position == 0 && providerHeader == null)
                viewHolder.item.setBackgroundDrawable(new BgBtn(DimenRes.radius, DimenRes.radius, 0, 0));
            else if (position == getCount() - 1)
                viewHolder.item.setBackgroundDrawable(new BgBtn(0, 0, DimenRes.radius, DimenRes.radius));
            else
                viewHolder.item.setBackgroundDrawable(new BgBtn(0, 0, 0, 0));
            viewHolder.item.setText(String.valueOf(getItem(position)));
            return convertView;
        }
    }
}
