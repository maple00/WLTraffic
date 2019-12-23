package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubItemWordBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 9:34
 * @Desc: 文档列表
 */
public class ItemDetailWordListAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemWordBean> mList;

    public ItemDetailWordListAdapter(Context mContext, List<SubItemWordBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubItemWordBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_sub_content, parent, false);
            holder = new ViewHolder();

            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_word_title = convertView.findViewById(R.id.tv_word_title);

            // 点击事件
            holder.ll_download = convertView.findViewById(R.id.ll_download);
            holder.ll_preview = convertView.findViewById(R.id.ll_preview);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        holder.tv_title.setText(getItem(position).getBackEditTitle());
        holder.tv_word_title.setText(getItem(position).getWordTitle());


        // 点击事件
        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "下载：" + getItem(position).getWordTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.ll_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "预览：" + getItem(position).getWordTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_title, tv_word_title;
        private LinearLayoutCompat ll_download, ll_preview;
    }
}
