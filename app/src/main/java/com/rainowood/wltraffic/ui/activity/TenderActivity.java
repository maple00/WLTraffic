package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.SubQuestionsAndBareBean;
import com.rainowood.wltraffic.domain.TenderManagerBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.ui.adapter.TenderAdapter;
import com.rainowood.wltraffic.ui.adapter.TenderSubAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/28 11:35
 * @Desc: 招投标
 */
public class TenderActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

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
    @ViewById(R.id.lv_audit_company)
    private MeasureListView auditWord;
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
    @ViewById(R.id.lv_audit_export)
    private MeasureListView auditExport;
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
    @ViewById(R.id.lv_records_info)
    private MeasureListView recordsInfo;
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
    @ViewById(R.id.lv_contract_attach)
    private MeasureListView contractAttach;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("招投标管理");

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
                } else {         // 显示
                    showText.setVisibility(View.GONE);
                }
            }
        });
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
            case R.id.ll_audit_content:         // 公司审核 -- 查看详情
                Intent intent = new Intent(this, TenderDetailAuditActivity.class);
                intent.putExtra("key", "company_audit");
                startActivity(intent);
                break;
            case R.id.ll_expert_content:            // 专家审核 -- 查看详情
                Intent intent1 = new Intent(this, TenderDetailAuditActivity.class);
                intent1.putExtra("key", "experts_audit");
                startActivity(intent1);
                break;
            case R.id.ll_record_content:            // 备案   -- 查看详情
                openActivity(TenderRecordActivity.class);
                break;
        }
    }

    // 请求的数据
    TenderManagerBean data;

    /*
       模拟数据
        */
    // 代理公司
    private String[] agentCompany = {"代理公司", "代理费"};
    // 公司审核,专家审核
    private String[] auditCompany = {"公司审核", "审核人"};
    private String[] expertReview = {"专家审核", "审核人"};
    // 备案
    private String[] forRecords = {"备案"};
    // 挂网
    private String[] nets = {"挂网", "挂网时间：2019.12.18", "预计开标时间：2019.12.18"};
    // 质疑答疑、补漏
    private String[] titles = {"质疑答疑", "补漏"};
    // 开标
    private String[] bidOpens = {"开标", "中标金额"};
    // 公示
    private String[] publicShow = {"公示", "起止时间：2019.12.19-2019.12.30"};
    // 合同签订
    private String[] contractInfo = {"合同签订", "3650万", "合同金额", "2019.12.19"};


    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestPost.getItemTenderData(Contants.ITEM_ID, TenderActivity.this);
            }
        }).start();

    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            data = JsonParser.parseJSONObject(TenderManagerBean.class, body.get("data"));
            // 代理公司
            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1431;
            mHandler.sendMessage(msg);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0x1431:
                    // 代理公司
                    titleLabel.setText(agentCompany[0]);
                    titleCompany.setText(data.getA().getNameSix());
                    titleMoney.setText(data.getA().getMoneySix());
                    type.setText(agentCompany[1]);
                    time.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"12dp\">" + "确定时间 &nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.colorWord) + " font-size=\"12dp\">" + data.getA().getTimeOneSix() + "</font>"));

                    // 公司审核
                    auditTitle.setText(auditCompany[0]);
                    auditName.setText(data.getB().getPeopleSix());
                    auditType.setText(auditCompany[1]);
                    auditTime.setText(data.getB().getTimeOneSix());
                    auditContent.setText(data.getB().getIdeaSix());
                    // 附件多个
                    ItemAttachListAdapter auditComAdapter = new ItemAttachListAdapter(TenderActivity.this, data.getB().getIdeaSixFile());
                    auditWord.setAdapter(auditComAdapter);
                    initTextView(auditContent, auditViewAll);
                    auditContentLL.setOnClickListener(TenderActivity.this);

                    // 专家审核
                    expertTitle.setText(expertReview[0]);
                    expertName.setText(data.getC().getPeopleSix());
                    expertType.setText(expertReview[1]);
                    expertTime.setText(data.getC().getTimeOneSix());
                    experContent.setText(data.getC().getIdeaSix());
                    // 多个附件
                    ItemAttachListAdapter auditExAdapter = new ItemAttachListAdapter(TenderActivity.this, data.getC().getIdeaSixFile());
                    auditExport.setAdapter(auditExAdapter);
                    initTextView(experContent, expertAll);
                    expertContentLL.setOnClickListener(TenderActivity.this);
                    // 备案
                    recordTitle.setText(forRecords[0]);
                    recordTimeA.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "政务备案时间： &nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + data.getD().getTimeOneSix() + "</font>"));
                    //recordTimeA.setText(forRecords[1]);
                    recordTimeB.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "交通局备案时间：&nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + data.getD().getTimeTwoSix() + "</font>"));
                    recordContent.setText(data.getD().getIdeaSix());
                    ItemAttachListAdapter recordAdapter = new ItemAttachListAdapter(TenderActivity.this, data.getD().getIdeaSixFile());
                    recordsInfo.setAdapter(recordAdapter);
                    recordContentLL.setOnClickListener(TenderActivity.this);
                    initTextView(recordContent, recordAll);
                    // 挂网
                    netTitle.setText(nets[0]);
                    netTimeA.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "挂网时间：&nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + data.getE().getTimeOneSix() + "</font>"));
                    //netTimeB.setText(nets[2]);
                    netTimeB.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "预计开标时间：&nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + data.getE().getTimeTwoSix() + "</font>"));

                    // 质疑答疑、补漏
                    List<SubQuestionsAndBareBean> tenderList = new ArrayList<>();
                    for (int i = 0; i < titles.length; i++) {
                        SubQuestionsAndBareBean tender = new SubQuestionsAndBareBean();
                        tender.setTitle(titles[i]);
                        if (i == 0) {            // 质疑答疑
                            tender.setOne(data.getF().getOne());
                        }
                        if (i == 1) {            // 补遗
                            tender.setTwo(data.getF().getTwo());
                        }
                        tenderList.add(tender);
                    }
                    TenderAdapter tenderAdapter = new TenderAdapter(TenderActivity.this, tenderList);
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
                        public void onItemClick(int position) {
                            // 查看详情
                            switch (position) {
                                case 0:         // 质疑答疑
                                    Intent questionIntent = new Intent(TenderActivity.this, TenderListActivity.class);
                                    Bundle bundle = new Bundle();
                                    questionIntent.putExtra("key", "question");
                                    bundle.putSerializable("question", data.getF());
                                    questionIntent.putExtras(bundle);
                                    startActivity(questionIntent);
                                    break;
                                case 1:         // 补漏
                                    Intent bareIntent = new Intent(TenderActivity.this, TenderListActivity.class);
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putSerializable("question", data.getF());
                                    bareIntent.putExtra("key", "bare");
                                    bareIntent.putExtras(bundle1);
                                    startActivity(bareIntent);
                                    break;
                            }
                        }
                    });
                    // 开标
                    bidLabel.setText(bidOpens[0]);
                    bidCompany.setText(data.getG().getNameSix());
                    bidMoney.setText(data.getG().getMoneySix());
                    bidType.setText(bidOpens[1]);
                    //bidTime.setText(bidOpens[4]);
                    bidTime.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"12dp\">" + "开标时间：&nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.colorWord) + " font-size=\"12dp\">" + data.getG().getTimeOneSix() + "</font>"));
                    // 公示
                    publicTitle.setText(publicShow[0]);
                    //startEndTime.setText(publicShow[1]);
                    startEndTime.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"14dp\">" + "起止时间：&nbsp;" + "</font>"
                            + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"14dp\">" + data.getH().getTimeOneSix() + " - " + data.getH().getTimeTwoSix() + "</font>"));
                    // 随机有投诉
                    if ("有".equals(data.getH().getComplainSix())) {      // 有投诉
                        hasComplaints.setVisibility(View.VISIBLE);
                    } else {
                        hasComplaints.setVisibility(View.GONE);
                    }
                    // 合同签订
                    contractTitle.setText(contractInfo[0]);
                    contractMoney.setText(data.getI().getMoneySix());
                    contractType.setText(contractInfo[2]);
                    contractTime.setText(data.getI().getTimeOneSix());
                    // 合同附件
                    ItemAttachListAdapter attachListAdapter = new ItemAttachListAdapter(TenderActivity.this, data.getI().getIdeaSixFile());
                    contractAttach.setAdapter(attachListAdapter);
                    break;
            }
        }
    };
}
