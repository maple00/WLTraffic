package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SpecialAccountBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.ui.adapter.SpecialAccountAdapter;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:19
 * @Desc: 专户制
 */
public final class SpecialAccountActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_no_account;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.iv_background)
    private ImageView background;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.tv_status)
    private TextView status;

    // 附件
    @ViewById(R.id.lv_attach)
    private MeasureListView attachList;
    @ViewById(R.id.tv_name)
    private TextView pageLabel;
    // 具体内容
    @ViewById(R.id.lv_list)
    private MeasureListView list;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(this, title, background);
        // 设置状态栏字体颜色
        StatusBarUtil.setStatusBarFontIconDark(this, getResources().getColor(R.color.white), false);

        ivBack.setOnClickListener(this);
        pageTitle.setText("专户制度");
        pageLabel.setText("转入专户资金情况");
        // 状态
        status.setText(specialAccount.getTop().getState() + "三方协议");
        // 附件列表
        ItemAttachListAdapter attachListAdapter = new ItemAttachListAdapter(this, specialAccount.getTop().getFile());
        attachList.setAdapter(attachListAdapter);
        // 内容列表
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
