package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.domain.RealNameBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:08
 * @Desc: 实名制详情
 */
public final class RealNameDetailActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_real_name_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_atttach_word)
    private MeasureListView attachWord;
    @ViewById(R.id.tv_real_detail_des)
    private TextView detailDes;
    @ViewById(R.id.tv_update_time)
    private TextView updateTime;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("附件详情");

        RealNameBean value = (RealNameBean) getIntent().getSerializableExtra("value");
        if (value != null){
            detailDes.setText(value.getText());
            updateTime.setText(value.getUpdateTime());

            // 附件
            ItemAttachListAdapter wordListAdapter = new ItemAttachListAdapter(this, value.getFile());
            attachWord.setAdapter(wordListAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            openActivity(RealNameActivity.class);
            finish();
        }
    }
}
