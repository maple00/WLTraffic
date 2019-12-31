package com.rainowood.wltraffic.ui.adapter;

import android.annotation.SuppressLint;
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
import com.rainowood.wltraffic.domain.LabelBean;
import com.rainowood.wltraffic.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 横向布局 的 Adapter
 * <p>
 * Created by Tnno Wu on 2018/03/05.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    private static final String TAG = HorizontalAdapter.class.getSimpleName();

    private Context mContext;

    private List<LabelBean> mList = new ArrayList<>();

    public HorizontalAdapter(Context context) {
        mContext = context;
    }

    public void setHorizontalDataList(List<LabelBean> list) {
        Log.d(TAG, "setHorizontalDataList: " + list.size());
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.horizontal_recycle_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, final int position) {
        if (String.valueOf(DateTimeUtils.getNowYear()).equals(mList.get(position).getData())) {
            holder.tvContent.setText("今年");
        } else if (String.valueOf(DateTimeUtils.getNowYear() - 1).equals(mList.get(position).getData())) {
            holder.tvContent.setText("去年");
        } else {
            holder.tvContent.setText(mList.get(position).getData() + "年");
        }

        if (mList.get(position).isHasSelected()){
            holder.v_line.setVisibility(View.VISIBLE);
        }else {
            holder.v_line.setVisibility(View.GONE);
        }

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    public interface OnItenmClickListener {
        void onItemClick(int position);
    }

    private OnItenmClickListener listener;

    public void setListener(OnItenmClickListener listener) {
        this.listener = listener;
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        View v_line;
        TextView tvContent;
        LinearLayout ll_item;

        HorizontalViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            ll_item = itemView.findViewById(R.id.ll_item);
            v_line = itemView.findViewById(R.id.v_line);
        }
    }
}
