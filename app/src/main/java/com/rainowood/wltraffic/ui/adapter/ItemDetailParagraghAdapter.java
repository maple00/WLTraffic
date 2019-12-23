package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.domain.ParagraphListBean;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 10:16
 * @Desc: 段落列表
 */
public class ItemDetailParagraghAdapter extends BaseAdapter {

    private Context mContext;
    private List<ParagraphListBean> mList;

    public ItemDetailParagraghAdapter(Context mContext, List<ParagraphListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public ParagraphListBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_paragragh_content, parent, false);
            holder = new ViewHolder();

            holder.tv_order = convertView.findViewById(R.id.tv_order);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            holder.tv_query_paragragh = convertView.findViewById(R.id.tv_query_paragragh);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 赋值
        holder.tv_order.setText(getItem(position).getId());
        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_content.setText(Html.fromHtml(getItem(position).getContent()));

        holder.tv_content.post(new Runnable() {
            @Override
            public void run() {
                // 段落文字的行数
                int lineCount = holder.tv_content.getLineCount();
                if (lineCount < 6) {
                    holder.tv_query_paragragh.setVisibility(View.GONE);
                    holder.tv_content.setEnabled(false);
                }else {    // 超出的可查看详情
                    holder.tv_content.setEnabled(true);
                    holder.tv_content.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            contentCilckListener.ContentOnClick(position);
                        }
                    });
                }
            }
        });

        return convertView;
    }

    public interface ContentCilckListener {
        void ContentOnClick(int position);
    }

    private ContentCilckListener contentCilckListener;

    public void setContentCilckListener(ContentCilckListener contentCilckListener) {
        this.contentCilckListener = contentCilckListener;
    }

    private class ViewHolder {
        private TextView tv_order, tv_title, tv_content, tv_query_paragragh;
    }

}
