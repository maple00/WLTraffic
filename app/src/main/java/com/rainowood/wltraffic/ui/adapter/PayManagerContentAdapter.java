package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubItemLabelBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/25 18:19
 * @Desc: 支付管理内容 adapter
 */
public class PayManagerContentAdapter extends RecyclerView.Adapter<PayManagerContentAdapter.PayManagerContentViewHolder> {

    private Context mContext;
    private List<SubItemLabelBean> mList;

    PayManagerContentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(List<SubItemLabelBean> mList) {
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public PayManagerContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pay_manager_card_content, parent, false);
        return new PayManagerContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayManagerContentViewHolder holder, final int position) {
        holder.tv_money.setText(mList.get(position).getTitle());
        holder.tv_time.setText(mList.get(position).getContent());

        // 设置子项的点击
        holder.ll_item_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnItemClick(position);
            }
        });
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class PayManagerContentViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_item_content;       // 点击事件
        TextView tv_money, tv_time;

        PayManagerContentViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_item_content = itemView.findViewById(R.id.ll_item_content);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
