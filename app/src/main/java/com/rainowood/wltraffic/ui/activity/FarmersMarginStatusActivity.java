package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.FarmersMarginBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2019/12/27 13:49
 * @Desc: 农名工保证金缴状态
 */
public final class FarmersMarginStatusActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_margin_status;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.iv_background)
    private ImageView backgrounds;
    // 已缴
    @ViewById(R.id.tv_status)
    private TextView status;
    @ViewById(R.id.lv_attach_list)
    private MeasureListView attachList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(this, title, backgrounds);
        // 状态栏字体白色
        StatusBarUtil.setStatusBarFontIconDark(this, getResources().getColor(R.color.white), false);
        ivBack.setOnClickListener(this);
        pageTitle.setText("农民工工资保证金");

        // getIntent
        FarmersMarginBean value = (FarmersMarginBean) getIntent().getSerializableExtra("value");
        if (value != null){
            status.setText(value.getContent().getState() + "(免缴)");
            ItemAttachListAdapter adapter = new ItemAttachListAdapter(this, value.getFile());
            attachList.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            openActivity(FarmersSalaryManagerActivity.class);
            finish();
        }
    }

}
