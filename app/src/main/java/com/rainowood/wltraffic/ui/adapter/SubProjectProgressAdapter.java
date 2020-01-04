package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubProjectProgressBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:34
 * @Desc: 进度管理
 */
public class SubProjectProgressAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubProjectProgressBean> mList;

    SubProjectProgressAdapter(Context mContext, List<SubProjectProgressBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubProjectProgressBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_project_progress, parent, false);
            holder = new ViewHolder();

            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.iv_finished = convertView.findViewById(R.id.iv_finished);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).getName());
        holder.tv_time.setText(getItem(position).getTime());

        // 是否完成
        if (getItem(position).isImgState()) {        // 如果完成了，则标记
            holder.iv_finished.setVisibility(View.VISIBLE);
        } else {
            // 完成标志隐藏
            holder.iv_finished.setVisibility(View.GONE);
            // 设置字体
            holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.colorWord));
        }

        return convertView;
    }
    private class ViewHolder {
        private TextView tv_title, tv_time;
        private ImageView iv_finished;
    }
}
