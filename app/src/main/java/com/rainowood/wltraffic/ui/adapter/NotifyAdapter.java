package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.NotifyContentBean;
import com.rainowood.wltraffic.domain.QualityBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/24 13:53
 * @Desc: 质量安全及整改情况
 */
public class NotifyAdapter extends BaseAdapter {

    private static final String TAG = "NotifyAdapter";
    private Context mContext;
    private List<NotifyContentBean> mList;

    public NotifyAdapter(Context mContext, List<NotifyContentBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public NotifyContentBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_quality_safe, parent, false);
            holder = new ViewHolder();

            holder.ll_content = convertView.findViewById(R.id.ll_content);
            holder.tv_state = convertView.findViewById(R.id.tv_state);
            holder.tv_content = convertView.findViewById(R.id.tv_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //
        String state = mList.get(position).getChangeState();
        if("待处理".equals(state)){
            holder.tv_state.setBackgroundResource(R.color.colorCancel);
        }else if ("未整改".equals(state)){
            holder.tv_state.setBackgroundResource(R.color.colorPercentage);
        }else if ("整改中".equals(state)){
            holder.tv_state.setBackgroundResource(R.color.colorBlue1);
        }else {
            holder.tv_state.setBackgroundResource(R.color.colorSuccess);
        }
        // 状态
        holder.tv_state.setText(mList.get(position).getChangeState());
        // 主要内容
        holder.tv_content.setText(mList.get(position).getMatter());

        holder.ll_content.setOnClickListener(v -> contentOnClick.contentClick(position));
        return convertView;
    }

    public interface IContentOnClick {
        void contentClick(int position);
    }

    private IContentOnClick contentOnClick;

    public void setContentOnClick(IContentOnClick contentOnClick) {
        this.contentOnClick = contentOnClick;
    }

    private class ViewHolder {
        private LinearLayout ll_content;
        private TextView tv_content, tv_state;
    }
}
