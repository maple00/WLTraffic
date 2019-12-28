package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SpecialAccountBean;
import com.rainowood.wltraffic.ui.adapter.SpecialAccountAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:19
 * @Desc: 专户制
 */
public class SpecialAccountNoActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_account;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_status)
    private TextView status;

    @ViewById(R.id.tv_name)
    private TextView name;
    @ViewById(R.id.lv_list)
    private MeasureListView list;

    @Override
    protected void initView() {
        ivBack.setOnClickListener(this);
        pageTitle.setText("专户制度");
        status.setText("未签订三方协议");
        name.setText("转入专户资金情况");

        SpecialAccountAdapter accountAdapter = new SpecialAccountAdapter(this, mList);
        list.setAdapter(accountAdapter);
    }

    /*
    模拟数据
     */
    private List<SpecialAccountBean> mList;

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SpecialAccountBean specialAccount = new SpecialAccountBean();
            specialAccount.setMoney("￥ 560.00");
            specialAccount.setTime("2019.12.18 17:19:00");
            specialAccount.setNote("这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注");
            mList.add(specialAccount);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
