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
import android.widget.Toast;

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

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pay_manager_card, parent, false);
        return new VerticalViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, final int position) {

        holder.tv_label.setText(mList.get(position).getLabel());
        holder.tv_total_money.setText("￥ " + mList.get(position).getlMoney());

        // 加载子项的数据，如果大于两条则显示两条，通过点击展开或收起控制
        PayManagerContentAdapter contentAdapter = new PayManagerContentAdapter(mContext);
        LinearLayoutManager managerVertical = new LinearLayoutManager(mContext);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置item之间的间距
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 30);//下间距
        holder.rlv_content.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));

        holder.rlv_content.setLayoutManager(managerVertical);
        holder.rlv_content.setHasFixedSize(true);
        holder.rlv_content.setAdapter(contentAdapter);
        if (mList.get(position).getmList().size() > 2){
            contentAdapter.setmList(mList.get(position).getmList().subList(0, 2));
        }else {     // 如果数据不大于两条，这隐藏收起或展开
            contentAdapter.setmList(mList.get(position).getmList());
            holder.ll_show_or_hide.setVisibility(View.GONE);
        }

        contentAdapter.setClickListener(clickListener);     // 详情点击事件

        /*
        收起或展开逻辑, 默认为收起
         */
        holder.ll_show_or_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sxss----", "点击了" + mList.get(position).isHasHide());

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
