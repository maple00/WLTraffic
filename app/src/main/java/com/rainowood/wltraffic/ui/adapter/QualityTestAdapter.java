package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ImageBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/24 17:33
 * @Desc: 质量检测的img
 */
public class QualityTestAdapter extends BaseAdapter {

    private Context mContext;
    private List<ImageBean> mList;

    public QualityTestAdapter(Context mContext, List<ImageBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ImageBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_imgage, parent, false);
            holder = new ViewHolder();

            holder.iv_img = convertView.findViewById(R.id.iv_img);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.d("sxss------", "position:" + getItem(position).getPath());
        Glide.with(parent.getContext()).load(getItem(position))
                .placeholder(R.drawable.icon_hint_network)//加载未完成时显示占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.iv_img);//显示的位置

        return convertView;
    }

    private class ViewHolder{
        private ImageView iv_img;
    }
}
