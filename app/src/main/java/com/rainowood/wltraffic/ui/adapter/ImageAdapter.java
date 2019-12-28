package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rainowood.wltraffic.R;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/24 17:33
 * @Desc: 最多只能展示9张图片adapter
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public ImageAdapter(Context mContext, List<String> mList) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_imgage, parent, false);
            holder = new ViewHolder();

            holder.iv_img = convertView.findViewById(R.id.iv_img);
            holder.tv_show_more_img = convertView.findViewById(R.id.tv_show_more_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 图片最多只能显示9张，超过9张后用加号处理
        if (position < 9){
            Glide.with(parent.getContext()).load(getItem(position))
                    .placeholder(R.drawable.icon_hint_network)//加载未完成时显示占位图
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.iv_img);//显示的位置
        }

        if (getCount() > 9){
            if (position == 8){
                holder.tv_show_more_img.setText("+ " + (getCount() - 9));
            }
        }

        holder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick.imgClick(position);
            }
        });

        notifyDataSetChanged();
        return convertView;
    }

    public interface ImgOnClickListener {
        void imgClick(int position);
    }

    private ImgOnClickListener imgClick;

    public void setImgClick(ImgOnClickListener imgClick) {
        this.imgClick = imgClick;
    }

    private class ViewHolder {
        private ImageView iv_img;
        private TextView tv_show_more_img;
    }
}
