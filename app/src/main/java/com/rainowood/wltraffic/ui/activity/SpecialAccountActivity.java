package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SpecialAccountBean;
import com.rainowood.wltraffic.ui.adapter.SpecialAccountAdapter;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:19
 * @Desc: 专户制
 */
public class SpecialAccountActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_no_account;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.iv_background)
    private ImageView background;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.tv_status)
    private TextView status;

    @ViewById(R.id.tv_word_title)
    private TextView word;
    @ViewById(R.id.ll_notify_download)
    private LinearLayout download;
    @ViewById(R.id.ll_preview)
    private LinearLayout preview;
    @ViewById(R.id.lv_list)
    private MeasureListView list;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(this, title, background);

        ivBack.setOnClickListener(this);
        download.setOnClickListener(this);
        preview.setOnClickListener(this);
        pageTitle.setText("专户制度");
        status.setText("已签订三方协议");
        word.setText("三方协议具体细则.doc");

        SpecialAccountAdapter accountAdapter = new SpecialAccountAdapter(this, mList);
        list.setAdapter(accountAdapter);

    }

    /*
    模拟数据
     */
    private List<SpecialAccountBean> mList;

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SpecialAccountBean specialAccount = new SpecialAccountBean();
            specialAccount.setMoney("￥ 560.00");
            specialAccount.setTime("2019.12.18 17:19:00");
            specialAccount.setNote("这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注");
            mList.add(specialAccount);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_document:
                toast("下载");
                break;
            case R.id.ll_preview:
                toast("预览");
                break;
        }
    }
}
