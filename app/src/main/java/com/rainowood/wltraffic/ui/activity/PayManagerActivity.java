package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.PayManagerBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.domain.SubPayManagerBean;
import com.rainowood.wltraffic.ui.adapter.PayManagerAdapter;
import com.rainowood.wltraffic.ui.adapter.PayManagerContentAdapter;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;
import com.rainowood.wltraffic.utils.StatusBarUtils;
import com.rainwood.tools.viewinject.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/25 16:03
 * @Desc: 支付管理
 */
public class PayManagerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_manager;
    }

    @ViewById(R.id.iv_back)
    private ImageView ivBack;
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        //当前案例含ActionBar


        transport.setOnClickListener(this);
        tvOU.setOnClickListener(this);

        ivBack.setOnClickListener(this);
        transport.setText("交通局");
        tvOU.setText("业主单位");

        // 默认显示交通局
        showTransportInfo();
    }

    /*
    模拟数据
     */
    PayManagerBean payBean;
    private List<SubPayManagerBean> mList;
    private SubPayManagerBean subPayManager;
    private String[] lTitles = {"250,000.00", "250,000.00", "250,000.00", "250,000.00", "250,000.00", "250,000.00", "250,000.00", "250,000.00"};
    private String[] lTimes = {"2019.12.18 09:50", "2019.12.18 09:50", "2019.12.18 09:50", "2019.12.18 09:50", "2019.12.18 09:50",
            "2019.12.18 09:50", "2019.12.18 09:50", "2019.12.18 09:50"};

    private String[] lLabels = {"施工单位", "监理单位", "设计单位"};
    private String[] lMoneys = {"4,852,630.00", "4,852,630.00", "852,630.00"};

    private String[] lLabels1 = {"交通局"};
    private String[] lMoneys1 = {"4,852,630.00"};

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                openActivity(ProjectDetailActivity.class);
                break;
            case R.id.tv_transport:         // 交通局 shap_radio_transport
                transport.setBackgroundResource(R.drawable.shap_radio_white);
                tvOU.setBackgroundResource(R.drawable.shap_radio_transport);
                transport.setTextColor(getResources().getColor(R.color.colorBlue1));
                tvOU.setTextColor(getResources().getColor(R.color.white));

                showTransportInfo();
                break;
            case R.id.tv_owner_unit:        // 业主单位
                //toast("点击了");
                transport.setBackgroundResource(R.drawable.shap_radio_transport_right);
                tvOU.setBackgroundResource(R.drawable.shap_radio_white_right);
                transport.setTextColor(getResources().getColor(R.color.white));
                tvOU.setTextColor(getResources().getColor(R.color.colorBlue1));

                showOwerUnitInfo();
                break;
        }
    }

    private void initTransportInfo() {
        // 初始化业主单位的数据
        payBean = new PayManagerBean();
        payBean.setTotalMoneyInt("48,723,696");
        payBean.setTotalMoneyFloat("." + "00");

        mList = new ArrayList<>();
        for (int i = 0; i < lLabels1.length; i++) {
            subPayManager = new SubPayManagerBean();
            subPayManager.setLabel(lLabels1[i]);
            subPayManager.setlMoney(lMoneys1[i]);
            subPayManager.setHasHide(true);         // 默认隐藏
            List<SubItemLabelBean> subItemList = new ArrayList<>();
            for (int j = 0; j < lTitles.length; j++) {
                SubItemLabelBean subItemLabel = new SubItemLabelBean();
                subItemLabel.setTitle(lTitles[i]);
                subItemLabel.setContent(lTimes[i]);

                subItemList.add(subItemLabel);
            }
            subPayManager.setmList(subItemList);

            mList.add(subPayManager);
        }
    }


    /**
     * 展示交通局数据
     */
    private void showTransportInfo() {
        /*
        初始化交通局数据
         */
        initTransportInfo();
        tvTotalMoneyInt.setText(payBean.getTotalMoneyInt());
        tvTotalMoneyFloat.setText(payBean.getTotalMoneyFloat());
        // 交通局
        transport.setFocusable(true);
        tvOU.setFocusable(false);
        getData2Show();
    }

    /**
     * 展示业主单位数据
     */
    private void showOwerUnitInfo() {
        /*
        初始化业主单位数据
         */
        initOwerUnitInfo();
        tvTotalMoneyInt.setText(payBean.getTotalMoneyInt());
        tvTotalMoneyFloat.setText(payBean.getTotalMoneyFloat());
        // 业主单位
        transport.setFocusable(false);
        tvOU.setFocusable(true);
        getData2Show();
    }

    /**
     * 获取数据展示
     */
    private void getData2Show() {
        PayManagerAdapter adapter = new PayManagerAdapter(this);
        LinearLayoutManager managerVertical = new LinearLayoutManager(this);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置item之间的间距
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 10);//下间距
        cardContent.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        cardContent.setLayoutManager(managerVertical);
        cardContent.setHasFixedSize(true);
        cardContent.setAdapter(adapter);
        cardContent.setNestedScrollingEnabled(false);
        adapter.setmList(mList);

        /**
         * 子项的点击事件
         */
        adapter.setClickListener(new PayManagerContentAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                // 判断焦点在哪
                transport.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PayManagerActivity.this, PayDetailActivity.class);
                        if (transport.isFocusable()){   // 交通局
                            intent.putExtra("key", "transport");
                        }else {                 // 业主单位
                            intent.putExtra("key", "ou");
                        }
                        startActivity(intent);
                    }
                });
            }
        });

    }

    /**
     * 初始化业主单位数据
     */
    private void initOwerUnitInfo() {
        payBean = new PayManagerBean();
        payBean.setTotalMoneyInt("48,723,696");
        payBean.setTotalMoneyFloat("." + "00");

        mList = new ArrayList<>();
        for (int i = 0; i < lLabels.length; i++) {
            subPayManager = new SubPayManagerBean();
            subPayManager.setLabel(lLabels[i]);
            subPayManager.setlMoney(lMoneys[i]);
            subPayManager.setHasHide(true);         // 默认隐藏
            List<SubItemLabelBean> subItemList = new ArrayList<>();
            for (int j = 0; j < lTitles.length; j++) {
                SubItemLabelBean subItemLabel = new SubItemLabelBean();
                subItemLabel.setTitle(lTitles[i]);
                subItemLabel.setContent(lTimes[i]);

                subItemList.add(subItemLabel);
            }
            subPayManager.setmList(subItemList);

            mList.add(subPayManager);
        }

    }
}
