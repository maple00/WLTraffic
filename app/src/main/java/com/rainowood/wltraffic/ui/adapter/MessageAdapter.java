package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubMsgBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/20 15:27
 * @Desc: 前期项目
 */
public class MessageAdapter extends BaseAdapter {

    private List<SubMsgBean> mList;
    private Context mContext;

    public MessageAdapter(Context mContext, List<SubMsgBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubMsgBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_before_item, parent, false);
            // 生成一个ViewHolder 对象
            holder = new ViewHolder();
            // 映射
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            holder.ll_home_item = convertView.findViewById(R.id.ll_home_before_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        holder.tv_title.setText(mList.get(position).getText());
        holder.tv_label.setText(mList.get(position).getUpdateTime());
        // item 的点击事件
        holder.ll_home_item.setOnClickListener(v -> onClick.ItemOnClick(position));

        return convertView;
    }

    public interface ItemOnClick {
        void ItemOnClick(int position);
    }

    private ItemOnClick onClick;

    public void setOnClick(ItemOnClick onClick) {
        this.onClick = onClick;
    }

    private class ViewHolder {
        private TextView tv_title, tv_label;
        private LinearLayout ll_home_item;
    }
}
