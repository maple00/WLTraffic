package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
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
import com.rainowood.wltraffic.ui.adapter.ItemDetailParagraghAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemDetailSubItemAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemDetailWordAdapter;
import com.rainowood.wltraffic.ui.adapter.SubItemLabelAdapter;
import com.rainwood.tools.view.SmartTextView;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shearson
 * @Time: 2019/12/21 21:37
 * @Desc: 项目详情
 */
public final class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {


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
    @ViewById(R.id.lv_item_paragraph_content)
    private MeasureListView paragraghListView;


    @Override
    protected void initView() {
        backBtn.setOnClickListener(this);
        titleTv.setText("项目详情");

        // 初始化数据
        itemName.setText(mProjectInfo.getTitle());
        itemLabel.setText(mProjectInfo.getLabel());
        itemTime.setText(mProjectInfo.getTime());

        // 子项目标签
        SubItemLabelAdapter itemLabelAdapter = new SubItemLabelAdapter(this, mSubListLabel);
        itemContentTitle.setAdapter(itemLabelAdapter);
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
                        break;
                    case 6:             // 农民工工资
                        break;
                    case 7:             // 招投标
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

    /*
    模拟数据
     */
    // 项目
    private ProjectInfoBean mProjectInfo;
    // 项目的子内容
    private List<SubItemLabelBean> mSubListLabel;
    private String[] mTitles = {"甲方公司", "代建公司", "总投资", "批准概算投资", "建安投资", "业主管理费"};
    private String[] mContent = {"重庆中翰建设集团有限公司", "重庆跨越建设有限公司", "3000万元", "3000万元", "500万元", "50万元"};

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
        mProjectInfo = new ProjectInfoBean();
        mProjectInfo.setTitle("丰裕粮油安置房建设工程泉城花都E区安置工程丰裕粮油安置房");
        mProjectInfo.setLabel("施工图设计阶段-财政评审");
        mProjectInfo.setTime("2018年");

        mSubListLabel = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            SubItemLabelBean mSubItemLabel = new SubItemLabelBean();
            mSubItemLabel.setTitle(mTitles[i]);
            mSubItemLabel.setContent(mContent[i]);

            mSubListLabel.add(mSubItemLabel);
        }

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
                break;
        }
    }
}
