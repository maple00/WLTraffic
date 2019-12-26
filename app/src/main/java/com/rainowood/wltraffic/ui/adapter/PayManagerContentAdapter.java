package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.SubItemLabelBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/25 18:19
 * @Desc: 支付管理内容的适配
 */
public class PayManagerContentAdapter extends BaseAdapter {

    private Context mContext;
    private List<SubItemLabelBean> mList;
    private List<SubItemLabelBean> mAllItemList;
    private List<SubItemLabelBean> mTowItemList;
    private PayManagerCardAdapter parentAdapter;

    private boolean isShowAll;

    public void setShowAll(boolean showAll) {
        isShowAll = showAll;
        if (isShowAll) {
            mList = mAllItemList;
        } else {
            mTowItemList = new ArrayList<>();
            if (getCount() > 2) {
                mTowItemList.addAll(mAllItemList.subList(0, 2));
            } else {
                mTowItemList.addAll(mAllItemList);
            }
            mList = mTowItemList;
        }
        parentAdapter.notifyDataSetChanged();
    }

    public PayManagerContentAdapter(PayManagerCardAdapter parentAdapter, Context mContext, List<SubItemLabelBean> mList) {
        this.parentAdapter = parentAdapter;
        this.mContext = mContext;
        this.mList = mAllItemList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public SubItemLabelBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pay_manager_card_content, parent, false);
            holder = new ViewHolder();
            holder.tv_money = convertView.findViewById(R.id.tv_money);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.ll_item_content = convertView.findViewById(R.id.ll_item_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_money.setText(getItem(position).getTitle());
        holder.tv_time.setText(getItem(position).getContent());
        holder.ll_item_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelListener.onLabelClick(position);
            }
        });
//        holder.ll_item_content.setVisibility(getItem(position).isVisibility() ? View.VISIBLE : View.GONE);
        return convertView;
    }

    public interface LabelListener {
        void onLabelClick(int position);
    }

    private LabelListener labelListener;

    public void setLabelListener(LabelListener labelListener) {
        this.labelListener = labelListener;
    }

    private class ViewHolder {
        private TextView tv_money, tv_time;
        private LinearLayout ll_item_content;
    }
}
