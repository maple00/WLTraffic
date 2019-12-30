package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.adapter.TenderListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

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
        TenderListAdapter listAdapter;
        if ("question".equals(key)){                // 质疑答疑
            pageTitle.setText("质疑答疑");
            listAdapter = new TenderListAdapter(this, Arrays.asList(mLists));
            list.setAdapter(listAdapter);
            listAdapter.setClickListener(new TenderListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(TenderListActivity.this, TenderListDetailActivity.class);
                    intent.putExtra("key", "question");
                    startActivity(intent);
                }
            });
        }
        if ("bare".equals(key)){
            pageTitle.setText("补漏");
            listAdapter = new TenderListAdapter(this, Arrays.asList(mLists1));
            list.setAdapter(listAdapter);
            listAdapter.setClickListener(new TenderListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(TenderListActivity.this, TenderListDetailActivity.class);
                    intent.putExtra("key", "bare");
                    startActivity(intent);
                }
            });
        }
    }

    /*
    模拟数据
     */
    private String[] mLists = {"施工动迁任务较重，造成城市环境污染的情况应...", "工程成本控制不到位", "施工现场监管不到位", "公路工程建设中地下管线比较复杂", "施工动迁任务较重，造成城市环境污染的情况应...",
                "工程成本控制不到位","施工现场监管不到位", "公路工程建设中地下管线比较复杂"};

    private String[] mLists1 = {"评标办法前附表2.2.3条评标偏差率计算公式修...", "投标人须知前附表10.2条中“（一）设计费：本...", "投标人须知前附表10.3条中设计费支付调整为是...",
            "安装工程及竣工试验一切险的投保方及对投保的...", "评标办法前附表2.2.3条评标偏差率计算公式修...", "投标人须知前附表10.2条中“（一）设计费：本...",
            "投标人须知前附表10.3条中设计费支付调整为是...", "安装工程及竣工试验一切险的投保方及对投保的..."};

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
