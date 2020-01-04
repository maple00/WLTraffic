package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.ui.activity.ImageActivity;
import com.rainowood.wltraffic.ui.activity.TbsActivity;
import com.rainwood.tools.permission.OnPermission;
import com.rainwood.tools.permission.Permission;
import com.rainwood.tools.permission.XXPermissions;
import com.rainwood.tools.toast.ToastUtils;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 9:34
 * @Desc: 附件列表
 */
public class ItemAttachListAdapter extends BaseAdapter {

    private Context mContext;
    private List<AttachBean> mList;

    /**
     * @param mList 附件的名字、类型和地址 ----> title、type和address
     */
    public ItemAttachListAdapter(Context mContext, List<AttachBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public AttachBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_sub_content, parent, false);
            holder = new ViewHolder();

            holder.tv_word_title = convertView.findViewById(R.id.tv_word_title);
            holder.iv_pre_mark = convertView.findViewById(R.id.iv_pre_mark);
            // 点击事件
            holder.ll_download = convertView.findViewById(R.id.ll_download);
            holder.ll_preview = convertView.findViewById(R.id.ll_preview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        if ("png".equals(mList.get(position).getType())
                || "jpg".equals(mList.get(position).getType())){        // 显示图片标志
            holder.iv_pre_mark.setBackgroundResource(R.drawable.ic_icon_picture);
        }else {     // 显示文档标志
            holder.iv_pre_mark.setBackgroundResource(R.drawable.ic_icon_file);
        }
        holder.tv_word_title.setText(getItem(position).getName());

        // 点击事件
        holder.ll_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           // new 下载界面
                if (TextUtils.isEmpty(mList.get(position).getType())){
                    ToastUtils.show("文件地址错误！请确认");
                    return;
                }
                ToastUtils.show("下载成功！请预览");
                //Toast.makeText(mContext, "下载：" + getItem(position).getName(), Toast.LENGTH_SHORT).show();
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
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_word_title;
        private LinearLayoutCompat ll_download, ll_preview;
        private AppCompatImageView iv_pre_mark;
    }



}
