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
 * @Date: 2019/12/23 9:26
 * @Desc: 项目详情_文档列表
 */
public class ItemDetailWordAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemListContentBean> mList;

    public ItemDetailWordAdapter(Context mContext, List<SubItemListContentBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_content, parent, false);
            holder = new ViewHolder();

            holder.tv_order = convertView.findViewById(R.id.tv_order);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.lv_content = convertView.findViewById(R.id.lv_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_order.setText(mList.get(position).getId());
        holder.tv_title.setText(mList.get(position).getTitle());

        // 文档列表
        ItemDetailWordListAdapter adapter = new ItemDetailWordListAdapter(mContext, getItem(position).getmList());
        holder.lv_content.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_order, tv_title;
        private ListView lv_content;
    }
}
