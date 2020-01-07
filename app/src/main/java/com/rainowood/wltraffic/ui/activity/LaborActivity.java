package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.LaborBean;
import com.rainowood.wltraffic.ui.adapter.LaborAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:27
 * @Desc: 劳资管理
 */
public final class LaborActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_labor;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_labor_list)
    private MeasureListView laborList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("劳资人员管理");

        //
        LaborAdapter laborAdapter = new LaborAdapter(this, mList);
        laborList.setAdapter(laborAdapter);

    }

    /*
    模拟数据
     */
    private List<LaborBean> mList;

    @Override
    protected void initData() {
        super.initData();

        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LaborBean labor = new LaborBean();
            labor.setName("李佩奇");
            labor.setCompany("重庆大城建德建筑有限公司·项目经理");
            labor.setTel("13512270415");
            labor.setNote("这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容这里是备注内容");
            labor.setTime("2019.12.19 09:29:00更新");
            labor.setHasHide(true);

            mList.add(labor);
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
