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
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.AssessBean;
import com.rainowood.wltraffic.domain.AssessDeductionBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.AssessAttachmentAdapter;
import com.rainowood.wltraffic.ui.adapter.AssessDeductionAdapter;
import com.rainowood.wltraffic.utils.DateTimeUtils;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/26 14:58
 * @Desc: 考核管理
 */
public final class AssessManagerActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_assess_manager;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.ll_assess_title_one)
    private LinearLayout titleOneLL;
    @ViewById(R.id.tv_assess_title_one)
    private TextView titleOneTV;
    @ViewById(R.id.v_title_one)
    private View lineOne;
    @ViewById(R.id.ll_assess_title_two)
    private LinearLayout titleTwoLL;
    @ViewById(R.id.tv_assess_title_two)
    private TextView titleTwoTV;
    @ViewById(R.id.v_title_two)
    private View lineTwo;

    @ViewById(R.id.lv_assess_list)
    private MeasureListView assessList;

    @ViewById(R.id.lv_assess_deduction)
    private ListView assessDeduction;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("考核管理");

        titleOneLL.setOnClickListener(this);
        titleTwoLL.setOnClickListener(this);
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    // 考核细则
    private List<AssessBean> mList;
    // 扣分明细
    private List<AssessDeductionBean> deductionLists;

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();

        // 请求数据
        new Thread(() -> RequestPost.getItemAssessData(Contants.ITEM_ID, AssessManagerActivity.this)).start();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                openActivity(ProjectDetailActivity.class);
                finish();
                break;
            case R.id.ll_assess_title_one:          // 考核细则
                lineTwo.setVisibility(View.GONE);
                assessDeduction.setVisibility(View.GONE);
                // 加载数据
                lineOne.setVisibility(View.VISIBLE);
                assessList.setVisibility(View.VISIBLE);

                AssessAttachmentAdapter attachmentAdapter = new AssessAttachmentAdapter(getActivity(), mList);
                assessList.setAdapter(attachmentAdapter);

                break;
            case R.id.ll_assess_title_two:              // 扣分明细
                lineOne.setVisibility(View.GONE);
                assessList.setVisibility(View.GONE);
                // 加载数据
                assessDeduction.setVisibility(View.VISIBLE);
                lineTwo.setVisibility(View.VISIBLE);
                // 明细数据列表
                AssessDeductionAdapter deductionAdapter = new AssessDeductionAdapter(this, deductionLists);
                assessDeduction.setAdapter(deductionAdapter);
                deductionAdapter.setItemListener(position -> {
                    //toast("点击了：" + position);
                    Intent intent = new Intent(AssessManagerActivity.this, DeductionDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("content", deductionLists.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                });

                break;
          /*  case R.id.iv_before:            // 上个月
                if ("1".equals(tempMonthStr)){
                    month.setText("12");
                    year.setText(String.valueOf(Integer.parseInt(tempYearStr) - 1));
                }else {
                    month.setText(String.valueOf(Integer.parseInt(tempMonthStr) - 1));
                }
                toast("年月：" + year.getText().toString().trim() + "年" + month.getText().toString().trim() + "月");
                break;
            case R.id.iv_next:              // 下个月
                if (!"12".equals(tempMonthStr)){
                    month.setText(String.valueOf(Integer.parseInt(tempMonthStr) + 1));
                }else {
                    month.setText("1");
                    year.setText(String.valueOf(Integer.parseInt(tempYearStr) + 1));
                }
                toast("年月：" + year.getText().toString().trim() + "年" + month.getText().toString().trim() + "月");
                break;*/
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private static class getNowTime {
        private static int getYear() {
            return DateTimeUtils.getNowYear();
        }

        private static int getMonth() {
            return DateTimeUtils.getNowMonth();
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
            mList = JsonParser.parseJSONArray(AssessBean.class, data.get("left"));
            deductionLists = JsonParser.parseJSONArray(AssessDeductionBean.class, data.get("right"));

            dismissDialog();
            Message msg = new Message();
            msg.what = 0x1022;
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
                case 0x1022:
                    // 考核细则
                    titleOneTV.setText("考核细则");
                    AssessAttachmentAdapter attachmentAdapter = new AssessAttachmentAdapter(getActivity(), mList);
                    assessList.setAdapter(attachmentAdapter);
                    // 扣分明细
                    titleTwoTV.setText("扣分明细");
                    lineTwo.setVisibility(View.GONE);
                    break;
            }
        }
    };
}
