package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.LaborBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.LaborAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:27
 * @Desc: 劳资管理
 */
public final class LaborActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_labor;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_labor_list)
    private MeasureListView laborList;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("劳资人员管理");
    }

    /*
    模拟数据
     */
    private List<LaborBean> mList;

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();
        // 加载数据
        new Thread(() -> RequestPost.getItemFarmerFiveData(Contants.ITEM_ID, LaborActivity.this)).start();

    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            openActivity(FarmersSalaryManagerActivity.class);
            finish();
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            mList = JsonParser.parseJSONArray(LaborBean.class, body.get("data"));
            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1932;
            mHandler.sendMessage(msg);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0x1932:
                    LaborAdapter laborAdapter = new LaborAdapter(LaborActivity.this, mList);
                    laborList.setAdapter(laborAdapter);
                    break;
            }
        }
    };
}
