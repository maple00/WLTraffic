package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 9:10
 * @Desc: 附件列表adapter
 */
public class AttachmentListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;


    public AttachmentListAdapter(Context mContext, List<String> mList) {
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_attachment_list, parent, false);
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.ll_download = convertView.findViewById(R.id.ll_download);
            holder.ll_preview = convertView.findViewById(R.id.ll_preview);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position));

        // 下载和预览
        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachListener.onAttachDownloadClick(position);
            }
        });

        holder.ll_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachListener.onAttachPreviewClick(position);
            }
        });

        return convertView;
    }

    public interface OnAttachListener{
        void onAttachDownloadClick(int position);
        void onAttachPreviewClick(int position);
    }

    private OnAttachListener attachListener;

    public void setAttachListener(OnAttachListener attachListener) {
        this.attachListener = attachListener;
    }

    private class ViewHolder{
        private TextView tv_title;
        private LinearLayoutCompat ll_download, ll_preview;
    }
}
