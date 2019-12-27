package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubItemLabelBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 16:56
 * @Desc: 实名制
 */
public class RealNameAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemLabelBean> mList;

    public RealNameAdapter(Context mContext, List<SubItemLabelBean> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_real_name, parent, false);
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            holder.ll_item = convertView.findViewById(R.id.ll_item);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_label.setText(getItem(position).getContent());

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
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
        private TextView tv_title, tv_label;
        private LinearLayout ll_item;
    }
}
