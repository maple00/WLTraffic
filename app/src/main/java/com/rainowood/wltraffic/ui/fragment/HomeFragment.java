package com.rainowood.wltraffic.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.domain.HomeListBean;
import com.rainowood.wltraffic.ui.adapter.HomeListViewAdapter;
import com.rainowood.wltraffic.ui.adapter.HomeTitleGridViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/17 15:13
 * @Desc: 首页
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }


    // 首页列表
    private ListView mListView;
    // 首页数据.
    private List<HomeListBean> mList;

    // 模拟数据
    String[] mListTitles = {"白马至白果坪至天尺坪升级公路改造工程", "新安小学新建校区10kv供电工程", "国家地下水监测工程(水利部分)第二标段工程",
            "白马至白果坪至天尺坪升级公路改造工程发武隆仙女山", "新安小学新建校区10kv供电工程"};
    String[] mLabels = {"80%", "90%", "90%", "90%", "80%"};

    // 首页项目的类型
    String[] mTitles = {"在建项目", "前期项目"};

    @Override
    protected void initView(View view) {

        // 从接口获取数据之后再初始化列表，
        // 等待对话框
       /* final BaseDialog dialog = new WaitDialog.Builder(getActivity())
                // 文本消息
                .setMessage("加载中").show();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);*/

        // 初始化项目
        GridView mGridView = view.findViewById(R.id.gv_home_title);
        HomeTitleGridViewAdapter gridViewAdapter = new HomeTitleGridViewAdapter(this.getActivity(), Arrays.asList(mTitles));
        mGridView.setAdapter(gridViewAdapter);

        // 设置默认选中的--- 默认在建项目为选中状态


        gridViewAdapter.setmTitleOnClick(new HomeTitleGridViewAdapter.TitleOnClick() {
            @Override
            public void OnClick(int position) {
                toast("点击了：" + mTitles[position]);
            }
        });



        // 初始化列表数据       -- 此数据需要从后台获取
        mList = new ArrayList<>();
        for (int i = 0; i < mListTitles.length; i++) {
            HomeListBean homeList = new HomeListBean();
            homeList.setTitle(mListTitles[i]);
            homeList.setLabel(mLabels[i]);
            mList.add(homeList);
        }
        mListView = view.findViewById(R.id.lv_home);
        HomeListViewAdapter adapter = new HomeListViewAdapter(this.getActivity(), mList);
        mListView.setAdapter(adapter);
        adapter.setOnClick(new HomeListViewAdapter.ItemOnClick() {
            @Override
            public void ItemOnClick(int position) {
                toast("点击了：" + mList.get(position));
            }
        });
    }

    @Override
    protected void initData(Context mContext) {

    }
}
