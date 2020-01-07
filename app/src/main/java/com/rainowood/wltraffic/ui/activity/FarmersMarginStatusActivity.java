package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.common.MeasureUtil;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;

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
    @ViewById(R.id.tv_word)
    private TextView wordName;
    @ViewById(R.id.ll_download)
    private LinearLayout download;
    @ViewById(R.id.ll_preview)
    private LinearLayout preview;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(this, title, backgrounds);

        ivBack.setOnClickListener(this);
        pageTitle.setText("农民工工资保证金");
        status.setText("已缴存(免缴)");
        wordName.setText("工资保证金具体细则.doc");
        download.setOnClickListener(this);
        preview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_download:
                toast("下载");
                break;
            case R.id.ll_preview:
                toast("预览");
                break;
        }
    }

}
