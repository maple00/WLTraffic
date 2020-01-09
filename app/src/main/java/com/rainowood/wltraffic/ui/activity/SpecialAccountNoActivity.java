package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SpecialAccountBean;
import com.rainowood.wltraffic.ui.adapter.SpecialAccountAdapter;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:19
 * @Desc: 专户制
 */
public final class SpecialAccountNoActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_account;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.iv_background)
    private ImageView background;
    @ViewById(R.id.tv_no_status)
    private TextView status;

    @ViewById(R.id.tv_name)
    private TextView name;
    @ViewById(R.id.lv_list)
    private MeasureListView list;

    @Override
    protected void initView() {
        // 图片状态栏沉浸
        ImmersionUtil.ImageImmers(this, title, background);

        ivBack.setOnClickListener(this);
        pageTitle.setText("专户制度");
        status.setText("未签订三方协议");
        name.setText("转入专户资金情况");

        SpecialAccountAdapter accountAdapter = new SpecialAccountAdapter(this, specialAccount.getList());
        list.setAdapter(accountAdapter);
    }

    private SpecialAccountBean specialAccount;

    @Override
    protected void initData() {
        super.initData();

        // getIntent
        specialAccount = (SpecialAccountBean) getIntent().getSerializableExtra("specialAccount");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            openActivity(FarmersSalaryManagerActivity.class);
            finish();
        }
    }
}
