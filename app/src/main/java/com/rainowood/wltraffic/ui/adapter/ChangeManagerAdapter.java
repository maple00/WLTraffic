package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubChangeBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 10:25
 * @Desc: 变更管理
 */
public class ChangeManagerAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubChangeBean> mList;

    public ChangeManagerAdapter(Context mContext, List<SubChangeBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubChangeBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_change, parent, false);
            holder = new ViewHolder();

            holder.ll_content = convertView.findViewById(R.id.ll_content);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            holder.tv_change_money = convertView.findViewById(R.id.tv_change_money);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_content.setText(getItem(position).getChangeMatter());     //变更内容
        holder.tv_change_money.setText(getItem(position).getChangeMoney());     // 变更金额
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnItemClick(position);
            }
        });

        return convertView;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private class ViewHolder{
        private LinearLayout ll_content;
        private TextView tv_content, tv_change_money;
    }
}
