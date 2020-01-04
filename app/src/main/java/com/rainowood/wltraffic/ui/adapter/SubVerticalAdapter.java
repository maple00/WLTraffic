package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ProjectProgressBean;
import com.rainowood.wltraffic.domain.SubProjectProgressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 15:57
 * @Desc: 纵向布局
 */
public class SubVerticalAdapter extends RecyclerView.Adapter<SubVerticalAdapter.VerticalViewHolder> {

    private Context mContext;
    private List<SubProjectProgressBean> mList = new ArrayList<>();

    public SubVerticalAdapter(Context context) {
        mContext = context;
    }

    public void setVerticalDataList(List<SubProjectProgressBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sub_project_progress, parent, false);
        return new VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, int position) {
        holder.tv_title.setText(mList.get(position).getName());
        holder.tv_time.setText(mList.get(position).getTime());

        // 是否完成
        if (mList.get(position).isImgState()){        // 如果完成了，则标记
            holder.iv_finished.setVisibility(View.VISIBLE);
        }else {
            // 完成标志隐藏
            holder.iv_finished.setVisibility(View.GONE);
            // 设置字体
            holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.colorWord));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_time;
        ImageView iv_finished;
        public VerticalViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_finished = itemView.findViewById(R.id.iv_finished);
        }
    }
}
