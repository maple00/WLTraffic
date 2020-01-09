package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubBankInfoYearInfo;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 纵向布局 的 Adapter
 * <p>
 * Created by Tnno Wu on 2018/03/05.
 */

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder> {

    private static final String TAG = VerticalAdapter.class.getSimpleName();
    private Context mContext;
    private List<SubBankInfoYearInfo> mList = new ArrayList<>();

    public VerticalAdapter(Context context) {
        mContext = context;
    }

    public void setVerticalDataList(List<SubBankInfoYearInfo> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bank_data, parent, false);
        return new VerticalViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, int position) {
        holder.tv_money.setText("￥ " + mList.get(position).getMoney());
        holder.tv_month.setText(mList.get(position).getMonth() + "月");

        // 文件附件列表
        ItemAttachListAdapter attachListAdapter = new ItemAttachListAdapter(mContext, mList.get(position).getFile());
        holder.lv_word_list.setAdapter(attachListAdapter);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VerticalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_money, tv_month;
        MeasureListView lv_word_list;
        VerticalViewHolder(View itemView) {
            super(itemView);
            tv_month = itemView.findViewById(R.id.tv_month);
            tv_money = itemView.findViewById(R.id.tv_money);
            lv_word_list = itemView.findViewById(R.id.lv_word_list);
        }
    }
}
