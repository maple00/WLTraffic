package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: a797s
 * @Date: 2019/12/27 13:49
 * @Desc: 农名工保证金缴状态
 */
public class FarmersNoMarginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_margin_no_status;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.ll_bg)
    private RelativeLayout background;

    @ViewById(R.id.tv_no_margin)
    private TextView noMarginTv;
    @ViewById(R.id.tv_status)
    private TextView status;


    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        //StatusBarUtils.with(this).init();
        ivBack.setOnClickListener(this);
        pageTitle.setText("农民工工资保证金");

        status.setText("未缴存");

        background.setBackgroundResource(R.drawable.img_background_no_3x);
        noMarginTv.setText("此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述此处是文字描述");


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
