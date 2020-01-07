package com.rainowood.wltraffic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.ProjectInfoBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.activity.ProjectDetailActivity;
import com.rainowood.wltraffic.ui.adapter.HomeBeforeItemAdapter;
import com.rainowood.wltraffic.ui.adapter.HomeListViewAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/17 15:13
 * @Desc: 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, OnHttpListener {

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    // 首页列表
    private ListView mListView;
    // 首页数据.
    private List<ProjectInfoBean> leftList;
    private List<ProjectInfoBean> rightList;
    private View titleOne;
    private View titleTwo;
    private TextView tvHomeTitleOne;
    private TextView tvHomeTitleTwo;

    private DialogUtils dialog;

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
        tvHomeTitleOne.setText("在建项目");
        tvHomeTitleTwo.setText("前期项目");

        // 默认显示在建项目
        mListView = view.findViewById(R.id.lv_home);
    }

    @Override
    protected void initData(Context mContext) {
        dialog = new DialogUtils(getActivity(), "加载中");
        // 获取接口数据
        postDelayed(new Runnable() {
            @Override
            public void run() {
                RequestPost.getHomeDate(HomeFragment.this);
            }
        }, 100);
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
                HomeListViewAdapter adapter = new HomeListViewAdapter(mContext, leftList);
                mListView.setAdapter(adapter);
                adapter.setOnClick(new HomeListViewAdapter.ItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                        Bundle bundle = new Bundle();
                        Contants.ITEM_ID = leftList.get(position).getId();
                        intent.putExtra("stage", leftList.get(position).getStage());
                        intent.putExtras(bundle);
                        startActivity(intent);
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
                // 请求数据
                HomeBeforeItemAdapter beforeItemAdapter = new HomeBeforeItemAdapter(mContext, rightList);
                mListView.setAdapter(beforeItemAdapter);
                beforeItemAdapter.setOnClick(new HomeBeforeItemAdapter.ItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        // 打开详情, 带参访问详情
                        Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                        Bundle bundle = new Bundle();
                        Contants.ITEM_ID = rightList.get(position).getId();
                        bundle.putString("stage", rightList.get(position).getStage());
                        startActivity(intent, bundle);
                    }
                });

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void showDialog() {
        dialog.showDialog();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        }, 100);
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> bodys = JsonParser.parseJSONObject(result.body());
        if ("1".equals(bodys.get("code"))) {
            Map<String, String> data = JsonParser.parseJSONObject(bodys.get("data"));
            // 在建项目
            leftList = JsonParser.parseJSONArray(ProjectInfoBean.class, data.get("left"));
            // 前期项目
            rightList = JsonParser.parseJSONArray(ProjectInfoBean.class, data.get("right"));

            Message msg = new Message();
            msg.what = 0x958;
            mHandler.sendMessage(msg);

            dialog.dismissDialog();
        } else {
            dialog.dismissDialog();
            toast(bodys.get("warn"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismissDialog();
        }
    }

    /**
     * 局部刷新UI
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0x958:
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            HomeListViewAdapter adapter = new HomeListViewAdapter(mContext, leftList);
                            mListView.setAdapter(adapter);
                            adapter.setOnClick(new HomeListViewAdapter.ItemOnClick() {
                                @Override
                                public void ItemOnClick(int position) {
                                    // 打开详情
                                    Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                                    Contants.ITEM_ID = leftList.get(position).getId();
                                    intent.putExtra("stage", leftList.get(position).getStage());
                                    startActivity(intent);
                                }
                            });
                        }
                    }, 50);
                    break;
            }
        }
    };
}
