package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.AssessBean;
import com.rainowood.wltraffic.ui.activity.ImageActivity;
import com.rainowood.wltraffic.ui.activity.TbsActivity;
import com.rainwood.tools.toast.ToastUtils;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/24 17:33
 * @Desc: 展示所有图片adapter
 */
public class AssessAttachmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<AssessBean> mList;

    public AssessAttachmentAdapter(Context mContext, List<AssessBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public AssessBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_assess_manager, parent, false);
            holder = new ViewHolder();

            holder.tv_word_title = convertView.findViewById(R.id.tv_word_title);
            holder.tv_update_time = convertView.findViewById(R.id.tv_update_time);
            holder.ll_download = convertView.findViewById(R.id.ll_download);
            holder.ll_preview = convertView.findViewById(R.id.ll_preview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_word_title.setText(getItem(position).getName());
        holder.tv_update_time.setText(getItem(position).getUpdateTime() + " 更新");

        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mList.get(position).getType())) {
                    ToastUtils.show("文件地址错误！请确认");
                    return;
                }
                ToastUtils.show("下载成功！请预览");
                // Toast.makeText(mContext, "下载", Toast.LENGTH_SHORT).show();
            }
        });

        holder.ll_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mList.get(position).getType())){
                    ToastUtils.show("文件地址错误！请确认");
                    return;
                }
                // 根据不同的文件类型进行预览
                if ("png".equals(mList.get(position).getType())
                        || "jpg".equals(mList.get(position).getType())){        // 加载大图
                    ImageActivity.start(mContext, Contants.BASE_URI +  mList.get(position).getSrc());
                }else {     // 加载文档--- 需要打开权限
                    Intent intent = new Intent(mContext, TbsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("path", Contants.BASE_URI + mList.get(position).getSrc());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
                //Toast.makeText(mContext, "预览", Toast.LENGTH_SHORT).show();
            }
        });

        notifyDataSetChanged();
        return convertView;
    }


    private class ViewHolder {
        private TextView tv_word_title, tv_update_time;
        private LinearLayout ll_download, ll_preview;
    }
}
