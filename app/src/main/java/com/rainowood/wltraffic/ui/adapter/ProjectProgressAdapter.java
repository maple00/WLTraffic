package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ProjectProgressBean;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 15:57
 * @Desc: 纵向布局
 */
public class ProjectProgressAdapter extends RecyclerView.Adapter<ProjectProgressAdapter.VerticalViewHolder> {

    private Context mContext;
    private List<ProjectProgressBean> mList = new ArrayList<>();

    public ProjectProgressAdapter(Context context) {
        mContext = context;
    }

    public void setVerticalDataList(List<ProjectProgressBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vertical_recycle_item, parent, false);
        return new VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VerticalViewHolder holder, final int position) {
        holder.tv_item_name.setText(mList.get(position).getTitle());

        // 判断里面是否有未完成的项目  iv_mark
        int flagCount = 0;      // mark 完成的
        for (int i = 0; i < ListUtils.getSize(mList.get(position).getmList()); i++) {
            boolean finished = mList.get(position).getmList().get(i).isFinished();
            if (!finished) {
                flagCount++;
            }
        }
        if (flagCount > 0) {
            holder.iv_mark.setVisibility(View.VISIBLE);
        } else {
            holder.iv_mark.setVisibility(View.GONE);
        }

        // 查询每个目录下的子目录
        SubProjectProgressAdapter subProgressAdapter = new SubProjectProgressAdapter(mContext, mList.get(position).getmList());
        holder.lv_content.setAdapter(subProgressAdapter);
        subProgressAdapter.notifyDataSetChanged();

        // 点击
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = mList.get(position).getHasSelected() % 2;
                if (value == 0) {               // 点击了，默认为false
                    // 更换图标
                    holder.iv_next_content.setBackgroundResource(R.drawable.ic_icon_after);
                    mList.get(position).setHasSelected(++value);
                    holder.lv_content.setVisibility(View.VISIBLE);

                } else {
                    holder.iv_next_content.setBackgroundResource(R.drawable.ic_icon_front);
                    mList.get(position).setHasSelected(++value);
                    holder.lv_content.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VerticalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_name;
        ImageView iv_next_content, iv_mark;
        MeasureListView lv_content;
        LinearLayout ll_content;

        private VerticalViewHolder(View itemView) {
            super(itemView);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            iv_next_content = itemView.findViewById(R.id.iv_next_content);
            iv_mark = itemView.findViewById(R.id.iv_mark);
            lv_content = itemView.findViewById(R.id.lv_content);
            ll_content = itemView.findViewById(R.id.ll_content);
        }
    }
}
