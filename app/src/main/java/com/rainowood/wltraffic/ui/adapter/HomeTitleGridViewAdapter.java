package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rainowood.wltraffic.R;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/20 16:48
 * @Desc: 首页项目类型adapter
 */
public class HomeTitleGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public HomeTitleGridViewAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_title_name, parent, false);
            holder = new ViewHolder();

            holder.tv_item_name = convertView.findViewById(R.id.tv_item_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        holder.tv_item_name.setText(getItem(position));

        /*
        设置属性：第一个标题的时候，标题居右，第二个标题的时候，标题居左
         */
        if (position == 0) {
            holder.tv_item_name.setGravity(Gravity.RIGHT);
            holder.tv_item_name.setPadding(0, 0, 30, 0);
            // 设置默认的选中状态
        }

        if (position == 1) {
            holder.tv_item_name.setGravity(Gravity.LEFT);
            holder.tv_item_name.setPadding(30, 0, 0, 0);
        }

        // 点击事件
        holder.tv_item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleOnClick.OnClick(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    // 点击事件
    public interface TitleOnClick {
        void OnClick(int position);
    }

    private TitleOnClick mTitleOnClick;

    public void setmTitleOnClick(TitleOnClick mTitleOnClick) {
        this.mTitleOnClick = mTitleOnClick;
    }

    private class ViewHolder {
        private TextView tv_item_name;
    }
}
