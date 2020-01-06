package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.QualityBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/24 13:53
 * @Desc: 质量安全及整改情况
 */
public class QualitySafeAdapter extends BaseAdapter {

    private Context mContext;
    private List<QualityBean> mList;

    public QualitySafeAdapter(Context mContext, List<QualityBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public QualityBean getItem(int position) {
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
        /*String content = getItem(position).getCaseDescriptio();
        String beforeStr = content.substring(0, 3);         // 截取之前的字符
        String afterStr = content.substring(3);             // 截取之后的字符
        Spanned html = null;
        if ("未整改".equals(beforeStr)) {
            html = Html.fromHtml("<font color=\"" + mContext.getResources().getColor(R.color.colorPercentage) + "\" font-size=\"12sp\">"
                    + beforeStr + "\t" + "</font>" + afterStr);
        }

        if ("整改中".equals(beforeStr)) {
            html = Html.fromHtml("<font color=\"" + mContext.getResources().getColor(R.color.colorBlue1) + "\" font-size=\"12sp\">"
                    + beforeStr + "\t" + "</font>" + afterStr);
        }

        if ("已整改".equals(beforeStr)) {
            html = Html.fromHtml("<font color=\"" + mContext.getResources().getColor(R.color.colorSuccess) + "\" font-size=\"12sp\">"
                    + beforeStr + "\t" + "</font>" + afterStr);
        }

        if ("待处理".equals(beforeStr)) {
            html = Html.fromHtml("<font color=\"" + mContext.getResources().getColor(R.color.colorRed1) + "\" font-size=\"12sp\">"
                    + beforeStr + "\t" + "</font>" + afterStr);
        }
        holder.tv_content.setText(html);
        */

        String state = mList.get(position).getState();
        if("待处理".equals(state)){
            holder.tv_state.setBackgroundResource(R.color.colorCancel);
        }else if ("未整改".equals(state)){
            holder.tv_state.setBackgroundResource(R.color.colorPercentage);
        }else if ("整改中".equals(state)){
            holder.tv_state.setBackgroundResource(R.color.colorBlue1);
        }else {
            holder.tv_state.setBackgroundResource(R.color.colorSuccess);
        }
        holder.tv_state.setText(mList.get(position).getState());
        holder.tv_content.setText(mList.get(position).getCaseDescriptio());

        notifyDataSetChanged();
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentOnClick.contentClick(position);
            }
        });

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
