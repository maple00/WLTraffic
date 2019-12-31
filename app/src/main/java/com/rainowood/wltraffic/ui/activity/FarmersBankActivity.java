package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.BankRestBean;
import com.rainowood.wltraffic.domain.FarmerBankBean;
import com.rainowood.wltraffic.domain.LabelBean;
import com.rainowood.wltraffic.ui.adapter.FarmerBankAdapter;
import com.rainowood.wltraffic.ui.adapter.HorizontalAdapter;
import com.rainowood.wltraffic.ui.adapter.VerticalAdapter;
import com.rainowood.wltraffic.utils.DateTimeUtils;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
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
    @ViewById(R.id.tv_output_statement)
    private TextView outputStat;
    @ViewById(R.id.rl_rest_time)
    private RecyclerView restTimeList;
    @ViewById(R.id.rl_rest_info)
    private RecyclerView restinfoList;          // 每月产值表、工资表


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
    private List<LabelBean> mHorizontalList;       // 横向年份
    private List<BankRestBean> mVerticalList;        // 纵向数据

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
                restTimeList.setVisibility(View.GONE);
                restinfoList.setVisibility(View.GONE);

                break;
            case R.id.tv_output_statement:          // 每月产值表
                // toast("每月产值表");
                restinfoList.setVisibility(View.VISIBLE);
                restTimeList.setVisibility(View.VISIBLE);
                mListUserFunds.setVisibility(View.GONE);

                // 横向时间，纵向数据，滑动监听
                initTimeInfo();
                initTime();

                // data
                initVDataInfo();
                initVData();
                break;
            case R.id.tv_salary_schedule:           // 农民工工资
                toast("农民工工资");
                break;
        }
    }

    private void initVDataInfo() {
        mVerticalList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            BankRestBean bankRest = new BankRestBean();
            double random = Math.random() * 1000000;

            bankRest.setTime(i + 1 + "月");
            bankRest.setMoney("￥ " + random);
            mVerticalList.add(bankRest);
        }
    }

    private void initVData() {
        VerticalAdapter adapter = new VerticalAdapter(this);
        LinearLayoutManager managerVertical = new LinearLayoutManager(this);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        restinfoList.setLayoutManager(managerVertical);
        restinfoList.setHasFixedSize(true);
        restinfoList.setAdapter(adapter);
        adapter.setVerticalDataList(mVerticalList);

    }


    private void initTime() {
        final HorizontalAdapter adapter = new HorizontalAdapter(this);
        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this);
        managerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        managerHorizontal.setStackFromEnd(true);                    // 定位到最后一项

        restTimeList.setLayoutManager(managerHorizontal);
        restTimeList.setHasFixedSize(true);
        restTimeList.setAdapter(adapter);

        adapter.setHorizontalDataList(mHorizontalList);

        adapter.setListener(new HorizontalAdapter.OnItenmClickListener() {
            @Override
            public void onItemClick(int position) {
                //toast(mHorizontalList.get(postion));
                int flag = -1;      // 记录变量
                for (int i = 0; i < ListUtils.getSize(mHorizontalList); i++) {                // 设置选中
                    if (position == i){
                        mHorizontalList.get(i).setHasSelected(false);
                        flag = position;
                        break;
                    }
                }
                mHorizontalList.get(flag).setHasSelected(true);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initTimeInfo() {
        mHorizontalList = new ArrayList<>();
        for (int i = DateTimeUtils.getNowYear() - 50; i <= DateTimeUtils.getNowYear(); i++) {           // 默认选中今年
            LabelBean label = new LabelBean();
            if (i == DateTimeUtils.getNowYear()){
                label.setHasSelected(true);
            }
            label.setData(String.valueOf(i));
            mHorizontalList.add(label);
        }

    }
}
