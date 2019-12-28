package com.rainowood.wltraffic.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubTenderBean;
import com.rainowood.wltraffic.domain.TenderBean;
import com.rainowood.wltraffic.ui.adapter.TenderAdapter;
import com.rainowood.wltraffic.ui.adapter.TenderSubAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 11:35
 * @Desc: 招投标
 */
public class TenderActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_title_label)
    private TextView titleLabel;
    @ViewById(R.id.tv_title_company)
    private TextView titleCompany;
    @ViewById(R.id.tv_title_money)
    private TextView titleMoney;
    @ViewById(R.id.tv_money_type)
    private TextView type;
    @ViewById(R.id.tv_confirm_time)
    private TextView time;

    // 公司审核 start
    @ViewById(R.id.tv_audit_title)
    private TextView auditTitle;
    @ViewById(R.id.tv_audit_name)
    private TextView auditName;
    @ViewById(R.id.tv_audit_type)
    private TextView auditType;
    @ViewById(R.id.tv_audit_time)
    private TextView auditTime;
    @ViewById(R.id.ll_audit_content)
    private LinearLayout auditContentLL;
    @ViewById(R.id.tv_audit_content)
    private TextView auditContent;
    @ViewById(R.id.tv_view_all)
    private TextView auditViewAll;
    @ViewById(R.id.tv_audit_word_title)
    private TextView auditWord;
    @ViewById(R.id.ll_audit_download)
    private LinearLayoutCompat auditDownload;
    @ViewById(R.id.ll_audit_preview)
    private LinearLayoutCompat auditPreview;
    // end

    // 专家审核 start
    @ViewById(R.id.tv_expert_title)
    private TextView expertTitle;
    @ViewById(R.id.tv_expert_name)
    private TextView expertName;
    @ViewById(R.id.tv_expert_type)
    private TextView expertType;
    @ViewById(R.id.tv_expert_time)
    private TextView expertTime;
    @ViewById(R.id.ll_expert_content)
    private LinearLayout expertContentLL;
    @ViewById(R.id.tv_expert_content)
    private TextView experContent;
    @ViewById(R.id.tv_expert_all)
    private TextView expertAll;
    // end

    // 备案 start
    @ViewById(R.id.tv_record_title)
    private TextView recordTitle;
    @ViewById(R.id.tv_record_time_a)
    private TextView recordTimeA;
    @ViewById(R.id.tv_record_time_b)
    private TextView recordTimeB;
    @ViewById(R.id.ll_record_content)
    private LinearLayout recordContentLL;
    @ViewById(R.id.tv_record_content)
    private TextView recordContent;
    @ViewById(R.id.tv_record_all)
    private TextView recordAll;
    // end

    // 挂网
    @ViewById(R.id.tv_net_title)
    private TextView netTitle;
    @ViewById(R.id.tv_net_time_a)
    private TextView netTimeA;
    @ViewById(R.id.tv_net_time_b)
    private TextView netTimeB;

    // 质疑答疑、补漏
    @ViewById(R.id.lv_list)
    private MeasureListView mList;

    // 开标
    @ViewById(R.id.tv_bid_label)
    private TextView bidLabel;
    @ViewById(R.id.tv_bid_company)
    private TextView bidCompany;
    @ViewById(R.id.tv_bid_money)
    private TextView bidMoney;
    @ViewById(R.id.tv_bid_type)
    private TextView bidType;
    @ViewById(R.id.tv_bid_time)
    private TextView bidTime;

    // 公示
    @ViewById(R.id.tv_public_title)
    private TextView publicTitle;
    @ViewById(R.id.ll_has_complaints)
    private LinearLayout hasComplaints; // 是否有投诉
    @ViewById(R.id.tv_start_end_time)
    private TextView startEndTime;

    // 签订合同
    @ViewById(R.id.tv_contract_title)
    private TextView contractTitle;
    @ViewById(R.id.tv_contract_money)
    private TextView contractMoney;
    @ViewById(R.id.tv_contract_type)
    private TextView contractType;
    @ViewById(R.id.tv_contract_time)
    private TextView contractTime;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("招投标管理");

        // 代理公司
        titleLabel.setText(agentCompany[0]);
        titleCompany.setText(agentCompany[1]);
        titleMoney.setText(agentCompany[2]);
        type.setText(agentCompany[3]);
        time.setText(agentCompany[4]);

        // 公司审核
        auditTitle.setText(auditCompany[0]);
        auditName.setText(auditCompany[1]);
        auditType.setText(auditCompany[2]);
        auditTime.setText(auditCompany[3]);
        auditContent.setText(auditCompany[4]);
        auditWord.setText(auditCompany[5]);
        initTextView(auditContent, auditViewAll);
        auditContentLL.setOnClickListener(this);

        // 专家审核
        expertTitle.setText(expertReview[0]);
        expertName.setText(expertReview[1]);
        expertType.setText(expertReview[2]);
        expertTime.setText(expertReview[3]);
        experContent.setText(expertReview[4]);
        initTextView(experContent, expertAll);
        expertContentLL.setOnClickListener(this);

        // 备案
        recordTitle.setText(forRecords[0]);
        recordTimeA.setText(forRecords[1]);
        recordTimeB.setText(forRecords[2]);
        recordContent.setText(forRecords[3]);
        recordContentLL.setOnClickListener(this);
        initTextView(recordContent, recordAll);

        // 挂网
        netTitle.setText(nets[0]);
        netTimeA.setText(nets[1]);
        netTimeB.setText(nets[2]);

        // 质疑答疑、补漏
        TenderAdapter tenderAdapter = new TenderAdapter(this, tenderList);
        mList.setAdapter(tenderAdapter);
        tenderAdapter.setItemClickListener(new TenderSubAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                toast("点击了：" + position);
            }
        });
        // 查看全部
        tenderAdapter.setListener(new TenderAdapter.OnClickViewListener() {
            @Override
            public void onItemClick() {
                // 查看详情
                toast("查看全部");
            }
        });

        // 开标
        bidLabel.setText(bidOpens[0]);
        bidCompany.setText(bidOpens[1]);
        bidMoney.setText(bidOpens[2]);
        bidType.setText(bidOpens[3]);
        bidTime.setText(bidOpens[4]);

        // 公示
        publicTitle.setText(publicShow[0]);
        startEndTime.setText(publicShow[1]);
                    // 随机有投诉
        int num = (int) (Math.random() * 2);
        if (num == 1){      // 有投诉
            hasComplaints.setVisibility(View.VISIBLE);
        }else {
            hasComplaints.setVisibility(View.GONE);
        }

        // 合同签订
        contractTitle.setText(contractInfo[0]);
        contractMoney.setText(contractInfo[1]);
        contractType.setText(contractInfo[2]);
        contractTime.setText(contractInfo[3]);

    }

    private void initTextView(final TextView textView, final TextView showText) {
        textView.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = textView.getLineCount();
                textView.setLineSpacing(12, 1);
                if (lineCount > 5) { // 隐藏
                    textView.setMaxLines(2);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    // 记录收起的状态 company_audit
                    if ("company_audit".contentEquals(textView.getHint())) { // 公司审核
                        auditFlag = true;
                    }
                    if ("expert_audit".contentEquals(textView.getHint())) { // 公司审核
                        expertFlag = true;
                    }
                    if ("record".contentEquals(textView.getHint())){
                        recordFlag = true;
                    }

                } else {         // 显示
                    showText.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setContentShowOrHide(boolean isShow, TextView text) {
        if (isShow) {     //  展开
            text.setMaxLines(Integer.MAX_VALUE);
            text.setEllipsize(null);
        } else {             // 收起
            text.setMaxLines(2);
            text.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    /**
     * 标记
     */
    private boolean auditFlag;      // 公司审核
    private boolean expertFlag;     // 专家审核
    private boolean recordFlag;     // 备案

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_audit_content:         // 公司审核 -- 查看详情
                setContentShowOrHide(auditFlag, auditContent);
                auditFlag = !auditFlag;
                break;
            case R.id.ll_expert_content:            // 专家审核 -- 查看详情
                setContentShowOrHide(expertFlag, experContent);
                expertFlag = !expertFlag;
                break;
            case R.id.ll_record_content:            // 备案   -- 查看详情
                setContentShowOrHide(recordFlag, recordContent);
                recordFlag = !recordFlag;
                break;
        }
    }

    @Override
    protected void initData() {
        super.initData();
        /*
        质疑答疑、补漏
         */
        tenderList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            TenderBean tender = new TenderBean();
            tender.setTitle(titles[i]);
            if (i == 0){            // 质疑答疑
                List<SubTenderBean> subTenderList = new ArrayList<>();
                for (int j = 0; j < labels1.length; j++) {
                    SubTenderBean subTender = new SubTenderBean();
                    subTender.setLabel(labels1[i]);
                    subTenderList.add(subTender);
                }
                tender.setmList(subTenderList);
            }
            if (i == 1){            // 补遗
                List<SubTenderBean> subTenderList = new ArrayList<>();
                for (int j = 0; j < labels2.length; j++) {
                    SubTenderBean subTender = new SubTenderBean();
                    subTender.setLabel(labels2[i]);
                    subTenderList.add(subTender);
                }
                tender.setmList(subTenderList);
            }
            tenderList.add(tender);
        }
    }

    /*
    模拟数据
     */
    // 代理公司
    private String[] agentCompany = {"代理公司", "重庆大城建德建筑工程有限公司", "56.2万", "代理费", "确定时间  2019.12.19"};
    // 公司审核,专家审核
    private String[] auditCompany = {"公司审核", "吴建新", "审核人", "2019.12.19", "施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工...",
            "白马坪项目修改意见.doc"};
    private String[] expertReview = {"专家审核", "罗钟石", "审核人", "2019.12.19", "施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工...",
            "白马坪项目修改意见.doc"};
    // 备案
    private String[] forRecords = {"备案", " 政务备案时间：2019.12.18", "交通局备案时间：2019.12.18", "施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工...",};
    // 挂网
    private String[] nets = {"挂网", "挂网时间：2019.12.18", "预计开标时间：2019.12.18"};
    // 质疑答疑、补漏
    private List<TenderBean> tenderList;
    private String[] titles = {"质疑答疑", "补漏"};
    private String[] labels1 = {"施工动迁任务较重，造成城市环境污染的情况应...","施工动迁任务较重，造成城市环境污染的情况应...",
                        "施工动迁任务较重，造成城市环境污染的情况应...","施工动迁任务较重，造成城市环境污染的情况应...","施工动迁任务较重，造成城市环境污染的情况应..."};
    private String[] labels2 = {"评标办法前附表2.2.3条评标偏差率计算公式修...", "投标人须知前附表10.2条中“（一）设计费：本...",
        "投标人须知前附表10.3条中设计费支付调整为是...", "安装工程及竣工试验一切险的投保方及对投保的..."};

    // 开标
    private String[] bidOpens = {"开标", "重庆大城建德建筑工程有限公司", "3650万", "中标金额", "开标时间：2019.12.18"};
    // 公示
    private String[] publicShow = {"公示", "起止时间：2019.12.19-2019.12.30"};
    // 合同签订
    private String[] contractInfo = {"合同签订", "3650万", "合同金额","2019.12.19"};
}
