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
 * @Date: 2019/12/28 16:28
 * @Desc:
 */
public class TenderSubAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubQuestionBean> mList;

    public TenderSubAdapter(Context mContext, List<SubQuestionBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : (mList.size() > 4 ? 4 : mList.size());
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
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tender_sub_others, parent, false);
            holder = new ViewHolder();
            holder.ll_item = convertView.findViewById(R.id.ll_item);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_label.setText(getItem(position).getProblem());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });

        return convertView;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private class ViewHolder{
        private LinearLayout ll_item;
        private TextView tv_label;
    }
}
