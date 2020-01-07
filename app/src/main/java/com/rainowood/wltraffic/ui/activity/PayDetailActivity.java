package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.domain.SubPayContentBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.ui.adapter.PayDetailAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/31 13:14
 * @Desc: 支付详情
 */
public class PayDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_pay_money)
    private TextView payMoney;

    @ViewById(R.id.lv_money_detail)
    private MeasureListView moneyDetail;

    // 业主单位
    @ViewById(R.id.tv_label)
    private TextView label;
    @ViewById(R.id.lv_pay_attach)
    private MeasureListView payAttach;


    private SubPayContentBean content;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("支付详情");

        // 需判断是交通局还是业主单位
        String key = (String) getIntent().getCharSequenceExtra("key");
        // 数据
        content = (SubPayContentBean) getIntent().getSerializableExtra("value");
        if ("transport".equals(key)) {           // 交通局
            label.setVisibility(View.GONE);
            payAttach.setVisibility(View.GONE);
            initTransportInfos();
            PayDetailAdapter detailAdapter = new PayDetailAdapter(this, mList);
            moneyDetail.setAdapter(detailAdapter);
        }

        if ("ou".equals(key)) {              // 业主单位
            label.setText("合同附件");
            initOUInfos();
            PayDetailAdapter detailAdapter = new PayDetailAdapter(this, mList);
            moneyDetail.setAdapter(detailAdapter);

            // 合同附件
            ItemAttachListAdapter attachmentListAdapter = new ItemAttachListAdapter(this, content.getFileTypeFile());
            payAttach.setAdapter(attachmentListAdapter);
        }

        payMoney.setText(content.getPayMoney());

    }

    /*
     模拟数据
    */
    private List<SubItemLabelBean> mList;
    private String[] transportLabels = {"计划金额", "计划变更金额", "变更后累计金额", "支付时间", "更新时间"};
    private String[] ouLabels = {"合同金额", "合同变更金额", "变更后累计金额", "支付时间", "更新时间"};

    private void initTransportInfos() {
        mList = new ArrayList<>();
        // 交通局
        for (int i = 0; i < transportLabels.length; i++) {
            SubItemLabelBean label = new SubItemLabelBean();
            label.setTitle(transportLabels[i]);
            if (i == 0) {
                label.setContent(content.getPlanMoney());
            }
            if (i == 1) {
                label.setContent(content.getPlChangeMoney());
            }
            if (i == 2) {
                label.setContent(content.getChsumMoney());
            }
            if (i == 3) {
                label.setContent(content.getPayTime());
            }
            if (i == 4) {
                label.setContent(content.getUpdateTime());
            }
            mList.add(label);
        }
    }

    private void initOUInfos() {
        mList = new ArrayList<>();
        for (int i = 0; i < ouLabels.length; i++) {
            SubItemLabelBean label = new SubItemLabelBean();
            label.setTitle(ouLabels[i]);
            if (i == 0) {
                label.setContent(content.getPlanMoney());
            }
            if (i == 1) {
                label.setContent(content.getPlChangeMoney());
            }
            if (i == 2) {
                label.setContent(content.getChsumMoney());
            }
            if (i == 3) {
                label.setContent(content.getPayTime());
            }
            if (i == 4) {
                label.setContent(content.getUpdateTime());
            }
            // 文件列表
            mList.add(label);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(PayManagerActivity.class);
                finish();
                break;
        }
    }
}
