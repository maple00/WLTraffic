package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubSpecialAccountListBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:35
 * @Desc: 专户制
 */
public class SpecialAccountAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubSpecialAccountListBean> mList;

    public SpecialAccountAdapter(Context mContext, List<SubSpecialAccountListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubSpecialAccountListBean getItem(int position) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_special_account, parent, false);
            holder = new ViewHolder();
            holder.tv_money = convertView.findViewById(R.id.tv_money);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.tv_note = convertView.findViewById(R.id.tv_note);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_time.setText(getItem(position).getTransfeTime());         // 支付时间
        holder.tv_money.setText("￥ " + getItem(position).getMoney());              // 支付金额
        holder.tv_note.setText(getItem(position).getText());                // 备注

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_money, tv_time, tv_note;
    }
}
