package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubPayManagerBean;
import com.rainwood.tools.widget.MeasureListView;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/25 18:12
 * @Desc: 支付管理卡片adapter
 */
public class PayManagerCardAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubPayManagerBean> mList;

    public PayManagerCardAdapter(Context mContext, List<SubPayManagerBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubPayManagerBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_manager_card, parent, false);
            holder = new ViewHolder();

            holder.ll_title = convertView.findViewById(R.id.ll_title);
            holder.tv_label = convertView.findViewById(R.id.tv_label);
            holder.tv_total_money = convertView.findViewById(R.id.tv_total_money);
            holder.lv_content = convertView.findViewById(R.id.lv_content);
            holder.ll_show_or_hide = convertView.findViewById(R.id.ll_show_or_hide);
            holder.tv_show_or_hide = convertView.findViewById(R.id.tv_show_or_hide);
            holder.iv_show_or_hide = convertView.findViewById(R.id.iv_show_or_hide);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_label.setText(getItem(position).getLabel());
        holder.tv_total_money.setText("￥ " + getItem(position).getlMoney());

        // 如果子项大于2项，默认隐藏
        PayManagerContentAdapter contentAdapter = new PayManagerContentAdapter(this,mContext, getItem(position).getmList());
        holder.lv_content.setAdapter(contentAdapter);
        contentAdapter.setLabelListener(listener);
        contentAdapter.setShowAll(!getItem(position).isHasHide());
        holder.lv_content.postInvalidate();
        holder.tv_show_or_hide.setText(getItem(position).isHasHide() ? "展开" : "收起");
        holder.tv_show_or_hide.setBackgroundResource(getItem(position).isHasHide() ? R.drawable.ic_icon_after : R.drawable.ic_icon_front);
        holder.iv_show_or_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem(position).setHasHide(!getItem(position).isHasHide());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private PayManagerContentAdapter.LabelListener listener;

    public void setListener(PayManagerContentAdapter.LabelListener listener) {
        this.listener = listener;
    }

    private class ViewHolder {
        private LinearLayout ll_title, ll_show_or_hide;
        private TextView tv_label, tv_total_money, tv_show_or_hide;
        private MeasureListView lv_content;
        private ImageView iv_show_or_hide;
    }
}
