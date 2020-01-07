package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.AssessDeductionBean;
import com.rainwood.tools.viewinject.ViewById;

import java.io.Serializable;

/**
 * @Author: shearson
 * @Time: 2019/12/26 20:11
 * @Desc: 扣分项详情
 */
public final class DeductionDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deduction_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_name)
    private TextView name;
    @ViewById(R.id.tv_score)
    private TextView scoreTV;
    @ViewById(R.id.tv_title_content)
    private TextView titleContent;
    @ViewById(R.id.tv_label_content)
    private TextView labelContent;
    @ViewById(R.id.tv_time_content)
    private TextView timeContent;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("扣分详情");
    }

    @Override
    protected void initData() {
        super.initData();
        AssessDeductionBean content = (AssessDeductionBean) getIntent().getSerializableExtra("content");

        Message msg = new Message();
        msg.what = 0x1040;
        msg.obj = content;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x1040:
                    AssessDeductionBean content = (AssessDeductionBean) msg.obj;
                    name.setText(content.getDepartment());
                    scoreTV.setText(content.getPoint());
                    titleContent.setText(content.getPointMx());
                    labelContent.setText(content.getText());
                    timeContent.setText(content.getUpdateTime());
                    break;
            }
        }
    };
}
