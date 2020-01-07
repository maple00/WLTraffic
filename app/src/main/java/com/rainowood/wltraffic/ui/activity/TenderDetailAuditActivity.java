package com.rainowood.wltraffic.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubCompanyAudit;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

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
        SubCompanyAudit value = (SubCompanyAudit) getIntent().getSerializableExtra("value");
        String key = getIntent().getStringExtra("key");
        if ("audit_company".equals(key)){
            type.setText("公司审核");
            pageTitle.setText("公司审核");
        }else if ("audit_export".equals(key)){
            type.setText("专家审核");
            pageTitle.setText("专家审核");
        }

        Log.e(":sxs", "value: " + value);
        if (value != null){
            name.setText(value.getPeopleSix());
            time.setText(value.getTimeOneSix());
            content.setText(value.getIdeaSix());
            ItemAttachListAdapter adapter = new ItemAttachListAdapter(this, value.getIdeaSixFile());
            mAttachList.setAdapter(adapter);
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
