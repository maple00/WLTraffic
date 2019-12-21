package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubIteProjectBean;

import java.util.List;

/**
 * @Author: shearson
 * @Time: 2019/12/21 22:46
 * @Desc: 项目详情中的子项目
 */
public class ItemDetailSubItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubIteProjectBean> mList;

    public ItemDetailSubItemAdapter(Context mContext, List<SubIteProjectBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubIteProjectBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_project, parent, false);
            holder = new ViewHolder();
            //
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            holder.iv_icon = convertView.findViewById(R.id.iv_icon);
            holder.tv_label = convertView.findViewById(R.id.tv_label);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.iv_icon.setBackgroundResource(mList.get(position).getIcon());
        holder.tv_label.setText(mList.get(position).getLabel());

        // 点击逻辑
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.ItemOnClick(position);
            }
        });

        return convertView;
    }

    public interface ItemOnClickListener{
        void ItemOnClick(int position);
    }

    private ItemOnClickListener clickListener;

    public void setClickListener(ItemOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private class ViewHolder{
        private LinearLayout ll_item;
        private AppCompatImageView iv_icon;
        private AppCompatTextView tv_label;
    }
}
