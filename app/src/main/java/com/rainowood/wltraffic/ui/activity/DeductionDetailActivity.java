package com.rainowood.wltraffic.ui.activity;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainwood.tools.viewinject.ViewById;

import java.text.DecimalFormat;

/**
 * @Author: shearson
 * @Time: 2019/12/26 20:11
 * @Desc: 扣分项详情
 */
public class DeductionDetailActivity extends BaseActivity implements View.OnClickListener {

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

    /*
    模拟数据
     */
    private String[] mContents = {"工程部", "-5", "遗失公司重要文件", "工程进度延期，未能按时完成工程工程进度延期未能按时完成工程工程进度延期未能按时完成工程工程进度延期。", "2019.12.18  13:15:00"};

    @Override
    protected void initData() {
        super.initData();
        // 获取数据
        name.setText(mContents[0]);
        scoreTV.setText("-5");
        scoreTV.setText(Html.fromHtml(""));

        titleContent.setText(mContents[2]);
        labelContent.setText(mContents[3]);
        timeContent.setText(mContents[4]);
    }

    @Override
    public void onClick(View v) {

    }
}
