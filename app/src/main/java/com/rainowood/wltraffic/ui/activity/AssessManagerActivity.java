package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.AssessDeductionBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.ui.adapter.AssessAttachmentAdapter;
import com.rainowood.wltraffic.ui.adapter.AssessDeductionAdapter;
import com.rainowood.wltraffic.utils.DateTimeUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/26 14:58
 * @Desc: 考核管理
 */
public class AssessManagerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_assess_manager;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.ll_assess_title_one)
    private LinearLayout titleOneLL;
    @ViewById(R.id.tv_assess_title_one)
    private TextView titleOneTV;
    @ViewById(R.id.v_title_one)
    private View lineOne;
    @ViewById(R.id.ll_assess_title_two)
    private LinearLayout titleTwoLL;
    @ViewById(R.id.tv_assess_title_two)
    private TextView titleTwoTV;
    @ViewById(R.id.v_title_two)
    private View lineTwo;

    @ViewById(R.id.lv_assess_list)
    private MeasureListView assessList;

    @ViewById(R.id.ll_year_month)
    private LinearLayout ll_year_month;
    @ViewById(R.id.tv_year)
    private TextView year;
    @ViewById(R.id.tv_month)
    private TextView month;
    @ViewById(R.id.iv_before)
    private ImageView before;
    @ViewById(R.id.iv_next)
    private ImageView next;
    @ViewById(R.id.lv_assess_deduction)
    private ListView assessDeduction;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("考核管理");

        titleOneLL.setOnClickListener(this);
        titleTwoLL.setOnClickListener(this);
        before.setOnClickListener(this);
        next.setOnClickListener(this);

        // 考核细则
        titleOneTV.setText("考核细则");
        AssessAttachmentAdapter attachmentAdapter = new AssessAttachmentAdapter(getActivity(), mList);
        assessList.setAdapter(attachmentAdapter);

        // 扣分明细
        titleTwoTV.setText("扣分明细");
        lineTwo.setVisibility(View.GONE);
        ll_year_month.setVisibility(View.GONE);

    }

    /*
    模拟数据
     */
    private List<SubItemLabelBean> mList;

    private String[] mTitles = {"考核附件考核附件.doc", "考核附件考核附件.doc", "考核附件考核附件.doc", "考核附件考核附件.doc", "考核附件考核附件.doc"};
    private String[] mLabels = {"2019.12.19 13:09:00更新", "2019.12.19 13:09:00更新", "2019.12.19 13:09:00更新", "2019.12.19 13:09:00更新", "2019.12.19 13:09:00更新"};

    // 扣分明细
    private List<AssessDeductionBean> deductionLists;

    private String[] mTitles1 = {"工程部", "开发部", "研发部", "人事部", "技术部"};
    private String[] mScore = {"-5", "-10", "-7", "-5", "-5"};
    private String[] mLabels1 = {"工程进度延期，未能按时完成工程", "遗失公司重要文件", "工程进度延期，未能按时完成工程",
                    "工程进度延期，未能按时完成工程", "遗失公司重要文件"};


    @Override
    protected void initData() {
        super.initData();

        // 初始化模拟 考核细则
        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            SubItemLabelBean label = new SubItemLabelBean();
            label.setTitle(mTitles[i]);
            label.setContent(mLabels[i]);

            mList.add(label);
        }

        // 初始化模拟 扣分明细
        deductionLists = new ArrayList<>();
        for (int i = 0; i < mTitles1.length; i++) {
            AssessDeductionBean deduction = new AssessDeductionBean();
            deduction.setTitle(mTitles1[i]);
            deduction.setScore(mScore[i]);
            deduction.setLabel(mLabels1[i]);

            deductionLists.add(deduction);
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        // 获取年月String
        String tempYearStr = year.getText().toString().trim();
        String tempMonthStr = month.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(ProjectDetailActivity.class);
                break;
            case R.id.ll_assess_title_one:          // 考核细则
                lineTwo.setVisibility(View.GONE);
                ll_year_month.setVisibility(View.GONE);
                // 加载数据
                lineOne.setVisibility(View.VISIBLE);
                assessList.setVisibility(View.VISIBLE);

                AssessAttachmentAdapter attachmentAdapter = new AssessAttachmentAdapter(getActivity(), mList);
                assessList.setAdapter(attachmentAdapter);

                break;
            case R.id.ll_assess_title_two:              // 扣分明细
                lineOne.setVisibility(View.GONE);
                assessList.setVisibility(View.GONE);
                // 加载数据
                lineTwo.setVisibility(View.VISIBLE);
                ll_year_month.setVisibility(View.VISIBLE);
                // 获取当前时间
                year.setText(String.valueOf(getNowTime.getYear()));
                month.setText(String.valueOf(getNowTime.getMonth()));
                // 明细数据列表
                AssessDeductionAdapter deductionAdapter = new AssessDeductionAdapter(this, deductionLists);
                assessDeduction.setAdapter(deductionAdapter);
                deductionAdapter.setItemListener(new AssessDeductionAdapter.OnItemListener() {
                    @Override
                    public void OnItemClick(int position) {
                        toast("点击了：" + position);
                        openActivity(DeductionDetailActivity.class);
                    }
                });

                break;
            case R.id.iv_before:            // 上个月
                if ("1".equals(tempMonthStr)){
                    month.setText("12");
                    year.setText(String.valueOf(Integer.parseInt(tempYearStr) - 1));
                }else {
                    month.setText(String.valueOf(Integer.parseInt(tempMonthStr) - 1));
                }
                toast("年月：" + year.getText().toString().trim() + "年" + month.getText().toString().trim() + "月");
                break;
            case R.id.iv_next:              // 下个月
                if (!"12".equals(tempMonthStr)){
                    month.setText(String.valueOf(Integer.parseInt(tempMonthStr) + 1));
                }else {
                    month.setText("1");
                    year.setText(String.valueOf(Integer.parseInt(tempYearStr) + 1));
                }
                toast("年月：" + year.getText().toString().trim() + "年" + month.getText().toString().trim() + "月");
                break;
        }
    }

    private static class getNowTime{
        private static int getYear(){
            return DateTimeUtils.getNowYear();
        }
        private static int getMonth(){
            return DateTimeUtils.getNowMonth();
        }
    }
}
