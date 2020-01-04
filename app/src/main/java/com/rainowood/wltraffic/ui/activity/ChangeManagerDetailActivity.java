package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.domain.SubChangeBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.domain.SubItemListContentBean;
import com.rainowood.wltraffic.ui.adapter.ChangeDetailAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemDetailWordAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 10:44
 * @Desc: 变更管理详情
 */
public class ChangeManagerDetailActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chage_manager_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_change_list)          // 变更信息列表
    private MeasureListView chageList;
    @ViewById(R.id.lv_word_list)            // 文档信息列表
    private MeasureListView wordList;
    @ViewById(R.id.tv_update_time)
    private TextView updateTime;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("变更详情");

        // 变更信息列表
        ChangeDetailAdapter detailAdapter = new ChangeDetailAdapter(this, changeContentList);
        chageList.setAdapter(detailAdapter);

        // 文档信息列表
        ItemDetailWordAdapter wordListAdapter = new ItemDetailWordAdapter(this, wordsList);
        wordList.setAdapter(wordListAdapter);

        // 更新时间
        updateTime.setText("2019.12.18 13:58:00更新");
    }

    /*
    模拟数据
     */
    // 变更数据
    private List<SubItemLabelBean> changeContentList;
    private String[] mTitles = {"变更内容", "变更金额", "变更依据"};
    // 文档列表
    private List<SubItemListContentBean> wordsList;
    private String[] wTitles = {"现场会议纪要", "业主会议纪要", "交通局会议纪要"};
    private String[] wContents = {"关于项目在实施过程中遇到的....doc", "关于项目在实施过程中遇到的....doc", "关于项目在实施过程中遇到的....doc"};

    @Override
    protected void initData() {
        super.initData();

        SubChangeBean content = (SubChangeBean) getIntent().getSerializableExtra("content");

        changeContentList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            SubItemLabelBean label = new SubItemLabelBean();
            // 变更内容
            label.setTitle(mTitles[i]);
            label.setContent(content.getChangeMatter());
            // 变更金额
            if (!TextUtils.isEmpty(content.getChangeMoney())) {
                label.setTitle(mTitles[i]);
                label.setContent(content.getChangeMoney());
            }
            // 变更依据
            if (!TextUtils.isEmpty(content.getChangeBasis())) {
                label.setTitle(mTitles[i]);
                label.setContent(content.getChangeMoney());
            }
            changeContentList.add(label);
        }

        // 文档列表
        wordsList = new ArrayList<>();
        for (int i = 0; i < wTitles.length; i++) {
            SubItemListContentBean word = new SubItemListContentBean();
            word.setTitle(wTitles[i]);
            List<AttachBean> attachList = new ArrayList<>();
            AttachBean attachment = new AttachBean();
            attachment.setName(wContents[i]);
            attachList.add(attachment);
            word.setmList(attachList);
//            word.setType(wContents[i]);
            wordsList.add(word);
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
