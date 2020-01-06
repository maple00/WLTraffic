package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubQuestionBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 10:43
 * @Desc: 招投标中质疑答疑、补漏列表
 */
public class TenderListAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubQuestionBean> mList;

    public TenderListAdapter(Context mContext, List<SubQuestionBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubQuestionBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tender_list, parent, false);
            holder = new ViewHolder();
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 问题
        holder.tv_content.setText(getItem(position).getProblem());

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });

        return convertView;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private class ViewHolder {
        private TextView tv_content;
        private LinearLayout ll_item;
    }
}
