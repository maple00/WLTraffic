package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.LaborBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:50
 * @Desc: 劳资管理
 */
public class LaborAdapter extends BaseAdapter {

    private Context mContext;
    private List<LaborBean> mList;

    public LaborAdapter(Context mContext, List<LaborBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public LaborBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_labor, parent, false);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_company = convertView.findViewById(R.id.tv_company);
            holder.tv_tel = convertView.findViewById(R.id.tv_tel);
            holder.tv_note = convertView.findViewById(R.id.tv_note);
            holder.tv_last_time = convertView.findViewById(R.id.tv_last_time);
            holder.ll_show_or_hide = convertView.findViewById(R.id.ll_show_or_hide);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(getItem(position).getName());
        holder.tv_company.setText(getItem(position).getCompany());
        holder.tv_tel.setText(getItem(position).getTel());
        holder.tv_note.setText(getItem(position).getNote());
        holder.tv_last_time.setText(getItem(position).getTime());

        // 默认收起
        if (getItem(position).isHasHide()) {     // 收起了
            holder.tv_note.setVisibility(View.GONE);
            holder.tv_last_time.setVisibility(View.GONE);

        } else {
            holder.tv_note.setVisibility(View.VISIBLE);
            holder.tv_last_time.setVisibility(View.VISIBLE);
        }

        holder.ll_show_or_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem(position).setHasHide(!getItem(position).isHasHide());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private TextView tv_name, tv_company, tv_tel, tv_note, tv_last_time;
        private LinearLayout ll_show_or_hide;

    }
}
