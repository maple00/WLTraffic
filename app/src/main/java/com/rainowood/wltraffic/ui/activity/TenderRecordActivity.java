package com.rainowood.wltraffic.ui.activity;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.adapter.AttachmentListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.Arrays;

/**
 * @Author: a797s
 * @Date: 2019/12/30 9:38
 * @Desc: 招投标备案详情
 */
public class TenderRecordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender_record;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_record_time_a)
    private TextView timeA;
    @ViewById(R.id.tv_record_time_b)
    private TextView timeB;
    @ViewById(R.id.tv_record_content)
    private TextView content;
    @ViewById(R.id.lv_list)
    private MeasureListView mList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("备案");

        timeA.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "政务备案时间： &nbsp;" + "</font>"
                + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + "2019.12.19" + "</font>"));
        timeB.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "交通局备案时间： &nbsp;" + "</font>"
                + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + "2019.12.19" + "</font>"));

        content.setText("施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明，施工方案中要体现如何施工。");

        // 附件列表
        AttachmentListAdapter listAdapter = new AttachmentListAdapter(this, Arrays.asList(attachList));
        mList.setAdapter(listAdapter);

        listAdapter.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
            @Override
            public void onAttachDownloadClick(int position) {
                toast("下载：" + position);
            }

            @Override
            public void onAttachPreviewClick(int position) {
                toast("预览：" + position);
            }
        });
    }

    /*
    模拟数据
     */
    private String[] attachList = {"白马坪项目修改意见.doc", "白马坪项目见.doc", "白马坪=修改意见.doc", "白马坪项=改意见.doc"};

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;

        }
    }
}
