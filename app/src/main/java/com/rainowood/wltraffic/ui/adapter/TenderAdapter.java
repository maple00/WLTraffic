package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubQuestionBean;
import com.rainowood.wltraffic.domain.SubQuestionsAndBareBean;
import com.rainwood.tools.widget.MeasureListView;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 16:16
 * @Desc: 质疑答疑、补漏
 */
public class TenderAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubQuestionsAndBareBean> mList;

    public TenderAdapter(Context mContext, List<SubQuestionsAndBareBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubQuestionsAndBareBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tender_others, parent, false);
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.lv_list = convertView.findViewById(R.id.lv_list);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(getItem(position).getTitle());

        // 质疑答疑
        if (position == 0){
            TenderSubAdapter subAdapter = new TenderSubAdapter(mContext, mList.get(position).getOne());
            holder.lv_list.setAdapter(subAdapter);
            subAdapter.setClickListener(itemClickListener);
            // 查看全部
            holder.tv_label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }
        // 补漏
        else{
            TenderSubAdapter subAdapter = new TenderSubAdapter(mContext, mList.get(position).getTwo());
            holder.lv_list.setAdapter(subAdapter);
            subAdapter.setClickListener(itemClickListener);
            // 查看全部
            holder.tv_label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }

        return convertView;
    }

    private TenderSubAdapter.OnItemClickListener itemClickListener;

    public void setItemClickListener(TenderSubAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnClickViewListener {
        void onItemClick(int position);
    }

    private OnClickViewListener listener;

    public void setListener(OnClickViewListener listener) {
        this.listener = listener;
    }

    private class ViewHolder {
        private TextView tv_title, tv_label;
        private MeasureListView lv_list;
    }
}
