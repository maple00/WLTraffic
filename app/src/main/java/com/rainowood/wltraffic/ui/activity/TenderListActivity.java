package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubQuestionsAndBareBean;
import com.rainowood.wltraffic.ui.adapter.TenderListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author: a797s
 * @Date: 2019/12/30 10:36
 * @Desc: 质疑答疑、补漏列表
 */
public class TenderListActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tender_list;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_list)
    private MeasureListView list;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        String key = (String) getIntent().getCharSequenceExtra("key");
        final SubQuestionsAndBareBean question = (SubQuestionsAndBareBean) getIntent().getSerializableExtra("question");
        TenderListAdapter listAdapter;
        if ("question".equals(key)){                // 质疑答疑
            pageTitle.setText("质疑答疑");
            listAdapter = new TenderListAdapter(this, question.getOne());
            list.setAdapter(listAdapter);
            listAdapter.setClickListener(new TenderListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(TenderListActivity.this, TenderListDetailActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("key", "question");
                    bundle.putSerializable("question", question.getOne().get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        if ("bare".equals(key)){
            pageTitle.setText("补漏");
            listAdapter = new TenderListAdapter(this, question.getTwo());
            list.setAdapter(listAdapter);
            listAdapter.setClickListener(new TenderListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(TenderListActivity.this, TenderListDetailActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("key", "bare");
                    bundle.putSerializable("question", question.getTwo().get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
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
