package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.rainowood.wltraffic.domain.SubChangeBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ChangeManagerAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/27 9:46
 * @Desc: 变更管理
 */
public final class ChangeActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_count_change_money)
    private TextView countChangeMoney;
    @ViewById(R.id.tv_count_money)
    private TextView countMoney;
    @ViewById(R.id.lv_change_list)
    private MeasureListView changeList;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("变更管理");
    }

    /*
    模拟数据
     */
    private List<String> moneyChange = new ArrayList<>();
    private List<SubChangeBean> mList;

    @Override
    protected void initData() {
        super.initData();
        waitDialog();
        dialog.showDialog();

        new Thread(() -> RequestPost.getItemChangeManagerData(Contants.ITEM_ID, ChangeActivity.this)).start();
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
            Map<String, String> data = JsonParser.parseJSONObject(body.get("data"));
            String changeMoney = data.get("changeMoney");                       // 累计变更金额
            String aboutMoney = data.get("aboutMoney");                         // 超概金额
            moneyChange.add(changeMoney);
            moneyChange.add(aboutMoney);
            // 内容列表
            mList = JsonParser.parseJSONArray(SubChangeBean.class, data.get("changeManager"));
            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1727;
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
                case 0x1727:
                    countChangeMoney.setText(moneyChange.get(0));
                    countMoney.setText(moneyChange.get(1));
                    // 内容列表
                    ChangeManagerAdapter managerAdapter = new ChangeManagerAdapter(ChangeActivity.this, mList);
                    changeList.setAdapter(managerAdapter);

                    managerAdapter.setClickListener(position -> {
                        Intent intent = new Intent(ChangeActivity.this, ChangeManagerDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("content", mList.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    });
                    break;
            }
        }
    };

}
