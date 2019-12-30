package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.ProjectProgressBean;
import com.rainowood.wltraffic.domain.SubProjectProgressBean;
import com.rainowood.wltraffic.ui.adapter.ProjectProgressAdapter;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:03
 * @Desc: 项目进度
 */
public class ProjectProgressActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_progress;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_title_content)
    private TextView titleContent;

    @ViewById(R.id.rlv_content)
    private RecyclerView rclContentList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("进度管理");

        titleContent.setText("丰裕粮油安置房建设工程泉城花都E区安置工程丰裕粮油安置房");

        ProjectProgressAdapter adapter = new ProjectProgressAdapter(this);
        LinearLayoutManager managerVertical = new LinearLayoutManager(this);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);       // 纵向

        // 设置Item之间的间距
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION, 30);//右间距
        rclContentList.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));

        rclContentList.setLayoutManager(managerVertical);
        rclContentList.setHasFixedSize(true);
        rclContentList.setAdapter(adapter);

        adapter.setVerticalDataList(mList);
    }

    /*
    模拟数据
     */
    private List<ProjectProgressBean> mList;
    private String[] mTitles = {"立项阶段", "工可阶段", "初步设计阶段", "实施方案阶段", "施工图设计阶段", "招投标阶段", "施工许可阶段", "施工阶段", "验收阶段"};
    // 子项title
    private String[] mSubTitles = {"立项"};
    private String[] mSubTitles1 = {"编制勘测定界报告", "土地规划调整", "用地预审", "选址意见书", "编制涉河方案", "编制防洪影响评价", "通航条件影响评价", "编制可行性研究报告"};
    private String[] mSubTitles2 = {"设计单位确定", "安全评价", "初步设计批文", "建设工程规划许可", "项目概算审核", "编制林地可行性研究报告", "编制水土保持方案", "编制地灾影响评估报告", "编制环境影响评价报告", "压覆矿产资源评估", "农用地转用"};
    private String[] mSubTitles3 = {"农用地转用", "用地手续", "编制林地可行性研究报告", "编制水土保持方案", "编制地灾影响评估报告", "编制地灾影响评估报告", "压覆矿产资源评估", "编制实施方案"};
    private String[] mSubTitles4 = {"施工图设计单位确定", "施工图批文", "财政评审", "土地使用证"};
    private String[] mSubTitles5 = {"招投标管理"};
    private String[] mSubTitles6 = {"建设程序管理"};
    private String[] mSubTitles7 = {"当前进度", "当月产值", "累计产值", "存在困难"};
    private String[] mSubTitles8 = {"存在困难", "竣工验收"};

    private String[] mSubTimes = {"2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17", "2019.12.17"};

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();

        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ProjectProgressBean progress = new ProjectProgressBean();
            progress.setTitle(mTitles[i]);
            // 默认都没有选中
            progress.setHasSelected(0);

            List<SubProjectProgressBean> mSubList = new ArrayList<>();
            if (i == 0) {   // 立项
                for (int j = 0; j < mSubTitles.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 1) {  //工可阶段
                for (int j = 0; j < mSubTitles1.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles1[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 2) {
                for (int j = 0; j < mSubTitles2.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles2[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    if (j > 3) {
                        subProgress.setFinished(true);
                    } else {
                        subProgress.setFinished(false);
                    }

                    mSubList.add(subProgress);
                }
            }

            if (i == 3) {
                for (int j = 0; j < mSubTitles3.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles3[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 4) {
                for (int j = 0; j < mSubTitles4.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles4[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 5) {
                for (int j = 0; j < mSubTitles5.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles5[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 6) {
                for (int j = 0; j < mSubTitles6.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles6[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 7) {
                for (int j = 0; j < mSubTitles7.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles7[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            if (i == 8) {
                for (int j = 0; j < mSubTitles8.length; j++) {
                    SubProjectProgressBean subProgress = new SubProjectProgressBean();
                    subProgress.setTitle(mSubTitles8[j]);
                    subProgress.setTime(mSubTimes[j]);
                    // 是否完成
                    subProgress.setFinished(true);

                    mSubList.add(subProgress);
                }
            }

            progress.setmList(mSubList);
            mList.add(progress);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
