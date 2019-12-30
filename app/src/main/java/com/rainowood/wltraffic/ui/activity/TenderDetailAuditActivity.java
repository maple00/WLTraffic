package com.rainowood.wltraffic.ui.activity;

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
 * @Date: 2019/12/28 17:34
 * @Desc: 招投标详情页
 */
public class TenderDetailAuditActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_name)
    private TextView name;
    @ViewById(R.id.tv_type)
    private TextView type;
    @ViewById(R.id.tv_time)
    private TextView time;
    @ViewById(R.id.tv_content)
    private TextView content;

    @ViewById(R.id.lv_attach_list)
    private MeasureListView mAttachList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);

        String key = (String) getIntent().getCharSequenceExtra("key");
        if ("company_audit".equals(key)){
            pageTitle.setText("公司审核");
            name.setText(info[0]);
            type.setText(info[1]);
            time.setText(info[2]);
            content.setText(info[3]);
            AttachmentListAdapter adapter = new AttachmentListAdapter(this, Arrays.asList(attachList));
            mAttachList.setAdapter(adapter);
            adapter.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
                @Override
                public void onAttachDownloadClick(int position) {
                    toast("下载：" + position);
                }
                @Override
                public void onAttachPreviewClick(int position) {
                    toast("预览: " + position);
                }
            });
        }

        if ("experts_audit".equals(key)){
            pageTitle.setText("专家审核");
            name.setText(experts[0]);
            type.setText(experts[1]);
            time.setText(experts[2]);
            content.setText(experts[3]);

            AttachmentListAdapter adapter = new AttachmentListAdapter(this, Arrays.asList(attachList));
            mAttachList.setAdapter(adapter);
            adapter.setAttachListener(new AttachmentListAdapter.OnAttachListener() {
                @Override
                public void onAttachDownloadClick(int position) {
                    toast("下载：" + position);
                }
                @Override
                public void onAttachPreviewClick(int position) {
                    toast("预览: " + position);
                }
            });
        }
    }

    /*
    模拟数据
     */
    private String[] info = {"吴建新", "审核人", "2019.12.19", "施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明，施工方案中要体现如何施工。"};
    private String[] attachList = {"白马坪项目修改意见.doc", "白马坪项目修改意见.doc"};
    // 专家审核
    private String[] experts = {"罗钟石", "审核人", "2019.12.19", "施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明。施工方案中要体现如何施工，不只是简单的包括施工施工方案中要体现如何施工，不只是简单的包括施工内容，还要写清施工顺序，进度控制，如何分段施工。具体应配图进行简要说明，施工方案中要体现如何施工。"};

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
