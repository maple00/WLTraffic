package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.PayManagerBean;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.domain.SubPayManagerBean;
import com.rainowood.wltraffic.ui.adapter.PayManagerCardAdapter;
import com.rainowood.wltraffic.ui.adapter.PayManagerContentAdapter;
import com.rainwood.tools.statusbar.StatusBarUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
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
    private TextView tvTraport;     // 交通局
    @ViewById(R.id.tv_owner_unit)
    private TextView tvOU;          // 业主单位
    @ViewById(R.id.tv_total_money_int)
    private TextView tvTotalMoneyInt;       // 总金额，整数部分
    @ViewById(R.id.tv_total_money_float)
    private TextView tvTotalMoneyFloat;     // 总金额，小数部分
    @ViewById(R.id.lv_lv_card_view)
    private ListView lvCardList;     // 卡片


    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 状态栏渐变
        StatusBarUtils.with(this)
                .setIsActionBar(true)
                .clearActionBarShadow()
                .setDrawable(getResources().getDrawable(R.drawable.shape_bg_change))
                .init();

        tvTraport.setOnClickListener(this);
        tvOU.setOnClickListener(this);

        ivBack.setOnClickListener(this);
        tvTraport.setText("交通局");
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
                tvTraport.setBackgroundResource(R.drawable.shap_radio_white);
                tvOU.setBackgroundResource(R.drawable.shap_radio_transport);
                tvTraport.setTextColor(getResources().getColor(R.color.colorBlue1));
                tvOU.setTextColor(getResources().getColor(R.color.white));

                showTransportInfo();
                break;
            case R.id.tv_owner_unit:        // 业主单位
                //toast("点击了");
                tvTraport.setBackgroundResource(R.drawable.shap_radio_transport_right);
                tvOU.setBackgroundResource(R.drawable.shap_radio_white_right);
                tvTraport.setTextColor(getResources().getColor(R.color.white));
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

        PayManagerCardAdapter cardAdapter = new PayManagerCardAdapter(this, mList);
        lvCardList.setAdapter(cardAdapter);

        cardAdapter.setListener(new PayManagerContentAdapter.LabelListener() {
            @Override
            public void onLabelClick(int position) {
                toast("点击了：" + position);
            }
        });
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
        PayManagerCardAdapter cardAdapter = new PayManagerCardAdapter(this, mList);
        lvCardList.setAdapter(cardAdapter);


        cardAdapter.setListener(new PayManagerContentAdapter.LabelListener() {
            @Override
            public void onLabelClick(int position) {
                toast("点击了：" + position);
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
