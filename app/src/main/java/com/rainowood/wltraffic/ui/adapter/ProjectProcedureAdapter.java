package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
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
            holder.tv_word_title = convertView.findViewById(R.id.tv_word_title);
            holder.ll_document = convertView.findViewById(R.id.ll_document);
            holder.ll_is_standard = convertView.findViewById(R.id.ll_is_standard);
            holder.tv_success_or_fail = convertView.findViewById(R.id.tv_success_or_fail);
            holder.iv_success_or_fail = convertView.findViewById(R.id.iv_success_or_fail);
            holder.ll_download = convertView.findViewById(R.id.ll_download);
            holder.ll_preview = convertView.findViewById(R.id.ll_preview);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_time.setText(getItem(position).getTime());

        if (getItem(position).getDocName() == null){        // 后两项
            holder.ll_document.setVisibility(View.GONE);
            holder.ll_is_standard.setVisibility(View.VISIBLE);

            holder.tv_success_or_fail.setText(getItem(position).getStandard());
            // 如果是失败则设置红色显示，成功设置绿色先死
            if ("合格".equals(getItem(position).getStandard())){
                holder.tv_success_or_fail.setTextColor(mContext.getResources().getColor(R.color.colorSuccess));
                holder.iv_success_or_fail.setBackgroundResource(R.drawable.ic_icon_success);
            }else {
                holder.tv_success_or_fail.setTextColor(mContext.getResources().getColor(R.color.colorFail));
                holder.iv_success_or_fail.setBackgroundResource(R.drawable.ic_icon_delete);
            }
        }else {         // 前三项
            holder.ll_document.setVisibility(View.VISIBLE);
            holder.ll_is_standard.setVisibility(View.GONE);

            holder.tv_word_title.setText(getItem(position).getDocName());
            // 下载
            holder.ll_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "下载", Toast.LENGTH_SHORT).show();
                }
            });
            // 预览
            holder.ll_preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "预览", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }

    private class ViewHolder{
        private TextView tv_title, tv_time, tv_word_title, tv_success_or_fail;
        private ImageView iv_success_or_fail;
        private LinearLayoutCompat ll_download, ll_preview, ll_document, ll_is_standard;
    }
}
