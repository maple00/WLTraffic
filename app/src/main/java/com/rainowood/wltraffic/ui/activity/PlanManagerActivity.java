package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.ParagraphListBean;
import com.rainowood.wltraffic.domain.PlanManagerBean;
import com.rainowood.wltraffic.domain.SubItemWordBean;
import com.rainowood.wltraffic.domain.SubPlanManagerBean;
import com.rainowood.wltraffic.ui.adapter.ItemDetailWordListAdapter;
import com.rainowood.wltraffic.ui.adapter.SubItemPlanManagerAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 13:08
 * @Desc: 计划管理
 */
public class PlanManagerActivity extends BaseActivity implements View.OnClickListener {

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

    @Override
    protected void initView() {

        btnBack.setOnClickListener(this);
        tvTitle.setText("计划管理");
        // 前期通知书
        itemName.setText(planManager.getItemName());
        startAddress.setText(planManager.getStartAdr());
        endAddress.setText(planManager.getEndAdr());
        constructionScale.setText(planManager.getConstructionScale());
        // 文档展示
        setOnClickAndShowDetail(constructionScale, llConstructionScale, tvQueryNnotify, "建设规模");

        // 计划下达
        ItemDetailWordListAdapter wordAdapter = new ItemDetailWordListAdapter(this, mSubItemWordList);
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

        SubItemPlanManagerAdapter capitalAdapter = new SubItemPlanManagerAdapter(this, capitalList);
        itemCapitalGv.setAdapter(capitalAdapter);
        itemCapitalGv.setNumColumns(2);
        capitalAdapter.notifyDataSetChanged();

    }

    /*
    模拟数据
     */
    private PlanManagerBean planManager;

    private List<SubItemWordBean> mSubItemWordList;
    private String[] mBackTitles = {"武隆交委计[2016]15号2016.07.20", "武隆交委计[2016]15号2016.07.20", "武隆交委计[2016]15号2016.07.20"};
    private String[] mPreTitles = {"安置房改造通知.doc", "安置房改造通知.doc", "安置房改造通知.doc"};

    // 资金来源
    private List<SubPlanManagerBean> capitalList;
    private String[] capitalTitles = {"中央补助", "市级补助", "债券", "银行贷款", "部门投入", "自筹", "其他"};
    private String[] capitalValues = {"1200万", "1200万", "150万", "150万", "240万", "240万", "240万"};

    @Override
    protected void initData() {
        super.initData();

        capitalList = new ArrayList<>();
        for (int i = 0; i < capitalTitles.length; i++) {
            SubPlanManagerBean capital = new SubPlanManagerBean();
            capital.setTitle(capitalTitles[i]);
            capital.setValue(capitalValues[i]);

            capitalList.add(capital);
        }

        // 前期通知书
        planManager = new PlanManagerBean();
        planManager.setItemName("丰裕粮油安置房建设工程泉城花都E区安置工程丰裕粮油安置房");
        planManager.setStartAdr("起：" + "白果坪");
        planManager.setEndAdr("止：" + "白马丁");
        planManager.setConstructionScale("预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地。预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地。预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地，投资规模达到15亿以上，将打造成...");
        // 任务下达
        planManager.setConstructionContent("预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地。预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地。预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地，投资规模达到15亿以上，将打造成...");
        planManager.setInvestmentScale("预计367.5亩，投资规模达到15亿以上，将打造成为西南地区乃全国最大的现代影视拍摄基地。预计367.5亩，");
        // 资金来源
        planManager.setTotalInvestment("15000");
        planManager.setJianAnInvestment("15000");
        planManager.setGapInvestment("15000");
        planManager.setStriveInvesment("￥" + "1463.50" + "万");
        planManager.setSimulationSriveInvesment("拟争取投资：￥" + "2000.00" + "万");


        mSubItemWordList = new ArrayList<>();
        for (int j = 0; j < mBackTitles.length; j++) {
            SubItemWordBean mSubItemWord = new SubItemWordBean();
            mSubItemWord.setBackEditTitle(mBackTitles[j]);
            mSubItemWord.setWordTitle(mPreTitles[j]);

            mSubItemWordList.add(mSubItemWord);
        }
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
        text.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = text.getLineCount();
                if (lineCount > 5) {
                    clickRegion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(PlanManagerActivity.this, DocumentShowDetailActivity.class);
                            ParagraphListBean paragraph = new ParagraphListBean();
                            paragraph.setTitle(title);
                            paragraph.setContent(text.getText().toString().trim());
                            intent.putExtra("document", paragraph);
                            startActivity(intent);
                        }
                    });
                } else {
                    hideRegion.setVisibility(View.GONE);
                }
            }
        });
    }
}
