package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: a797s
 * @Date: 2019/12/27 15:52
 * @Desc: 异常显示页面
 */
public final class StatusActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_status;
    }

    @ViewById(R.id.tv_title)
    private TextView pageTitle;


    @Override
    protected void initView() {
        // 页面返回
        findViewById(R.id.btn_back).setOnClickListener(this);
        String key = getIntent().getStringExtra("key");
        if ("marginstatus".equals(key)){
            pageTitle.setText("农民工工资保证金");
        }

        if ("specialaccount".equals(key)){
            pageTitle.setText("专户制");
        }

        if ("notify".equals(key)){
            pageTitle.setText("通报");
        }

    }

    @Override
    protected void initData() {

        // 空数据
        showEmpty();
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
