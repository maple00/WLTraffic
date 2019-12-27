package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.ui.adapter.ChangeManagerAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 9:46
 * @Desc: 变更管理
 */
public class ChangeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_count_change_money)
    private TextView countChangeMoney;
    @ViewById(R.id.tv_count_money)
    private TextView countMoney;
    @ViewById(R.id.lv_change_list)
    private MeasureListView changeList;


    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("变更管理");

        countChangeMoney.setText(changeData[0]);
        countMoney.setText(changeData[1]);

        ChangeManagerAdapter managerAdapter = new ChangeManagerAdapter(this, mList);
        changeList.setAdapter(managerAdapter);

        managerAdapter.setClickListener(new ChangeManagerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                // toast("点击了：" + position);
                openActivity(ChangeManagerDetailActivity.class);
            }
        });
    }

    /*
    模拟数据
     */
    private String[] changeData = {"15000", "60"};
    private List<SubItemLabelBean> mList;
    private String[] mTitles = {"在实际施工过程中，随着施工设计的深化，优于低下部分地址情况较差引起的桩基工程、的工...", "在实际施工过程中，随着施工设计的深化，优于低下部分地址情况较差引起的桩基工程、的工...", "在实际施工过程中，随着施工设计的深化，优于低下部分地址情况较差引起的桩基工程、的工..."};
    private String[] mLabels = {"50万元", "50万元", "50万元"};


    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            SubItemLabelBean label = new SubItemLabelBean();
            label.setTitle(mTitles[i]);
            label.setContent(mLabels[i]);

            mList.add(label);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
