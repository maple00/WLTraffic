package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.AttachBean;
import com.rainowood.wltraffic.domain.RectificationBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ImageAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureGridView;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/25 9:29
 * @Desc: 质量安全详情
 */
public final class RectificationActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rectification_main;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.tv_rectification_content)
    private TextView rectificationContent;
    @ViewById(R.id.lv_title_describe)
    private MeasureListView titleDescribe;

    /*
     整改情况
     */
    // 已整改
    @ViewById(R.id.tv_rectified_content)
    private TextView rectifiedContent;
    @ViewById(R.id.ll_rectified_area)
    private LinearLayout rectifiedArea;     // 整改点击区域
    @ViewById(R.id.lv_top_describe)
    private MeasureListView topDescribe;

    // 整改中
    @ViewById(R.id.tv_rectifying_content)
    private TextView rectifyingContent;
    @ViewById(R.id.ll_rectifying_area)
    private LinearLayout rectifityingArea;      // 整改中点击区域
    @ViewById(R.id.lv_rectifying_list)
    private MeasureListView rectifyingList;

    // 未整改
    @ViewById(R.id.tv_unrectify_content)
    private TextView unRectifyContent;
    @ViewById(R.id.ll_unrectify_area)
    private LinearLayout unRectifyArea;         // 点击的区域
    @ViewById(R.id.lv_unrectify_list)
    private MeasureListView unRectifyList;

    private DialogUtils dialog;
    private RectificationBean rectification;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("质量安全检查及整改情况");
        // 已整改
        rectifiedArea.setOnClickListener(this);         // 点击的区域
        // 整改中
        rectifityingArea.setOnClickListener(this);
        // 未整改
        unRectifyArea.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();
        // 请求数据
        final String id = getIntent().getStringExtra("id");
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestPost.getItemQSDetailData(id, RectificationActivity.this);
            }
        }).start();
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(QualitySafetyActivity.class);
                finish();
                break;
            case R.id.ll_rectified_area:                // 查看详情--已整改
                Intent intent = new Intent(this, RectificationDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "已整改");
                bundle.putSerializable("value", rectification);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.ll_rectifying_area:
                Intent rectifyingIntent = new Intent(this, RectificationDetailActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("key", "整改中");
                bundle1.putSerializable("value", rectification);
                rectifyingIntent.putExtras(bundle1);
                startActivity(rectifyingIntent);
                break;
            case R.id.ll_unrectify_area:
                Intent unRectifyIntent = new Intent(this, RectificationDetailActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("key", "未整改");
                bundle2.putSerializable("value", rectification);
                unRectifyIntent.putExtras(bundle2);
                startActivity(unRectifyIntent);
                break;
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            Log.e("sxs", "data: " + result.body());
            rectification = JsonParser.parseJSONObject(RectificationBean.class, body.get("data"));
            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1048;
            mHandler.sendMessage(msg);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x1048:
                    // 头部描述
                    rectificationContent.setText(rectification.getCaseDescriptio());
                    ItemAttachListAdapter titleAdapter = new ItemAttachListAdapter(RectificationActivity.this, rectification.getCaseDescriptioFile());
                    titleDescribe.setAdapter(titleAdapter);
                    /*
                     整改情况
                     */
                    // 已整改
                    rectifiedContent.setText(rectification.getModifyHave());
                    ItemAttachListAdapter modifyHaveAdapter = new ItemAttachListAdapter(RectificationActivity.this, rectification.getHaveFile());
                    topDescribe.setAdapter(modifyHaveAdapter);
                    // 整改中
                    rectifyingContent.setText(rectification.getModifyIn());
                    ItemAttachListAdapter modifyInAdapter = new ItemAttachListAdapter(RectificationActivity.this, rectification.getInFile());
                    rectifyingList.setAdapter(modifyInAdapter);
                    // 已整改
                    unRectifyContent.setText(rectification.getModifyNot());
                    ItemAttachListAdapter modifyNotAdapter = new ItemAttachListAdapter(RectificationActivity.this, rectification.getNotFile());
                    unRectifyList.setAdapter(modifyNotAdapter);
                    break;
            }
        }
    };
}
