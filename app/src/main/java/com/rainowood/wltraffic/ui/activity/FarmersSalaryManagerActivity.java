package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubIteProjectBean;
import com.rainowood.wltraffic.ui.adapter.FarmersSalaryManagerAdapter;
import com.rainwood.tools.viewinject.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 11:43
 * @Desc: 农名工工资管理
 */
public class FarmersSalaryManagerActivity extends BaseActivity implements View.OnClickListener {


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

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("农民工工资管理");

        FarmersSalaryManagerAdapter itemAdapter = new FarmersSalaryManagerAdapter(this, mItemList);
        gvList.setAdapter(itemAdapter);
        itemAdapter.setClickListener(new FarmersSalaryManagerAdapter.ItemOnClickListener() {
            @Override
            public void ItemOnClick(int position) {
                switch (position) {
                    case 0:    // 农民工工资保证金
                        //openActivity(FarmersMarginStatusActivity.class);
                        // 保证金分为三种状态， 已缴，未缴和未设置
                        // 模拟状态
                        int num = (int) (Math.random() * (3));        // 1到3的随机数
                        Log.d("sxs---", "random: " + num);
                        num = 1;
                        if (num == 1) {          // 已缴
                            openActivity(FarmersMarginStatusActivity.class);
                        } else if (num == 2) {          // 未缴
                            openActivity(FarmersNoMarginActivity.class);
                        } else {          // 未设置
                            Intent intent = new Intent(getActivity(), StatusActivity.class);
                            intent.putExtra("key", "marginstatus");
                            startActivity(intent);
                        }
                        break;
                    case 1:    //  实名制
                        openActivity(RealNameActivity.class);
                        break;
                    case 2:    // 专户制
                        int num1 = (int) (Math.random() * (3));        // 1到3的随机数
                        Log.d("sxs---", "random: " + num1);
                        if (num1 == 1) {          // 已缴
                            openActivity(SpecialAccountActivity.class);
                        } else if (num1 == 2) {          // 未缴
                            openActivity(SpecialAccountNoActivity.class);       // 未缴纳
                        } else {          // 未设置
                            Intent intent = new Intent(getActivity(), StatusActivity.class);
                            intent.putExtra("key", "specialaccount");
                            startActivity(intent);
                        }
                        break;
                    case 3:    // 银行代发制
                        openActivity(FarmersBankActivity.class);
                        break;
                    case 4:    // 劳资人员管理
                        openActivity(LaborActivity.class);
                        break;
                    case 5:    // 通报
                        int num2 = (int) (Math.random() * (3));        // 1到3的随机数
                        Log.d("sxs---", "random: " + num2);
                        if (num2 == 1) {          // 已缴
                            openActivity(NotifyModuleActivity.class);
                        } else if (num2 == 2) {          // 未缴
                            openActivity(NotifyModuleNoActivity.class);      // 未缴纳
                        } else {          // 未设置
                            Intent intent = new Intent(getActivity(), StatusActivity.class);
                            intent.putExtra("key", "notify");
                            startActivity(intent);
                        }

                        break;
                    default:
                        break;
                }
            }
        });

    }

    /*
    初始化数据
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
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
