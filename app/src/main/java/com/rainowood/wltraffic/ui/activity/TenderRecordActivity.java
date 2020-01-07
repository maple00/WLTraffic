package com.rainowood.wltraffic.ui.activity;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubRecordsBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2019/12/30 9:38
 * @Desc: 招投标备案详情
 */
public final class TenderRecordActivity extends BaseActivity implements View.OnClickListener {

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

        SubRecordsBean value = (SubRecordsBean) getIntent().getSerializableExtra("value");

        if (value != null){
            timeA.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "政务备案时间： &nbsp;" + "</font>"
                    + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + value.getTimeOneSix() + "</font>"));
            timeB.setText(Html.fromHtml("<font color=" + getResources().getColor(R.color.colorLabel) + " font-size=\"13dp\">" + "交通局备案时间： &nbsp;" + "</font>"
                    + "<font color=" + getResources().getColor(R.color.textColor) + " font-size=\"13dp\">" + value.getTimeTwoSix() + "</font>"));
            content.setText(value.getIdeaSix());

            // 附件列表
            ItemAttachListAdapter listAdapter = new ItemAttachListAdapter(this, value.getIdeaSixFile());
            mList.setAdapter(listAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(TenderActivity.class);
                finish();
                break;

        }
    }
}
