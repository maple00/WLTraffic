package com.rainowood.wltraffic.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 13:59
 * @Desc: 农民工工资双向滑动
 */
public class FarmerBankSlideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HORIZONTAL = 0;           // 横向滑动为年
    private static final int TYPE_VERTICAL = 1;             // 纵向滑动为数据

    private Context mContext;
    private List<Integer> mTypeList;

    private List<String> mHorizontalList = new ArrayList<>();
    private List<String> mVerticalList = new ArrayList<>();

    public FarmerBankSlideAdapter(Context mContext, List<Integer> mTypeList) {
        this.mContext = mContext;
        this.mTypeList = mTypeList;
    }

    public void setmHorizontalList(List<String> mHorizontalList) {
        this.mHorizontalList = mHorizontalList;
        notifyDataSetChanged();
    }

    public void setmVerticalList(List<String> mVerticalList) {
        this.mVerticalList = mVerticalList;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mTypeList.get(position) == 0) {         // 横向
            return TYPE_HORIZONTAL;
        } else if (mTypeList.get(position) == 1) {  // 纵向
            return TYPE_VERTICAL;
        } else {
            return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HORIZONTAL) {
            View viewHorizontal = LayoutInflater.from(mContext).inflate(R.layout.item_slide_horizontal_include, parent, false);
            return new HorizontalViewHolder(viewHorizontal);
        } else if (viewType == TYPE_VERTICAL) {
            View viewVertical = LayoutInflater.from(mContext).inflate(R.layout.item_slide_vertical_include, parent, false);
            return new VerticalViewHolder(viewVertical);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HorizontalViewHolder) {
            if (mHorizontalList != null) {
                SlideHorizontalAdapter horizontalAdapter = new SlideHorizontalAdapter(mContext, mHorizontalList);
                LinearLayoutManager manager = new LinearLayoutManager(mContext);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                ((HorizontalViewHolder) holder).rcvHorizontal.setLayoutManager(manager);
                ((HorizontalViewHolder) holder).rcvHorizontal.setHasFixedSize(true);
                ((HorizontalViewHolder) holder).rcvHorizontal.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
                ((HorizontalViewHolder) holder).rcvHorizontal.setAdapter(horizontalAdapter);

                horizontalAdapter.notifyDataSetChanged();
            }
        } else if (holder instanceof VerticalViewHolder) {
            if (mVerticalList != null) {
                SlideVerticalAdapter verticalAdapter = new SlideVerticalAdapter(mContext, mVerticalList);
                ((VerticalViewHolder) holder).rcvVertical.setLayoutManager(new LinearLayoutManager(mContext));
                ((VerticalViewHolder) holder).rcvVertical.setHasFixedSize(true);
                ((VerticalViewHolder) holder).rcvVertical.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
                ((VerticalViewHolder) holder).rcvVertical.setAdapter(verticalAdapter);
                // 设置Item之间的间距


                verticalAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTypeList == null ? 0 : mTypeList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rcvHorizontal;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            rcvHorizontal = itemView.findViewById(R.id.rlv_slide_horizontal);
        }
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rcvVertical;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            rcvVertical = itemView.findViewById(R.id.rlv_slide_vertical);
        }
    }
}
