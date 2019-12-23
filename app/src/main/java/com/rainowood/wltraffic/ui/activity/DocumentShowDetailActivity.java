package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.ParagraphListBean;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: a797s
 * @Date: 2019/12/23 16:42
 * @Desc: 文档详情展示
 */
public class DocumentShowDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_document_show;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView title;
    @ViewById(R.id.tv_document)
    private TextView documentTv;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        // 获取传递过来的数据
        ParagraphListBean document = (ParagraphListBean) getIntent().getSerializableExtra("document");
        title.setText(document.getTitle());
        documentTv.setText(document.getContent());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            finish();
        }
    }
}
