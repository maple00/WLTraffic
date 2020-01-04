package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:08
 * @Desc: 实名制详情
 */
public class RealNameDetailActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_real_name_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_atttach_word)
    private MeasureListView attachWord;
    @ViewById(R.id.tv_real_detail_des)
    private TextView detailDes;
    @ViewById(R.id.tv_update_time)
    private TextView updateTime;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("附件详情");

        detailDes.setText(wordDes);
        updateTime.setText(updateTimeStr);

        // 附件
        ItemAttachListAdapter wordListAdapter = new ItemAttachListAdapter(this, mList);
        attachWord.setAdapter(wordListAdapter);

    }

    /*
    模拟数据
     */
    private String wordDes = "备注描述文字内容备注描述文字备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容备注描述文字内容内容备注描述文字内容备注描述文字内容备注描述文字内容";
    private String updateTimeStr = "2019.12.28 16:50:00更新";
    // 合同附件
    private List<AttachBean> mList;
    private String[] mTitles = {"合同附件"};
    private String[] mLabels = {"XXXXXXXX附件.doc"};

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            AttachBean word = new AttachBean();
            word.setName(mTitles[i]);

            mList.add(word);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
