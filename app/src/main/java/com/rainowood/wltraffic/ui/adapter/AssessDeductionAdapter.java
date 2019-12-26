package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.AssessDeductionBean;

import java.util.List;

/**
 * @Author: shearson
 * @Time: 2019/12/26 19:52
 * @Desc: 考核管理扣分明细
 */
public class AssessDeductionAdapter extends BaseAdapter {

    private Context mContext;
    private List<AssessDeductionBean> mList;

    public AssessDeductionAdapter(Context mContext, List<AssessDeductionBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public AssessDeductionBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_assess_deduction, parent, false);
            holder = new ViewHolder();
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_score = convertView.findViewById(R.id.tv_score);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_score.setText(getItem(position).getScore());
        holder.tv_label.setText(getItem(position).getLabel());

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.OnItemClick(position);
            }
        });

        return convertView;
    }

    public interface OnItemListener{
        void OnItemClick(int position);
    }

    private OnItemListener itemListener;

    public void setItemListener(OnItemListener itemListener) {
        this.itemListener = itemListener;
    }

    private class ViewHolder{
        private LinearLayout ll_item;
        private TextView tv_title, tv_score, tv_label;
    }
}
