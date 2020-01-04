package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ProjectProcedureBean;
import com.rainwood.tools.widget.MeasureListView;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 18:01
 * @Desc: 项目建设程序
 */
public class ProjectProcedureAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProjectProcedureBean> mList;

    public ProjectProcedureAdapter(Context mContext, List<ProjectProcedureBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ProjectProcedureBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_project_procedure, parent, false);
            holder = new ViewHolder();

            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.ll_is_standard = convertView.findViewById(R.id.ll_is_standard);
            holder.tv_success_or_fail = convertView.findViewById(R.id.tv_success_or_fail);
            holder.iv_success_or_fail = convertView.findViewById(R.id.iv_success_or_fail);
            holder.lv_attachment = convertView.findViewById(R.id.lv_attachment);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).getProcessName());
        holder.tv_time.setText(getItem(position).getTimeSeven());

        if (!TextUtils.isEmpty(getItem(position).getStateSeven())){        // 后两项
            holder.lv_attachment.setVisibility(View.GONE);
            holder.ll_is_standard.setVisibility(View.VISIBLE);
            // 如果是失败则设置红色显示，成功设置绿色显示
            if ("合格".equals(getItem(position).getStateSeven())){
                holder.tv_success_or_fail.setTextColor(mContext.getResources().getColor(R.color.colorSuccess));
                holder.iv_success_or_fail.setBackgroundResource(R.drawable.ic_icon_success);
            }else {
                holder.tv_success_or_fail.setTextColor(mContext.getResources().getColor(R.color.colorFail));
                holder.iv_success_or_fail.setBackgroundResource(R.drawable.ic_icon_no);
            }
            holder.tv_success_or_fail.setText(getItem(position).getStateSeven());
        }else {         // 前几项
            holder.lv_attachment.setVisibility(View.VISIBLE);
            holder.ll_is_standard.setVisibility(View.GONE);

            // 附件
            ItemAttachListAdapter attachListAdapter = new ItemAttachListAdapter(mContext, mList.get(position).getFileSevenFile());
            holder.lv_attachment.setAdapter(attachListAdapter);
            attachListAdapter.notifyDataSetChanged();
        }
        notifyDataSetChanged();

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_title, tv_time, tv_success_or_fail;
        private ImageView iv_success_or_fail;
        private LinearLayoutCompat ll_is_standard;
        private MeasureListView lv_attachment;      // 附件
    }
}
