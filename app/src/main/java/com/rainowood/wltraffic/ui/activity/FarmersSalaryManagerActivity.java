package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.FarmersMarginBean;
import com.rainowood.wltraffic.domain.NotifyBean;
import com.rainowood.wltraffic.domain.SpecialAccountBean;
import com.rainowood.wltraffic.domain.SubIteProjectBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.FarmersSalaryManagerAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.viewinject.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/27 11:43
 * @Desc: 农名工工资管理
 */
public final class FarmersSalaryManagerActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_farmers_salary_manager;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.gv_list)
    private GridView gvList;

    private DialogUtils dialog;

    //
    private FarmersMarginBean marginData;           // 保证金
    private SpecialAccountBean specialAccount;              // 专户制
    private NotifyBean notify;                          //通报

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("农民工工资管理");
        FarmersSalaryManagerAdapter itemAdapter = new FarmersSalaryManagerAdapter(this, mItemList);
        gvList.setAdapter(itemAdapter);
        itemAdapter.setClickListener(position -> {
            waitDialog();
            switch (position) {
                case 0:    // 农民工工资保证金
                    dialog.showDialog();
                    new Thread(() -> RequestPost.getItemFarmerOneData(Contants.ITEM_ID, FarmersSalaryManagerActivity.this)).start();
                    break;
                case 1:    //  实名制
                    openActivity(RealNameActivity.class);
                    break;
                case 2:    // 专户制
                    dialog.showDialog();
                    new Thread(() -> RequestPost.getItemFarmerThreeData(Contants.ITEM_ID, FarmersSalaryManagerActivity.this)).start();
                    break;
                case 3:    // 银行代发制
                    openActivity(FarmersBankActivity.class);
                    break;
                case 4:    // 劳资人员管理
                    openActivity(LaborActivity.class);
                    break;
                case 5:    // 通报
                    dialog.showDialog();
                    new Thread(() -> RequestPost.getItemFarmerSixData(Contants.ITEM_ID, FarmersSalaryManagerActivity.this)).start();
                    break;
                default:
                    break;
            }
        });

    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    /*
    初始化界面数据
     */
    private List<SubIteProjectBean> mItemList;
    private int[] mIcons = {R.drawable.ic_icon_bond, R.drawable.ic_icon_real_name, R.drawable.ic_icon_special_account,
            R.drawable.ic_icon_bank, R.drawable.ic_icon_workmans, R.drawable.ic_icon_notice};
    private String[] mLabels = {"农民工工资保证金", "实名制", "专户制", "银行代发制", "劳资人员管理", "通报"};

    @Override
    protected void initData() {
        super.initData();
        mItemList = new ArrayList<>();
        for (int i = 0; i < mIcons.length; i++) {
            SubIteProjectBean item = new SubIteProjectBean();
            item.setIcon(mIcons[i]);
            item.setLabel(mLabels[i]);
            mItemList.add(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            openActivity(ProjectDetailActivity.class);
            finish();
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {      // 多个接口
            Message msg = new Message();
            if (result.url().contains("library/mData.php?type=nonWorkersState")) {   // 农民工工资保证金
                marginData = JsonParser.parseJSONObject(FarmersMarginBean.class, body.get("data"));
                dismissDialog();
                msg.what = 0xA0;
            }
            if (result.url().contains("library/mData.php?type=nonWorkersState2")) {        // 专户制度
                specialAccount = JsonParser.parseJSONObject(SpecialAccountBean.class, body.get("data"));
                dismissDialog();
                msg.what = 0xA1;
            }
            if (result.url().contains("library/mData.php?type=nonWorkersState5")) {         // 通报
                notify = JsonParser.parseJSONObject(NotifyBean.class, body.get("data"));
                dismissDialog();
                msg.what = 0xA2;
            }
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
                case 0xA0:                    // 保证金
                    if (marginData.getContent() != null) {
                        String state = marginData.getContent().getState();
                        if ("已缴存".equals(state)) {
                            Intent intent = new Intent(FarmersSalaryManagerActivity.this, FarmersMarginStatusActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("value", marginData);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if ("未缴存".equals(state)) {
                            Intent intent = new Intent(FarmersSalaryManagerActivity.this, FarmersNoMarginActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("value", marginData);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), StatusActivity.class);
                            intent.putExtra("key", "marginstatus");
                            startActivity(intent);
                        }
                    }
                    break;
                case 0xA1:                      // 专户制
                    if ("已签订".equals(specialAccount.getTop().getState())) {
                        Intent intent = new Intent(FarmersSalaryManagerActivity.this, SpecialAccountActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("specialAccount", specialAccount);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        //openActivity(SpecialAccountActivity.class);
                    } else if ("未签订".equals(specialAccount.getTop().getState())) {
                        Intent intent = new Intent(FarmersSalaryManagerActivity.this, SpecialAccountNoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("specialAccount", specialAccount);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), StatusActivity.class);
                        intent.putExtra("key", "specialaccount");
                        startActivity(intent);
                    }
                    break;
                case 0xA2:                  // 通报
                    if ("已设置".equals(notify.getTop().getState())) {                 // 已设置
                        Intent intent = new Intent(FarmersSalaryManagerActivity.this, NotifyModuleActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("notify", notify);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if ("未设置".equals(notify.getTop().getState())) {          // 未设置
                        Intent intent = new Intent(FarmersSalaryManagerActivity.this, NotifyModuleNoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("notify", notify);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), StatusActivity.class);
                        intent.putExtra("key", "notify");
                        startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}