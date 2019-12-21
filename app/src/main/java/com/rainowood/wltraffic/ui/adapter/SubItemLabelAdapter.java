package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubItemLabelBean;

import java.util.List;

/**
 * @Author: shearson
 * @Time: 2019/12/21 22:23
 * @Desc: 项目详情的 标签内容 adapter
 */
public class SubItemLabelAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemLabelBean> mList;

    public SubItemLabelAdapter(Context mContext, List<SubItemLabelBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubItemLabelBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_project_detail_title, parent, false);
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_content = convertView.findViewById(R.id.tv_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        holder.tv_title.setText(mList.get(position).getTitle());
        holder.tv_content.setText(mList.get(position).getContent());

        return convertView;
    }


    private class ViewHolder {
        private TextView tv_title, tv_content;
    }
}
