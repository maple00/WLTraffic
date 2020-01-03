package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.ParagraphListBean;
import com.rainowood.wltraffic.domain.ProjectInfoBean;
import com.rainowood.wltraffic.domain.SubIteProjectBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.domain.SubItemListContentBean;
import com.rainowood.wltraffic.domain.SubItemWordBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ItemDetailParagraghAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemDetailSubItemAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemDetailWordAdapter;
import com.rainowood.wltraffic.ui.adapter.SubItemLabelAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.view.SmartTextView;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: shearson
 * @Time: 2019/12/21 21:37
 * @Desc: 项目详情
 */
public final class ProjectDetailActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_detail;
    }

    @ViewById(R.id.btn_back)
    private Button backBtn;
    @ViewById(R.id.tv_title)
    private TextView titleTv;
    //
    @ViewById(R.id.tv_item_name)
    private SmartTextView itemName;
    @ViewById(R.id.tv_item_label)
    private SmartTextView itemLabel;
    @ViewById(R.id.tv_item_time)
    private SmartTextView itemTime;
    @ViewById(R.id.lv_item_content_title)
    private MeasureListView itemContentTitle;
    @ViewById(R.id.gv_sub_item)
    private MeasureGridView subItemGridView;
    @ViewById(R.id.lv_item_word_content)
    private MeasureListView itemContent;
    @ViewById(R.id.lv_item_paragraph)
    private MeasureListView paragraghListView;

    private DialogUtils dialog;
    private String id;

    @Override
    protected void initView() {
        backBtn.setOnClickListener(this);
        titleTv.setText("项目详情");

        // 初始化数据
        postDelayed(new Runnable() {
            @Override
            public void run() {
                itemName.setText(mProjectInfo.getItemName());
                itemLabel.setText(mProjectInfo.getStage());
                itemTime.setText(year);
            }
        }, 500);
        // 子项目标签
        postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("sxs", "-->" + mSubListLabel.toString());
                SubItemLabelAdapter itemLabelAdapter = new SubItemLabelAdapter(ProjectDetailActivity.this, mSubListLabel);
                itemContentTitle.setAdapter(itemLabelAdapter);
            }
        }, 500);

        // 子项目
        ItemDetailSubItemAdapter subItemAdapter = new ItemDetailSubItemAdapter(this, mSubProjectList);
        subItemGridView.setAdapter(subItemAdapter);
        subItemGridView.setColumnWidth(5);
        subItemAdapter.notifyDataSetChanged();


        subItemAdapter.setClickListener(new ItemDetailSubItemAdapter.ItemOnClickListener() {
            @Override
            public void ItemOnClick(int position) {
                switch (position) {
                    case 0:             // 计划管理
                        openActivity(PlanManagerActivity.class);
                        break;
                    case 1:             // 项目建设程序
                        openActivity(ProjectProcedureActivity.class);
                        break;
                    case 2:             // 项目进度
                        openActivity(ProjectProgressActivity.class);
                        break;
                    case 3:             // 支付管理
                        openActivity(PayManagerActivity.class);
                        break;
                    case 4:             // 质量安全
                        openActivity(QualitySafetyActivity.class);
                        break;
                    case 5:             // 变更管理
                        openActivity(ChangeActivity.class);
                        break;
                    case 6:             // 农民工工资
                        openActivity(FarmersSalaryManagerActivity.class);
                        break;
                    case 7:             // 招投标
                        openActivity(TenderActivity.class);
                        break;
                    case 8:             // 考核管理
                        openActivity(AssessManagerActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });

        // 文档列表
        ItemDetailWordAdapter wordAdapter = new ItemDetailWordAdapter(this, mlSubItemList);
        itemContent.setAdapter(wordAdapter);
        wordAdapter.notifyDataSetChanged();

        // 段落列表
        ItemDetailParagraghAdapter paragraghAdapter = new ItemDetailParagraghAdapter(this, mParagraphList);
        paragraghListView.setAdapter(paragraghAdapter);

        paragraghAdapter.setContentCilckListener(new ItemDetailParagraghAdapter.ContentCilckListener() {
            @Override
            public void ContentOnClick(int position) {
                Intent intent = new Intent(ProjectDetailActivity.this, DocumentShowDetailActivity.class);
                intent.putExtra("document", mParagraphList.get(position));
                startActivity(intent);
            }
        });
        paragraghAdapter.notifyDataSetChanged();

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

    /*
    模拟数据
     */
    // 项目
    private ProjectInfoBean mProjectInfo;
    private String year;
    // 项目的子内容
    private List<SubItemLabelBean> mSubListLabel;
    private String[] mTitles = {"甲方公司", "代建公司", "总投资", "批准概算投资", "建安投资", "业主管理费"};
    private List<String> mContentList;

    // 项目详情中的子项目
    private List<SubIteProjectBean> mSubProjectList;
    private int[] mIcons = {R.drawable.ic_icon_project_plan, R.drawable.ic_icon_project_program, R.drawable.ic_icon_project_process,
            R.drawable.ic_icon_project_pay, R.drawable.ic_icon_project_safe, R.drawable.ic_icon_project_change,
            R.drawable.ic_icon_project_worker, R.drawable.ic_icon_project_bidding, R.drawable.ic_icon_project_examine};
    private String[] mLabels = {"计划管理", "项目建设程序", "项目进度", "支付管理", "质量安全",
            "变更管理", "农名工工资", "招投标", "考核管理"};

    // 项目详情中文档的列表
    private List<SubItemListContentBean> mlSubItemList;
    private String[] mItemTitles = {"计划情况", "任务来源"};
    private String[] mBackTitles = {"武隆交委计[2016]15号2016.07.20", "武隆交委计[2016]15号2016.07.20", "武隆交委计[2016]15号2016.07.20"};
    private String[] mPreTitles = {"安置房改造通知.doc", "安置房改造通知.doc", "安置房改造通知.doc"};

    // 项目详情中的段落 列表
    private List<ParagraphListBean> mParagraphList;
    private String[] mParagraphTitles = {"主要建设内容", "年度建设目标", "备注"};
    private String[] mParagraphContents = {"该项目白果坪至天池坪K0+000～K3+160，起于白果坪，止于天尺坪。白果坪至豹岩K0+000～K2+842，起于白果坪，止于豹岩村。山王庙K0+000～K0697，起于铁佛村指示牌处，止于山王庙垭该项目白果坪至天池坪K0+000～K3+160，起于白果坪，止于天尺坪。白果坪至豹岩K0+000～K2+842，起于白果坪，止于豹该项目白果坪至天池坪K0+000～K3+160，起于白果坪，止于天尺坪。白果坪至豹岩K0+000～K2+842，起于白果坪，止于豹岩村。山王庙K0+000～K0697，起于铁佛村指示牌处，止于山王庙垭该项目白果坪至天池坪K0+000～K3+160，起于白果坪，止于天尺坪。白果坪至豹岩K0+000～K2+842，起于白果坪，止于豹",
            "该项目白果坪至天池坪K0+000～K3+160，起于白果坪，止于天尺坪。白果坪至豹岩K0+000～K2+842，起于白果坪，止于豹岩村。山王庙K0+000～K0697，起于铁佛村指示牌处，止于山王庙垭。",
            "该项目白果坪至天池坪K0+000～K3+160，起于白果坪，止于天尺坪。白果坪至豹岩K0+000～K2+842，起于白果坪，止于豹岩村。山王庙K0+000～K0697，起于铁佛村指示牌处，止于山王庙垭。"};

    @Override
    protected void initData() {
        super.initData();
        //请求数据
        waitDialog();
        dialog.showDialog();
        mProjectInfo = new ProjectInfoBean();
        id = getIntent().getStringExtra("id");
        String stage = getIntent().getStringExtra("stage");         // 项目阶段
        // 通过id请求详情数据
        RequestPost.getItemDetailData(id, this);
        /*
        request end
         */
        mProjectInfo.setStage(stage);

        mSubProjectList = new ArrayList<>();
        for (int i = 0; i < mLabels.length; i++) {
            SubIteProjectBean mSubProject = new SubIteProjectBean();
            mSubProject.setIcon(mIcons[i]);
            mSubProject.setLabel(mLabels[i]);

            mSubProjectList.add(mSubProject);
        }
        // 项目详情文档列表
        mlSubItemList = new ArrayList<>();
        for (int i = 0; i < mItemTitles.length; i++) {
            SubItemListContentBean mSubItemListContent = new SubItemListContentBean();
            mSubItemListContent.setId(i + 1 + "");
            mSubItemListContent.setTitle(mItemTitles[i]);

            List<SubItemWordBean> mSubItemWordList = new ArrayList<>();
            for (int j = 0; j < mBackTitles.length; j++) {
                SubItemWordBean mSubItemWord = new SubItemWordBean();
                mSubItemWord.setBackEditTitle(mBackTitles[i]);
                mSubItemWord.setWordTitle(mPreTitles[i]);

                mSubItemWordList.add(mSubItemWord);
            }
            mSubItemListContent.setmList(mSubItemWordList);
            mlSubItemList.add(mSubItemListContent);
        }
        // 段落列表
        mParagraphList = new ArrayList<>();
        for (int i = 0; i < mParagraphTitles.length; i++) {
            ParagraphListBean mParagraph = new ParagraphListBean();
            mParagraph.setId(mlSubItemList.size() + i + 1 + "");
            mParagraph.setTitle(mParagraphTitles[i]);
            mParagraph.setContent(mParagraphContents[i]);
            mParagraphList.add(mParagraph);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(HomeActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            Map<String, String> data = JsonParser.parseJSONObject(body.get("data"));

            String itemName = data.get("itemName");     // 项目名称
            mProjectInfo.setItemName(itemName);
            this.year = data.get("year");
            //
            String jname = data.get("jname");       // 甲方公司
            String dname = data.get("dname");       // 代建公司
            String allInvest = data.get("allInvest");   // 总投资
            String permitInvest = data.get("permitInvest");     // 批准概算投资
            String jiananInvest = data.get("jiananInvest");     // 建安投资
            String proprietor = data.get("proprietor");         // 业主管理费
            mContentList = new ArrayList<>();
            mContentList.add(jname);
            mContentList.add(dname);
            mContentList.add(allInvest);
            mContentList.add(permitInvest);
            mContentList.add(jiananInvest);
            mContentList.add(proprietor);
            //
            String itemMatter = data.get("itemMatter");     // 主要建设内容
            String objectiveYear = data.get("objectiveYear");       // 年度建设目标
            String text = data.get("text");                     // 备注

            // 附件


            initDataDetail();
            dismissDialog();
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }

    /**
     * 初始化头部数据
     */
    private void initDataDetail() {
        // 甲方，代建等信息
        mSubListLabel = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            SubItemLabelBean mSubItemLabel = new SubItemLabelBean();
            mSubItemLabel.setTitle(mTitles[i]);
            mSubItemLabel.setContent(mContentList.get(i));
            mSubListLabel.add(mSubItemLabel);
        }

        //

    }
}
