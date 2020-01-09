package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.FarmersBankBean;
import com.rainowood.wltraffic.domain.SubBankInfoYear;
import com.rainowood.wltraffic.domain.SubBankInfoYearInfo;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.FarmerBankAdapter;
import com.rainowood.wltraffic.ui.adapter.HorizontalAdapter;
import com.rainowood.wltraffic.ui.adapter.ItemAttachListAdapter;
import com.rainowood.wltraffic.ui.adapter.VerticalAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainowood.wltraffic.utils.ImmersionUtil;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/30 11:34
 * @Desc: 农名工工资管理银行代发制度
 */
public final class FarmersBankActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fermer_bank;
    }

    @ViewById(R.id.iv_back)
    private ImageView pageBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.iv_background)
    private ImageView background;
    @ViewById(R.id.f_title)
    private FrameLayout title;
    @ViewById(R.id.tv_status)
    private TextView status;
    @ViewById(R.id.ll_status)
    private LinearLayout statusLine;
    @ViewById(R.id.iv_pre_logo)
    private ImageView preLogo;

    @ViewById(R.id.lv_attach)
    private MeasureListView attachList;

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

    private DialogUtils dialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 图片沉浸
        ImmersionUtil.ImageImmers(FarmersBankActivity.this, title, background);
        // 状态栏字体白色
        StatusBarUtil.setStatusBarFontIconDark(FarmersBankActivity.this, getResources().getColor(R.color.white), false);
        pageBack.setOnClickListener(this);
        pageTitle.setText("银行代发制");
        // 初始化背景
        background.setImageDrawable(null);

        // 转入专户资金
        userFunds.setOnClickListener(this);
        // 每月产值表
        outputStat.setOnClickListener(this);
        restinfoList.setVisibility(View.GONE);
        // 农民工工资
        salarySchedule.setOnClickListener(this);
    }

    private FarmersBankBean data;
    private List<SubBankInfoYearInfo> yearInfosList;

    // flag
    private int flagSize = -1;

    // 每月产值表
    private List<SubBankInfoYear> mHorizontalList;       // 横向年份
    private List<SubBankInfoYearInfo> mVerticalList;        // 纵向数据

    // 农民工工资
    private List<SubBankInfoYear> mYearList;       // 横向年份
    private List<SubBankInfoYearInfo> mContextList;        // 纵向数据

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();

        // 请求数据
        new Thread(() -> RequestPost.getItemFarmerFourData(Contants.ITEM_ID, FarmersBankActivity.this)).start();

    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_user_funds:            // 转入专户资金
                //toast("转入专户资金");
                outputStat.setBackgroundResource(R.drawable.shap_radio_white2blue1);
                outputStat.setTextColor(getResources().getColor(R.color.colorBlue1));
                userFunds.setBackgroundResource(R.drawable.shap_radio_blue2white);
                userFunds.setTextColor(getResources().getColor(R.color.white));
                salarySchedule.setBackgroundResource(R.drawable.shap_radio_white2blue1);
                salarySchedule.setTextColor(getResources().getColor(R.color.colorBlue1));

                mListUserFunds.setVisibility(View.VISIBLE);
                restTimeList.setVisibility(View.GONE);
                restinfoList.setVisibility(View.GONE);
                break;
            case R.id.tv_output_statement:          // 每月产值表---list2
                outputStat.setBackgroundResource(R.drawable.shap_radio_blue2white_midle);
                outputStat.setTextColor(getResources().getColor(R.color.white));
                userFunds.setBackgroundResource(R.drawable.shap_radio_white2blue3);
                userFunds.setTextColor(getResources().getColor(R.color.colorBlue1));
                salarySchedule.setBackgroundResource(R.drawable.shap_radio_white2blue2);
                salarySchedule.setTextColor(getResources().getColor(R.color.colorBlue1));

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
            case R.id.tv_salary_schedule:           // 农民工工资 salarySchedule---list3
                //toast("农民工工资");
                salarySchedule.setBackgroundResource(R.drawable.shap_radio_blue2white_right);
                salarySchedule.setTextColor(getResources().getColor(R.color.white));
                outputStat.setBackgroundResource(R.drawable.shap_radio_white2blue1);
                outputStat.setTextColor(getResources().getColor(R.color.colorBlue1));
                userFunds.setBackgroundResource(R.drawable.shap_radio_white2blue3);
                userFunds.setTextColor(getResources().getColor(R.color.colorBlue1));

                restinfoList.setVisibility(View.VISIBLE);
                restTimeList.setVisibility(View.VISIBLE);
                mListUserFunds.setVisibility(View.GONE);
                // 横向时间，纵向数据，滑动监听
                initTimeSalary();
                initTimeList();
                // data
                initSalaryInfo();
                initVSalaryData();
                break;
        }
    }

    private void initVDataInfo() {
        mVerticalList = data.getList2().getContent();
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

    private void initVSalaryData() {
        VerticalAdapter adapter = new VerticalAdapter(this);
        LinearLayoutManager managerVertical = new LinearLayoutManager(this);
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        restinfoList.setLayoutManager(managerVertical);
        restinfoList.setHasFixedSize(true);
        restinfoList.setAdapter(adapter);
        adapter.setVerticalDataList(mContextList);
    }

    private void initSalaryInfo() {
        mContextList = data.getList3().getContent();
    }

    /**
     * 每月产值表
     */
    private void initTime() {
        final HorizontalAdapter adapter = new HorizontalAdapter(this);
        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this);
        managerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        managerHorizontal.setStackFromEnd(true);                    // 定位到最后一项
        restTimeList.setLayoutManager(managerHorizontal);
        restTimeList.setHasFixedSize(true);
        restTimeList.setAdapter(adapter);
        adapter.setHorizontalDataList(mHorizontalList);
        adapter.setListener(position -> {
            // 如果选中的年份已经被选中了，则不让点击
            for (int i = 0; i < ListUtils.getSize(mHorizontalList); i++) {                // 设置选中
                // 全部置为false
                if (mHorizontalList.get(i).isHasSelected()) {
                    mHorizontalList.get(i).setHasSelected(false);
                    break;
                }
            }
            mHorizontalList.get(position).setHasSelected(true);
            adapter.notifyDataSetChanged();
            flagSize = 1;
            dialog.showDialog();
            // 请求指定年的接口
            new Thread(() -> RequestPost.getItemFarmerYearData(Contants.ITEM_ID, mHorizontalList.get(position).getYears(),
                    "list2", FarmersBankActivity.this)).start();
        });
    }

    /**
     * 农民工工资表
     */
    private void initTimeList() {
        final HorizontalAdapter adapter = new HorizontalAdapter(this);
        LinearLayoutManager managerHorizontal = new LinearLayoutManager(this);
        managerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        managerHorizontal.setStackFromEnd(true);                    // 定位到最后一项

        restTimeList.setLayoutManager(managerHorizontal);
        restTimeList.setHasFixedSize(true);
        restTimeList.setAdapter(adapter);

        adapter.setHorizontalDataList(mYearList);

        adapter.setListener(position -> {
            for (int i = 0; i < ListUtils.getSize(mYearList); i++) {                // 设置选中
                // 全部置为false
                if (mYearList.get(i).isHasSelected()) {
                    mYearList.get(i).setHasSelected(false);
                    break;
                }
            }
            mYearList.get(position).setHasSelected(true);
            adapter.notifyDataSetChanged();
            flagSize = -1;
            dialog.showDialog();
            // 请求指定年的接口
            new Thread(() -> RequestPost.getItemFarmerYearData(Contants.ITEM_ID, mHorizontalList.get(position).getYears(),
                    "list3", FarmersBankActivity.this)).start();

        });
    }

    private void initTimeInfo() {
        mHorizontalList = data.getList2().getYear();
    }

    private void initTimeSalary() {
        mYearList = data.getList3().getYear();
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            Message msg = new Message();
            if (result.url().contains("library/mData.php?type=nonWorkersState3")) {
                data = JsonParser.parseJSONObject(FarmersBankBean.class, body.get("data"));
                dismissDialog();
                msg.what = 0xA11;
            }
            if (result.url().contains("library/mData.php?type=yearsData")) {
                yearInfosList = JsonParser.parseJSONArray(SubBankInfoYearInfo.class, body.get("data"));
                dismissDialog();
                msg.what = 0xA12;
            }
            mHandler.sendMessage(msg);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0xA11:                 // 转入专户资金
                    if ("已签订".equals(data.getTop().getState())) {
                        background.setImageDrawable(getResources().getDrawable(R.drawable.img_background_ok_3x));
                    }

                    if ("未签订".equals(data.getTop().getState())) {
                        background.setImageDrawable(getResources().getDrawable(R.drawable.img_background_no_3x));
                        statusLine.setGravity(Gravity.CENTER_HORIZONTAL);
                        preLogo.setBackgroundResource(R.drawable.ic_icon_no_finish_white);
                    }
                    status.setText(data.getTop().getState() + "三方协议");
                    FarmerBankAdapter bankAdapter = new FarmerBankAdapter(FarmersBankActivity.this, data.getList1());
                    mListUserFunds.setAdapter(bankAdapter);
                    // 文档列表
                    ItemAttachListAdapter listAdapter = new ItemAttachListAdapter(FarmersBankActivity.this, data.getTop().getFile());
                    attachList.setAdapter(listAdapter);
                    break;
                case 0xA12:
                    if (flagSize == 1){             // 每月产值表
                        data.getList2().setContent(yearInfosList);
                        initVDataInfo();
                        initVData();
                    }
                    if (flagSize == -1){        // 农民工工资
                        data.getList3().setContent(yearInfosList);
                        initSalaryInfo();
                        initVSalaryData();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
