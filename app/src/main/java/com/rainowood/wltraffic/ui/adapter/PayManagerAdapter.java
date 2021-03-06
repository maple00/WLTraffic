package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubPayManagerBean;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 16:40
 * @Desc: 支付管理，纵向布局
 */
public class PayManagerAdapter extends RecyclerView.Adapter<PayManagerAdapter.VerticalViewHolder> {

    private Context mContext;
    private List<SubPayManagerBean> mList;

    public PayManagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(List<SubPayManagerBean> mList) {
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
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pay_manager_card, parent, false);
        return new VerticalViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final VerticalViewHolder holder, final int position) {
        holder.tv_label.setText(mList.get(position).getTeamName());
        holder.tv_total_money.setText("￥ " + mList.get(position).getTeamChildMoney());
        // 加载子项的数据，如果大于两条则显示两条，通过点击展开或收起控制
        final PayManagerContentAdapter contentAdapter = new PayManagerContentAdapter(mContext);
        LinearLayoutManager managerVertical = new LinearLayoutManager(mContext);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置item之间的间距
//        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
//        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 20);//下间距
//        holder.rlv_content.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        holder.rlv_content.setLayoutManager(managerVertical);
        holder.rlv_content.setHasFixedSize(true);       // 重新计算高度
        holder.rlv_content.setAdapter(contentAdapter);
        holder.rlv_content.setNestedScrollingEnabled(false);
        if (mList.get(position).getTeamChildArr().size() > 2) {
            contentAdapter.setNum(2);               // 超过两条，默认显示两条
            contentAdapter.setmList(mList.get(position).getTeamChildArr());
        } else {     // 如果数据不大于两条，这隐藏收起或展开
            contentAdapter.setmList(mList.get(position).getTeamChildArr());
            holder.ll_show_or_hide.setVisibility(View.GONE);
        }
        contentAdapter.setClickListener(position, clickListener);     // 详情点击事件
        /*
        收起或展开逻辑, 默认为收起
         */
        holder.ll_show_or_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 控制显示item数量
                if (mList.get(position).isHasHide()) {
                    contentAdapter.setNum(mList.get(position).getTeamChildArr().size());
                    holder.tv_show_or_hide.setText("收起");
                    holder.iv_show_or_hide.setImageResource(R.drawable.ic_icon_up);
                } else {
                    contentAdapter.setNum(2);
                    holder.tv_show_or_hide.setText("展开");
                    holder.iv_show_or_hide.setImageResource(R.drawable.ic_icon_down);
                }
                mList.get(position).setHasHide(!mList.get(position).isHasHide());
            }
        });
    }

    private PayManagerContentAdapter.OnItemClickListener clickListener;

    public void setClickListener(PayManagerContentAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class VerticalViewHolder extends RecyclerView.ViewHolder {
        TextView tv_label, tv_total_money, tv_show_or_hide;
        LinearLayout ll_show_or_hide;
        ImageView iv_show_or_hide;
        RecyclerView rlv_content;

        VerticalViewHolder(View itemView) {
            super(itemView);
            tv_label = itemView.findViewById(R.id.tv_label);
            tv_total_money = itemView.findViewById(R.id.tv_total_money);
            tv_show_or_hide = itemView.findViewById(R.id.tv_show_or_hide);
            ll_show_or_hide = itemView.findViewById(R.id.ll_show_or_hide);          // 点击事件
            iv_show_or_hide = itemView.findViewById(R.id.iv_show_or_hide);
            rlv_content = itemView.findViewById(R.id.rlv_content);
        }
    }
}
