package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.FarmerBankBean;
import com.rainowood.wltraffic.ui.adapter.FarmerBankAdapter;
import com.rainowood.wltraffic.ui.adapter.FarmerBankSlideAdapter;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/30 11:34
 * @Desc: 农名工工资管理银行代发制度
 */
public class FarmersBankActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fermer_bank;
    }

    @ViewById(R.id.iv_back)
    private ImageView pageBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_status)
    private TextView status;
    @ViewById(R.id.tv_word_title)
    private TextView wordTitle;
    @ViewById(R.id.ll_notify_download)
    private LinearLayout download;
    @ViewById(R.id.ll_preview)
    private LinearLayout preview;


    // 转入专户资金
    @ViewById(R.id.tv_user_funds)
    private TextView userFunds;
    @ViewById(R.id.lv_user_funds)
    private MeasureListView mListUserFunds;


    // 每月产值表  -- 双向滑动
    @ViewById(R.id.rl_rest_info)
    private RecyclerView restinfoList;          // 每月产值表、工资表
    @ViewById(R.id.tv_output_statement)
    private TextView outputStat;

    // 农民工工资   -- 双向滑动
    @ViewById(R.id.tv_salary_schedule)
    private TextView salarySchedule;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        pageBack.setOnClickListener(this);
        pageTitle.setText("银行代发制");
        status.setText("已签订三方协议");
        wordTitle.setText("三方协议具体细则.doc");
        download.setOnClickListener(this);
        preview.setOnClickListener(this);
        /*
        默认显示转入专户资金
         */
        // 转入专户资金
        userFunds.setOnClickListener(this);
        FarmerBankAdapter bankAdapter = new FarmerBankAdapter(this, userFundsList);
        mListUserFunds.setAdapter(bankAdapter);
        // 每月产值表
        outputStat.setOnClickListener(this);
        restinfoList.setVisibility(View.GONE);
        // 农民工工资
        salarySchedule.setOnClickListener(this);

    }

    /*
    模拟数据
     */
    private List<FarmerBankBean> userFundsList;
    private String[] fundsMoneys = {"￥560.00", "￥560.00", "￥560.00"};
    private String[] fundsTimes = {"2019.12.18 17:19:00", "2019.12.18 17:19:00", "2019.12.18 17:19:00"};
    private String[] fundsContents = {"这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注", "这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注",
            "这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注这里的内容是备注"};

    // 每月产值表、工资
    private List<Integer> mTypeList = new ArrayList<>();

    private List<String> mHorizontalList = new ArrayList<>();
    private List<String> mVerticalList = new ArrayList<>();

    private void initParam() {
        mTypeList.add(0);   // 横向滑动
        mTypeList.add(1);   // 纵向滑动
    }

    @Override
    protected void initData() {
        super.initData();
        // 转入专户资金
        userFundsList = new ArrayList<>();
        for (int i = 0; i < fundsMoneys.length; i++) {
            FarmerBankBean bank = new FarmerBankBean();
            bank.setMoney(fundsMoneys[i]);
            bank.setTime(fundsTimes[i]);
            bank.setContent(fundsContents[i]);
            userFundsList.add(bank);
        }

        // 每月产值表、工资
        initParam();
        initHorizontalData();
        initVerticalData();

    }

    private void initHorizontalData() {
        mHorizontalList.add("横向1");
        mHorizontalList.add("横向2");
        mHorizontalList.add("横向3");
        mHorizontalList.add("横向4");
        mHorizontalList.add("横向5");
        mHorizontalList.add("横向6");
        mHorizontalList.add("横向7");
        mHorizontalList.add("横向8");
        mHorizontalList.add("横向9");
        mHorizontalList.add("横向10");
    }

    private void initVerticalData() {
        mVerticalList.add("纵向1");
        mVerticalList.add("纵向2");
        mVerticalList.add("纵向3");
        mVerticalList.add("纵向4");
        mVerticalList.add("纵向5");
        mVerticalList.add("纵向6");
        mVerticalList.add("纵向7");
        mVerticalList.add("纵向8");
        mVerticalList.add("纵向9");
        mVerticalList.add("纵向10");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_user_funds:            // 转入专户资金
                //toast("转入专户资金");
                mListUserFunds.setVisibility(View.VISIBLE);
                restinfoList.setVisibility(View.GONE);
                break;
            case R.id.tv_output_statement:          // 每月产值表
                //toast("每月产值表");
                restinfoList.setVisibility(View.VISIBLE);
                mListUserFunds.setVisibility(View.GONE);

                FarmerBankSlideAdapter adapter = new FarmerBankSlideAdapter(this, mTypeList);

                restinfoList.setLayoutManager(new LinearLayoutManager(this));
                restinfoList.setHasFixedSize(false);
                restinfoList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                restinfoList.setAdapter(adapter);

                adapter.setmHorizontalList(mHorizontalList);
                adapter.setmVerticalList(mVerticalList);
                break;
            case R.id.tv_salary_schedule:           // 农民工工资
                toast("农民工工资");
                break;
        }
    }
}
