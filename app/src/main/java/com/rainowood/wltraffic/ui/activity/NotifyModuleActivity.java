package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.NotifyBean;
import com.rainowood.wltraffic.ui.adapter.NotifyAdapter;
import com.rainowood.wltraffic.ui.adapter.QualitySafeAdapter;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 9:50
 * @Desc: 通报
 */
public final class NotifyModuleActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_notify;
    }

    @ViewById(R.id.iv_back)
    private ImageView back;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.iv_background)
    private ImageView background;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.tv_status)
    private TextView status;

    @ViewById(R.id.lv_notify_content)
    private MeasureListView notifyContent;


    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(this, title, background);
        StatusBarUtil.setStatusBarFontIconDark(this, getResources().getColor(R.color.white), false);
        back.setOnClickListener(this);
        pageTitle.setText("通报");

        NotifyBean notify = (NotifyBean) getIntent().getSerializableExtra("notify");

        postDelayed(() -> {
            status.setText(notify.getTop().getState() + "维权公示牌");
            NotifyAdapter safeAdapter = new NotifyAdapter(NotifyModuleActivity.this, notify.getList());
            notifyContent.setAdapter(safeAdapter);
            safeAdapter.setContentOnClick(position -> {
                Intent intent = new Intent(NotifyModuleActivity.this, NotifyDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("content", notify.getList().get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            });
        }, 500);

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            openActivity(FarmersSalaryManagerActivity.class);
            finish();
        }
    }
}
