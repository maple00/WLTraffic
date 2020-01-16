package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.domain.ParagraphListBean;
import com.rainowood.wltraffic.domain.PlanManagerBean;
import com.rainowood.wltraffic.domain.SubPlanManagerBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.ui.adapter.SubItemPlanManagerAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/23 13:08
 * @Desc: 计划管理
 */
public final class PlanManagerActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plan_manager;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView tvTitle;
    @ViewById(R.id.gv_item_capital)
    private MeasureGridView itemCapitalGv;

    // 前期通知书
    @ViewById(R.id.tv_item_name)
    private TextView itemName;
    @ViewById(R.id.tv_start_address)
    private TextView startAddress;
    @ViewById(R.id.tv_end_address)
    private TextView endAddress;
    @ViewById(R.id.tv_construction_scale)
    private TextView constructionScale;
    @ViewById(R.id.ll_construction_scale)
    private LinearLayout llConstructionScale;       // 建设规模
    @ViewById(R.id.tv_query_notify)
    private TextView tvQueryNnotify;

    // 计划下达
    @ViewById(R.id.tv_plan_arrived)
    private AppCompatTextView planArrived;
    @ViewById(R.id.lv_item_word_content)
    private MeasureListView wordContent;
    @ViewById(R.id.tv_construction_content)
    private TextView constructionContent;
    @ViewById(R.id.tv_investment_scale)
    private TextView investmentScale;
    @ViewById(R.id.ll_construction_content)
    private LinearLayout ConstructionScaleContent;
    @ViewById(R.id.tv_query_release)
    private TextView queryRelease;
    @ViewById(R.id.ll_investment_scale)
    private LinearLayout llInvestmentScale;
    @ViewById(R.id.tv_query_release_2)
    private TextView queryRelease2;

    // 资金来源
    @ViewById(R.id.tv_total_investment)
    private TextView totalInvesment;
    @ViewById(R.id.tv_jianan_investment)
    private TextView jiananInvestment;
    @ViewById(R.id.tv_gap_investment)
    private TextView gapInvestment;
    @ViewById(R.id.tv_strive_invesment)
    private TextView striveInvesment;
    @ViewById(R.id.tv_simulation_srive_invesment)
    private TextView simulationSriveInvesment;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        tvTitle.setText("计划管理");
    }

    // 计划管理内容
    private PlanManagerBean planManager;
    // 附件
    private List<AttachBean> mSubItemWordList;
    // 资金来源
    private List<SubPlanManagerBean> capitalList;
    private String[] capitalTitles = {"中央补助", "市级补助", "债券", "银行贷款", "部门投入", "自筹", "其他"};

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();

        new Thread(() -> RequestPost.getItemPlanManagerData(Contants.ITEM_ID, PlanManagerActivity.this)).start();
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    /**
     * 文档的点击和展示method
     *
     * @param text        需要展示的文档
     * @param clickRegion 可以点击的区域
     * @param hideRegion  隐藏的区域
     */
    private void setOnClickAndShowDetail(final TextView text, final LinearLayout clickRegion, final TextView hideRegion, final String title) {
        text.post(() -> {
            int lineCount = text.getLineCount();
            if (lineCount > 5) {
                clickRegion.setOnClickListener(v -> {
                    Intent intent = new Intent(PlanManagerActivity.this, DocumentShowDetailActivity.class);
                    ParagraphListBean paragraph = new ParagraphListBean();
                    paragraph.setTitle(title);
                    paragraph.setContent(text.getText().toString().trim());
                    intent.putExtra("document", paragraph);
                    startActivity(intent);
                });
            } else {
                hideRegion.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            Map<String, String> data = JsonParser.parseJSONObject(body.get("data"));
            Log.d(TAG, "sxs---- " + body.get("data"));
            String itemName = data.get("itemName");         // 项目名称
            String ranges = data.get("ranges");             // 起始地址
            String rangesEnd = data.get("rangesEnd");       // 终止地址
            String buildSize = data.get("buildSize");       // 建设规模
            String itemMatter = data.get("itemMatter");     // 建设内容
            String investScalc = data.get("investScalc");   // 投资规模
            String allInvest = data.get("allInvest");       // 总投资
            String jiananInvest = data.get("jiananInvest"); // 建安投资
            String lack = data.get("lack");                 // 缺口
            String getMoney = data.get("getMoney");         // 已争取到的投资
            String willFight = data.get("willFight");       // 拟争取到的投资
            // 前期通知书
            planManager = new PlanManagerBean();
            planManager.setItemName(itemName);
            planManager.setStartAdr("起：" + ranges);
            planManager.setEndAdr("止：" + rangesEnd);
            planManager.setConstructionScale(buildSize);
            // 任务下达
            planManager.setPlanNo(data.get("planNo"));
            planManager.setConstructionContent(itemMatter);
            planManager.setInvestmentScale(investScalc);
            // 资金来源
            planManager.setTotalInvestment(allInvest);
            planManager.setJianAnInvestment(jiananInvest);
            planManager.setGapInvestment(lack);
            planManager.setStriveInvesment("￥" + getMoney + "万");
            planManager.setSimulationSriveInvesment("拟争取投资：￥" + willFight + "万");

            // 补贴
            String centralSubsidy = data.get("centralSubsidy");             // 中央补助
            String citySubsidy = data.get("citySubsidy");                   // 市级补助
            String bond = data.get("bond");                                 // 债券
            String loans = data.get("loans");                               // 银行贷款
            String sectionIn = data.get("sectionIn");                       // 部门投入
            String selfFinance = data.get("selfFinance");                   // 自筹
            String other = data.get("other");                               // 其他款
            List<String> capitalValuesList = new ArrayList<>();
            capitalValuesList.add(centralSubsidy);
            capitalValuesList.add(citySubsidy);
            capitalValuesList.add(bond);
            capitalValuesList.add(loans);
            capitalValuesList.add(sectionIn);
            capitalValuesList.add(selfFinance);
            capitalValuesList.add(other);
            capitalList = new ArrayList<>();
            for (int i = 0; i < capitalTitles.length; i++) {
                SubPlanManagerBean capital = new SubPlanManagerBean();
                capital.setTitle(capitalTitles[i]);
                capital.setValue(capitalValuesList.get(i));
                capitalList.add(capital);
            }

            // 附件
            mSubItemWordList = JsonParser.parseJSONArray(AttachBean.class, data.get("planNoFile"));
            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1404;
            mHandler.sendMessage(msg);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }

    /**
     * UI刷新
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0x1404:
                    // 前期通知书
                    itemName.setText(planManager.getItemName());
                    startAddress.setText(planManager.getStartAdr());
                    endAddress.setText(planManager.getEndAdr());
                    constructionScale.setText(planManager.getConstructionScale());
                    // 文档展示
                    setOnClickAndShowDetail(constructionScale, llConstructionScale, tvQueryNnotify, "建设规模");
                    // 计划下达---附件
                    planArrived.setText(planManager.getPlanNo());
                    ItemAttachListAdapter wordAdapter = new ItemAttachListAdapter(PlanManagerActivity.this, mSubItemWordList);
                    wordContent.setAdapter(wordAdapter);
                    wordAdapter.notifyDataSetChanged();
                    constructionContent.setText(planManager.getConstructionContent());
                    // 文档展示
                    setOnClickAndShowDetail(constructionContent, ConstructionScaleContent, queryRelease, "建设内容");
                    investmentScale.setText(planManager.getInvestmentScale());
                    // 文档展示
                    setOnClickAndShowDetail(investmentScale, llInvestmentScale, queryRelease2, "投资规模");
                    // 资金来源
                    totalInvesment.setText(planManager.getTotalInvestment());
                    jiananInvestment.setText(planManager.getJianAnInvestment());
                    gapInvestment.setText(planManager.getGapInvestment());
                    striveInvesment.setText(planManager.getStriveInvesment());
                    simulationSriveInvesment.setText(planManager.getSimulationSriveInvesment());

                    SubItemPlanManagerAdapter capitalAdapter = new SubItemPlanManagerAdapter(PlanManagerActivity.this, capitalList);
                    itemCapitalGv.setAdapter(capitalAdapter);
                    itemCapitalGv.setNumColumns(2);
                    capitalAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
