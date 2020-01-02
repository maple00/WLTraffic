package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.adapter.QualitySafeAdapter;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 9:50
 * @Desc: 通报
 */
public class NotifyModuleActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_notify;
    }

    @ViewById(R.id.iv_back)
    private ImageView back;
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

    @ViewById(R.id.lv_notify_content)
    private MeasureListView notifyContent;

    @ViewById(R.id.ll_notify_download)
    private LinearLayout download;
    @ViewById(R.id.ll_preview)
    private LinearLayout preview;


    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(this, title, background);
        back.setOnClickListener(this);
        download.setOnClickListener(this);
        preview.setOnClickListener(this);
        pageTitle.setText("通报");

        status.setText("已设置维权公示牌");
        word.setText("维权公示文档附件维权公....doc");

        QualitySafeAdapter safeAdapter = new QualitySafeAdapter(this, mSafeList);
        notifyContent.setAdapter(safeAdapter);

        safeAdapter.setContentOnClick(new QualitySafeAdapter.IContentOnClick() {
            @Override
            public void contentClick(int position) {
                //toast("点击了：" + position);
                openActivity(RectificationDetailActivity.class);
            }
        });

    }

    /*
    模拟数据
     */
    private List<String> mSafeList;
    private String[] mContents = {"未整改业务操作中，由于经办人员业务知识欠缺风险意识差，未严格执行规章制度，且个别被...计单位",
            "整改中业务操作中，由于经办人员业务知识欠缺风险意识差，未严格执行规章制度，且个别被...",
            "已整改已经整改完毕"};

    @Override
    protected void initData() {
        super.initData();

        mSafeList = new ArrayList<>();
        mSafeList.addAll(Arrays.asList(mContents));
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
