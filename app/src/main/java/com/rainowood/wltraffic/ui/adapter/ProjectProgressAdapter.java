package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ProjectProgressBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:34
 * @Desc: 进度管理
 */
public class ProjectProgressAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProjectProgressBean> mList;

    public ProjectProgressAdapter(Context mContext, List<ProjectProgressBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ProjectProgressBean getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_project_progress, parent, false);
            holder = new ViewHolder();

            holder.tv_item_name = convertView.findViewById(R.id.tv_item_name);
            holder.iv_mark = convertView.findViewById(R.id.iv_mark);
            holder.iv_next_content = convertView.findViewById(R.id.iv_next_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //
        holder.tv_item_name.setText(getItem(position).getTitle());
        // 判断里面是否有未完成的项目


        // 点击
        holder.iv_next_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }


    private class ViewHolder {
        private TextView tv_item_name;
        private ImageView iv_mark, iv_next_content;
    }
}
