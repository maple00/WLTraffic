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
 * @Date: 2019/12/30 14:13
 * @Desc: 双向滑动中的纵向滑动
 */
public class SlideVerticalAdapter extends RecyclerView.Adapter<SlideVerticalAdapter.SlideVerticalViewHolder> {

    private Context mContext;
    private List<String> mList;

    public SlideVerticalAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public SlideVerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_slide_vertical_include_item, parent, false);
        return new SlideVerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideVerticalViewHolder holder, int position) {
        holder.tvText.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class SlideVerticalViewHolder extends RecyclerView.ViewHolder {

        TextView tvText;

        public SlideVerticalViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_slide_vertical_text);
        }
    }
}
