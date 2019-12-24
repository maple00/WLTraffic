package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ProjectProgressBean;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.widget.MeasureListView;

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
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_project_progress, parent, false);
            holder = new ViewHolder();

            holder.tv_item_name = convertView.findViewById(R.id.tv_item_name);
            holder.iv_mark = convertView.findViewById(R.id.iv_mark);
            holder.iv_next_content = convertView.findViewById(R.id.iv_next_content);
            holder.ll_content = convertView.findViewById(R.id.ll_content);
            holder.lv_content = convertView.findViewById(R.id.lv_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //
        holder.tv_item_name.setText(getItem(position).getTitle());

        // 判断里面是否有未完成的项目  iv_mark
        int flagCount = 0;      // mark 完成的
        for (int i = 0; i < ListUtils.getSize(getItem(position).getmList()); i++) {
            boolean finished = getItem(position).getmList().get(i).isFinished();
            if (!finished){
                flagCount++;
            }
        }
        if (flagCount > 0){
            holder.iv_mark.setVisibility(View.VISIBLE);
        }else {
            holder.iv_mark.setVisibility(View.GONE);
        }

        // 查询每个目录下的子目录
        SubProjectProgressAdapter subProgressAdapter = new SubProjectProgressAdapter(mContext, getItem(position).getmList());
        holder.lv_content.setAdapter(subProgressAdapter);
        subProgressAdapter.notifyDataSetChanged();

        // 点击
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = getItem(position).getHasSelected() % 2;
                if (value == 0){               // 点击了，默认为false
                    // 更换图标
                    holder.iv_next_content.setBackgroundResource(R.drawable.ic_icon_after);
                    getItem(position).setHasSelected(++value);
                    holder.lv_content.setVisibility(View.VISIBLE);

                }else {
                    holder.iv_next_content.setBackgroundResource(R.drawable.ic_icon_front);
                    getItem(position).setHasSelected(++value);
                    holder.lv_content.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_item_name;
        private ImageView iv_mark, iv_next_content;
        private LinearLayout ll_content;
        private MeasureListView lv_content;
    }
}
