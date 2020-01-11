package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.PayManagerBean;
import com.rainowood.wltraffic.domain.SubPayManagerBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.PayManagerAdapter;
import com.rainowood.wltraffic.ui.adapter.PayManagerContentAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/25 16:03
 * @Desc: 支付管理
 */
public final class PayManagerActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_manager;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
    @ViewById(R.id.iv_background)
    private ImageView background;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.tv_transport)
    private TextView transport;     // 交通局
    @ViewById(R.id.tv_owner_unit)
    private TextView tvOU;          // 业主单位
    @ViewById(R.id.tv_total_money_int)
    private TextView tvTotalMoneyInt;       // 总金额，整数部分
    @ViewById(R.id.tv_total_money_float)
    private TextView tvTotalMoneyFloat;     // 总金额，小数部分

    @ViewById(R.id.rlc_card_content)
    private RecyclerView cardContent;

    private DialogUtils dialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 状态栏沉浸
        ImmersionUtil.ImageImmers(this, title, background);
        // 状态栏字体白色
        StatusBarUtil.setStatusBarFontIconDark(this, getResources().getColor(R.color.white), false);

        transport.setOnClickListener(this);
        tvOU.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        transport.setText("交通局");
        tvOU.setText("业主单位");
    }

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();

        new Thread(() -> RequestPost.getItemPayManagerData(Contants.ITEM_ID, PayManagerActivity.this)).start();
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    /*
    模拟数据
    */
    PayManagerBean payLeftBean;                 // title
    PayManagerBean payRightBean;                 // title
    private List<SubPayManagerBean> mLeftList;  // 左边支付列表
    private List<SubPayManagerBean> mRightList;  // 左边支付列表

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                openActivity(ProjectDetailActivity.class);
                finish();
                break;
            case R.id.tv_transport:         // 交通局 shap_radio_transport
                transport.setBackgroundResource(R.drawable.shap_radio_white);
                tvOU.setBackgroundResource(R.drawable.shap_radio_transport);
                transport.setTextColor(getResources().getColor(R.color.colorBlue1));
                tvOU.setTextColor(getResources().getColor(R.color.white));

                showTransportInfo();
                break;
            case R.id.tv_owner_unit:        // 业主单位
                transport.setBackgroundResource(R.drawable.shap_radio_transport_right);
                tvOU.setBackgroundResource(R.drawable.shap_radio_white_right);
                transport.setTextColor(getResources().getColor(R.color.white));
                tvOU.setTextColor(getResources().getColor(R.color.colorBlue1));
                showOwerUnitInfo();
                break;
        }
    }

    /**
     * 展示交通局数据
     */
    private void showTransportInfo() {
        /*
        初始化交通局数据
         */
        tvTotalMoneyInt.setText(payLeftBean.getTotalMoneyInt());
        tvTotalMoneyFloat.setText(payLeftBean.getTotalMoneyFloat());
        // 交通局
        transport.setFocusable(true);
        tvOU.setFocusable(false);
        getData2Show("transport");
    }

    /**
     * 展示业主单位数据
     */
    private void showOwerUnitInfo() {
        /*
        初始化业主单位数据
         */
        tvTotalMoneyInt.setText(payRightBean.getTotalMoneyInt());
        tvTotalMoneyFloat.setText(payRightBean.getTotalMoneyFloat());
        // 业主单位
        transport.setFocusable(false);
        tvOU.setFocusable(true);
        getData2Show("ou");
    }

    /**
     * 获取数据展示
     */
    private void getData2Show(final String type) {
        PayManagerAdapter adapter = new PayManagerAdapter(this);
        LinearLayoutManager managerVertical = new LinearLayoutManager(this);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置item之间的间距
//        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
//        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 10);//下间距
//        cardContent.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        cardContent.setLayoutManager(managerVertical);
        cardContent.setHasFixedSize(true);
        cardContent.setAdapter(adapter);
        cardContent.setNestedScrollingEnabled(false);
        if ("transport".equals(type)) {
            adapter.setmList(mLeftList);
        }
        if ("ou".equals(type)) {
            adapter.setmList(mRightList);
        }
        /**
         * 子项的点击事件
         */
        adapter.setClickListener((parenPosition, position) -> {
            // 判断焦点在哪
            transport.post(() -> {
                Intent intent = new Intent(PayManagerActivity.this, PayDetailActivity.class);
                Bundle bundle = new Bundle();
                if (transport.isFocusable()) {   // 交通局
                    intent.putExtra("key", "transport");
                    bundle.putSerializable("value", mLeftList.get(parenPosition).getTeamChildArr().get(position));
                } else {                 // 业主单位
                    intent.putExtra("key", "ou");
                    bundle.putSerializable("value", mRightList.get(parenPosition).getTeamChildArr().get(position));
                }
                intent.putExtras(bundle);
                startActivity(intent);
            });
        });

    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            Map<String, String> data = JsonParser.parseJSONObject(body.get("data"));
            // 交通局
            String leftMoney = data.get("leftMoney");
            String[] leftTotal = leftMoney.split("\\.");
            payLeftBean = new PayManagerBean();
            payLeftBean.setTotalMoneyInt(leftTotal[0]);
            payLeftBean.setTotalMoneyFloat("." + leftTotal[1]);
            mLeftList = JsonParser.parseJSONArray(SubPayManagerBean.class, data.get("leftArr"));

            // 业主单位
            String money = data.get("rightMoney");
            String[] split = money.split("\\.");
            payRightBean = new PayManagerBean();
            payRightBean.setTotalMoneyInt(split[0]);
            payRightBean.setTotalMoneyFloat("." + split[1]);
            mRightList = JsonParser.parseJSONArray(SubPayManagerBean.class, data.get("rightArr"));

            Log.e("sxs", "data: " + payLeftBean.toString() + ", right: " + payRightBean.toString());

            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1059;
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
                case 0x1059:
                    // 默认显示交通局
                    showTransportInfo();
                    break;
            }
        }
    };
}
