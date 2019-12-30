package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 14:09
 * @Desc: 双向滑动中的横向滑动
 */
public class SlideHorizontalAdapter extends RecyclerView.Adapter<SlideHorizontalAdapter.SlideHorizontalViewHolder> {

    private Context mContext;
    private List<String> mList;

    public SlideHorizontalAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public SlideHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_slide_horizontal_include_item, parent, false);
        return new SlideHorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideHorizontalViewHolder holder, int position) {
        holder.tvText.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SlideHorizontalViewHolder extends RecyclerView.ViewHolder {

        TextView tvText;

        private SlideHorizontalViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_slide_horizontal_text);
        }
    }
}
