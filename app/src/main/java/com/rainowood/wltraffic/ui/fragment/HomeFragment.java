package com.rainowood.wltraffic.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.domain.ProjectInfoBean;
import com.rainowood.wltraffic.ui.activity.ProjectDetailActivity;
import com.rainowood.wltraffic.ui.adapter.HomeBeforeItemAdapter;
import com.rainowood.wltraffic.ui.adapter.HomeListViewAdapter;
import com.rainowood.wltraffic.ui.dialog.WaitDialog;
import com.rainwood.tools.dialog.BaseDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/17 15:13
 * @Desc: 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    // 模拟数据  -- 在建项目
    private String[] mListTitles = {"白马至白果坪至天尺坪升级公路改造工程", "新安小学新建校区10kv供电工程", "国家地下水监测工程(水利部分)第二标段工程",
            "白马至白果坪至天尺坪升级公路改造工程发武隆仙女山", "新安小学新建校区10kv供电工程"};
    private String[] mLabels = {"80%", "90%", "90%", "90%", "80%"};

    // 前期项目
    private String[] beforeItemTitles = {"白马至白果坪至天尺坪公路升级改造工程", "丰裕粮油安置房建设工程泉城花都E区安置工程丰裕粮油安置房建设工程泉城花都E区安置工程...",
            "丰裕粮油安置房建设工程泉城花都E区安置", "丰裕粮油安置房建设工程泉城花都E区安置"};
    private String[] beforeLabels = {"施工图设计阶段-财政评审", "实施方案阶段-用地手续", "初步设计阶段-项目概算审核", "初步设计阶段-项目概算审核"};


    // 首页列表
    private ListView mListView;
    // 首页数据.
    private List<ProjectInfoBean> mList;
    private View titleOne;
    private View titleTwo;
    private TextView tvHomeTitleOne;
    private TextView tvHomeTitleTwo;

    @Override
    protected void initView(View view) {
        // 从接口获取数据之后再初始化列表，


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

        // 初始化在建项目数据
        initData();
        mListView = view.findViewById(R.id.lv_home);
        HomeListViewAdapter adapter = new HomeListViewAdapter(mContext, mList);
        mListView.setAdapter(adapter);
        adapter.setOnClick(new HomeListViewAdapter.ItemOnClick() {
            @Override
            public void ItemOnClick(int position) {
                // toast("点击了：" + mList.get(position));
                // 打开详情
                startActivity(ProjectDetailActivity.class);
            }
        });
    }

    @Override
    protected void initData(Context mContext) {

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
                initData();
                HomeListViewAdapter adapter = new HomeListViewAdapter(mContext, mList);
                mListView.setAdapter(adapter);
                adapter.setOnClick(new HomeListViewAdapter.ItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        // toast("点击了：" + mList.get(position).getTitle());
                        // 打开详情
                        startActivity(ProjectDetailActivity.class);
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
                initBeforeData();
                HomeBeforeItemAdapter beforeItemAdapter = new HomeBeforeItemAdapter(mContext, mList);
                mListView.setAdapter(beforeItemAdapter);
                beforeItemAdapter.setOnClick(new HomeBeforeItemAdapter.ItemOnClick() {
                    @Override
                    public void ItemOnClick(int position) {
                        // toast("点击了：" + mList.get(position).getLabel());
                        // 打开详情
                        startActivity(ProjectDetailActivity.class);
                    }
                });

                break;
        }
    }


    private void showDialog() {
        // 等待对话框
        final BaseDialog dialog = new WaitDialog.Builder(getActivity())
                // 文本消息
                .setMessage("加载中").show();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }

    // 对接接口后全都写到@Overried中
    private void initData() {
        // 初始化列表数据       -- 此数据需要从后台获取
        mList = new ArrayList<>();
        for (int i = 0; i < mListTitles.length; i++) {
            ProjectInfoBean homeList = new ProjectInfoBean();
            homeList.setTitle(mListTitles[i]);
            homeList.setLabel(mLabels[i]);
            mList.add(homeList);
        }
    }

    private void initBeforeData() {
        mList = new ArrayList<>();
        for (int i = 0; i < beforeItemTitles.length; i++) {
            ProjectInfoBean homeList = new ProjectInfoBean();
            homeList.setTitle(beforeItemTitles[i]);
            homeList.setLabel(beforeLabels[i]);
            mList.add(homeList);
        }
    }
}
