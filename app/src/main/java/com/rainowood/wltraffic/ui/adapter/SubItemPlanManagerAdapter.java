package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubPlanManagerBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 15:06
 * @Desc: 计划管理中的资金来源
 */
public class SubItemPlanManagerAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubPlanManagerBean> mList;

    public SubItemPlanManagerAdapter(Context mContext, List<SubPlanManagerBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubPlanManagerBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_plan_manager_capital, parent, false);
            holder = new ViewHolder();

            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_value = convertView.findViewById(R.id.tv_value);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_value.setText(getItem(position).getValue());

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_title, tv_value;
    }
}
