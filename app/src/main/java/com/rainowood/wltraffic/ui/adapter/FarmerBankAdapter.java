package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.FarmerBankBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 13:27
 * @Desc: 银行代发制
 */
public class FarmerBankAdapter extends BaseAdapter {

    private Context mContext;
    private List<FarmerBankBean> mList;

    public FarmerBankAdapter(Context mContext, List<FarmerBankBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public FarmerBankBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_farmer_bank_funds, parent, false);
            holder = new ViewHolder();
            holder.tv_money = convertView.findViewById(R.id.tv_money);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_money.setText(getItem(position).getMoney());
        holder.tv_time.setText(getItem(position).getTime());
        holder.tv_content.setText(getItem(position).getContent());
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_money, tv_time, tv_content;
    }
}
