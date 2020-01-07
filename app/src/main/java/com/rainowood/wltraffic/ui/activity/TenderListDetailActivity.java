package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubQuestionBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

/**
 * @Author: a797s
 * @Date: 2019/12/30 11:03
 * @Desc: 质疑答疑、补漏列表详情
 */
public final class TenderListDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender_list_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_content_title)
    private TextView title;
    @ViewById(R.id.lv_question)
    private MeasureListView question;
    @ViewById(R.id.lv_answer)
    private MeasureListView answer;
    @ViewById(R.id.tv_content)
    private TextView content;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);

        String key = (String) getIntent().getCharSequenceExtra("key");
        // 数据详情
        SubQuestionBean question = (SubQuestionBean) getIntent().getSerializableExtra("question");

        if ("question".equals(key)){        // 质疑答疑
            pageTitle.setText("质疑答疑详情");
            title.setText(question.getProblem());
            content.setText(question.getText());
            // 质疑
            ItemAttachListAdapter adapterQ = new ItemAttachListAdapter(this, question.getProblemFile());
            this.question.setAdapter(adapterQ);

            ItemAttachListAdapter adapterA = new ItemAttachListAdapter(this, question.getAnswerFile());
            answer.setAdapter(adapterA);
        }

        if ("bare".equals(key)){            // 补漏列表
            pageTitle.setText("补遗详情");
            title.setText(question.getProblem());
            content.setText(question.getText());
            // 质疑
            ItemAttachListAdapter adapterQ = new ItemAttachListAdapter(this, question.getProblemFile());
            this.question.setAdapter(adapterQ);
            // 答复
            ItemAttachListAdapter adapterA = new ItemAttachListAdapter(this, question.getAnswerFile());
            answer.setAdapter(adapterA);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
