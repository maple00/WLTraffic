package com.rainowood.wltraffic.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.domain.ProjectInfoBean;
import com.rainowood.wltraffic.ui.activity.MessageDetailActivity;
import com.rainowood.wltraffic.ui.adapter.HomeBeforeItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/21 17:43
 * @Desc: 消息
 */
public class MessageFrgment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int initLayout() {
        return R.layout.fragment_message;
    }

    // 模拟数据 ---代办事项和通知公告
    String[] mTitles = {"随着公司各类园林绿化景观工程项目的逐步增加随着公司各类园林绿化景观工程项目的逐步增加...",
            "随着公司各类园林绿化景观工程项目的逐步增加", "随着公司各类园林绿化景观工程项目的逐步增加",
            "随着公司各类园林绿化景观工程项目的逐步增加随着公司各类园林绿化景观工程项目的逐步增加..."};
    String[] mLabels = {"2019.12.18 15:30:25", "2019.12.18 15:30:25", "2019.12.18 15:30:25", "2019.12.18 15:30:25"};

    // 消息数据
    private List<ProjectInfoBean> mList;
    // 消息列表
    private ListView mListView;
    private View titleOne;
    private View titleTwo;
    private TextView tvHomeTitleOne;
    private TextView tvHomeTitleTwo;

    @Override
    protected void initView(View view) {
        // 初始化项目
        LinearLayoutCompat homeTitleOne = view.findViewById(R.id.ll_home_title_one);
        homeTitleOne.setOnClickListener(this);
        titleOne = view.findViewById(R.id.v_title_one);
        tvHomeTitleOne = view.findViewById(R.id.tv_home_title_one);
        LinearLayoutCompat homeTitleTwo = view.findViewById(R.id.ll_home_title_two);
        homeTitleTwo.setOnClickListener(this);
        titleTwo = view.findViewById(R.id.v_title_two);
        tvHomeTitleTwo = view.findViewById(R.id.tv_home_title_two);
        // 默认样式
        titleTwo.setVisibility(View.INVISIBLE);
        tvHomeTitleOne.setTextSize(20);
        tvHomeTitleOne.setText("待办事项");
        tvHomeTitleTwo.setText("通知公告");


        mListView = view.findViewById(R.id.lv_message);
        HomeBeforeItemAdapter adapter = new HomeBeforeItemAdapter(mContext, mList);
        mListView.setAdapter(adapter);
        adapter.setOnClick(new HomeBeforeItemAdapter.ItemOnClick() {
            @Override
            public void ItemOnClick(int position) {
                // toast("点击了：" + mList.get(position));
                // 展示详情
                startActivity(MessageDetailActivity.class);
            }
        });

    }

    @Override
    protected void initData(Context mContext) {
        // 初始化列表数据       -- 此数据需要从后台获取
        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ProjectInfoBean homeList = new ProjectInfoBean();
            homeList.setTitle(mTitles[i]);
            homeList.setLabel(mLabels[i]);
            mList.add(homeList);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_title_one:        // 在建项目
                // 设置样式
                titleOne.setVisibility(View.VISIBLE);
                titleTwo.setVisibility(View.INVISIBLE);
                tvHomeTitleOne.setTextSize(20);
                tvHomeTitleTwo.setTextSize(15);
                // 加载中
                showDialog();
                // 请求数据
//                initData();
                HomeBeforeItemAdapter adapter = new HomeBeforeItemAdapter(mContext, mList);
                mListView.setAdapter(adapter);
                adapter.setOnClick(new HomeBeforeItemAdapter.ItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        //toast("点击了：" + mList.get(position).getTitle());
                        // 展示详情
                        startActivity(MessageDetailActivity.class);
                    }
                });

                break;
            case R.id.ll_home_title_two:        // 前期项目
                // 设置样式
                titleOne.setVisibility(View.INVISIBLE);
                titleTwo.setVisibility(View.VISIBLE);
                tvHomeTitleOne.setTextSize(15);
                tvHomeTitleTwo.setTextSize(20);
                // 加载中
                showDialog();
//                // 请求数据
//                initBeforeData();
                HomeBeforeItemAdapter beforeItemAdapter = new HomeBeforeItemAdapter(mContext, mList);
                mListView.setAdapter(beforeItemAdapter);
                beforeItemAdapter.setOnClick(new HomeBeforeItemAdapter.ItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        //toast("点击了：" + mList.get(position).getLabel());
                        // 展示详情
                        startActivity(MessageDetailActivity.class);
                    }
                });

                break;
        }
    }


    private void showDialog() {

    }
}
