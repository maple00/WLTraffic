package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.RectificationBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2020/1/7 11:20
 * @Desc: 整改情况详情
 */
public final class RectificationDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rectify_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_rectify_state)
    private TextView rectifyState;
    @ViewById(R.id.tv_content)
    private TextView content;
    @ViewById(R.id.lv_attach)
    private MeasureListView attachList;         // 附件

    private DialogUtils dialog;
    private RectificationBean rectification;
    private String key;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("整改详情");

        if ("已整改".equals(key)) {
            rectifyState.setText(key);
            rectifyState.setTextColor(getResources().getColor(R.color.colorSuccess));
            content.setText(rectification.getModifyHave());
            ItemAttachListAdapter adapter = new ItemAttachListAdapter(this, rectification.getHaveFile());
            attachList.setAdapter(adapter);
        } else if ("整改中".equals(key)) {
            rectifyState.setText(key);
            rectifyState.setTextColor(getResources().getColor(R.color.colorAccent));
            content.setText(rectification.getModifyIn());
            ItemAttachListAdapter adapter = new ItemAttachListAdapter(this, rectification.getInFile());
            attachList.setAdapter(adapter);
        } else {
            rectifyState.setText(key);
            rectifyState.setTextColor(getResources().getColor(R.color.colorFail));
            content.setText(rectification.getModifyNot());
            ItemAttachListAdapter adapter = new ItemAttachListAdapter(this, rectification.getNotFile());
            attachList.setAdapter(adapter);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();
        //
        key = getIntent().getStringExtra("key");
        rectification = (RectificationBean) getIntent().getSerializableExtra("value");

        dismissDialog();
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        }, 500);
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
