package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubItemListContentBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/6 9:10
 * @Desc: 带标题的附件adapter
 */
public class ItemTitleAttachAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemListContentBean> mList;

    public ItemTitleAttachAdapter(Context mContext, List<SubItemListContentBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubItemListContentBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_with_title_attachment, parent, false);
            holder = new ViewHolder();

            holder.lv_content = convertView.findViewById(R.id.lv_content);
            holder.tv_word_title = convertView.findViewById(R.id.tv_word_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_word_title.setText(mList.get(position).getWordTitle());
        // 文档列表
        ItemAttachListAdapter adapter = new ItemAttachListAdapter(mContext, getItem(position).getmList());
        holder.lv_content.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_word_title;
        private ListView lv_content;
    }
}
