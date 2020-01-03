package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ProjectInfoBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/20 15:27
 * @Desc: 在线项目
 */
public class HomeListViewAdapter extends BaseAdapter {

    private List<ProjectInfoBean> mList;
    private Context mContext;

    public HomeListViewAdapter(Context mContext, List<ProjectInfoBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ProjectInfoBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_listview, parent, false);
            // 生成一个ViewHolder 对象
            holder = new ViewHolder();
            // 映射
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            holder.ll_home_item = convertView.findViewById(R.id.ll_home_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        holder.tv_title.setText(mList.get(position).getItemName());
        holder.tv_label.setText(mList.get(position).getProportion());
        // item 的点击事件
        holder.ll_home_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.ItemOnClick(position);
            }
        });

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
