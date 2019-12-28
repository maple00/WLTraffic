package com.rainowood.wltraffic.ui.activity;

import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: a797s
 * @Date: 2019/12/28 17:34
 * @Desc: 招投标详情页
 */
public class TenderDetailAActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_name)
    private TextView name;
    @ViewById(R.id.tv_type)
    private TextView type;
    @ViewById(R.id.tv_time)
    private TextView time;
    @ViewById(R.id.tv_content)
    private TextView content;

    @Override
    protected void initView() {

    }
}
