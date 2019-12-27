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
 * @Author: a797s
 * @Date: 2019/12/27 10:55
 * @Desc: 变更详情
 */
public class ChangeDetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemLabelBean> mList;

    public ChangeDetailAdapter(Context mContext, List<SubItemLabelBean> mList) {
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
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_change_detail, parent, false);
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        if ("变更金额".equals(getItem(position).getTitle())){
            holder.tv_content.setTextColor(mContext.getResources().getColor(R.color.colorOrange1));
            holder.tv_content.setTextSize(18);
        }else {
            holder.tv_content.setTextColor(mContext.getResources().getColor(R.color.colorWord));
            holder.tv_content.setTextSize(14);
        }
        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_content.setText(getItem(position).getContent());

        return convertView;
    }

    private class  ViewHolder{
        private TextView tv_title, tv_content;
    }
}
