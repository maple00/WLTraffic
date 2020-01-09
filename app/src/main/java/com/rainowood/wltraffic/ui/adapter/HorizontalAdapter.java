package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubBankInfoYear;
import com.rainowood.wltraffic.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 横向布局 的 Adapter
 * <p>
 * Created by Tnno Wu on 2018/03/05.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<SubBankInfoYear> mList = new ArrayList<>();
    public HorizontalAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(List<SubBankInfoYear> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.horizontal_recycle_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, final int position) {
        if (String.valueOf(DateTimeUtils.getNowYear()).equals(mList.get(position).getYears())) {
            holder.tvContent.setText("今年");
            // 默认选中当前年
            mList.get(position).setHasSelected(true);
        } else if (String.valueOf(DateTimeUtils.getNowYear() - 1).equals(mList.get(position).getYears())) {
            holder.tvContent.setText("去年");
        } else {
            holder.tvContent.setText(mList.get(position).getYears() + "年");
        }

        if (mList.get(position).isHasSelected()) {
            // 设置被选中的不可再被点击, 此时会有加载的效果，但是实际上没有加载
            holder.ll_item.setClickable(false);
            holder.v_line.setVisibility(View.VISIBLE);
        } else {
            holder.ll_item.setClickable(true);
            holder.v_line.setVisibility(View.GONE);
        }

        holder.ll_item.setOnClickListener(v -> listener.onItemClick(position));
    }

    public interface OnItenmClickListener {
        void onItemClick(int position);
    }

    private OnItenmClickListener listener;

    public void setListener(OnItenmClickListener listener) {
        this.listener = listener;
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        View v_line;
        TextView tvContent;
        LinearLayout ll_item;

        HorizontalViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            ll_item = itemView.findViewById(R.id.ll_item);
            v_line = itemView.findViewById(R.id.v_line);
        }
    }
}
