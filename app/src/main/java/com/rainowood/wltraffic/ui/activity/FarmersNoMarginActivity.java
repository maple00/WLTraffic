package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.FarmersMarginBean;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/27 13:49
 * @Desc: 农名工保证金缴状态
 */
public final class FarmersNoMarginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_margin_no_status;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.iv_background)
    private ImageView background;

    @ViewById(R.id.tv_no_margin)
    private TextView noMarginTv;
    @ViewById(R.id.tv_no_status)
    private TextView status;


    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片状态栏沉浸
        ImmersionUtil.ImageImmers(this, title, background);
        // 状态栏字体白色
        StatusBarUtil.setStatusBarFontIconDark(this, getResources().getColor(R.color.white), false);
        ivBack.setOnClickListener(this);
        pageTitle.setText("农民工工资保证金");

        // getIntent
        FarmersMarginBean value = (FarmersMarginBean) getIntent().getSerializableExtra("value");
        if (value != null){
            status.setText(value.getContent().getState());
            background.setBackgroundResource(R.drawable.img_background_no_3x);
            noMarginTv.setText(value.getContent().getText());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                openActivity(FarmersSalaryManagerActivity.class);
                finish();
                break;
        }
    }
}
