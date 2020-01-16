package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
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
 * @Date: 2019/12/31 13:37
 * @Desc: 支付详情
 */
public class PayDetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemLabelBean> mList;

    public PayDetailAdapter(Context mContext, List<SubItemLabelBean> mList) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_detail, parent, false);
            holder = new ViewHolder();
            holder.tv_plan_money = convertView.findViewById(R.id.tv_plan_money);
            holder.tv_plan_money_values = convertView.findViewById(R.id.tv_plan_money_values);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_plan_money.setText(getItem(position).getTitle());

        if (position < 3){
            holder.tv_plan_money_values.setText("￥" + getItem(position).getContent());
        }else {
            holder.tv_plan_money_values.setText(getItem(position).getContent());
        }
        return convertView;
    }

    class ViewHolder{
        private TextView tv_plan_money, tv_plan_money_values;
    }
}
