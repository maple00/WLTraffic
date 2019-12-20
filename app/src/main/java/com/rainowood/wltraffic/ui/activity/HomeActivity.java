package com.rainowood.wltraffic.ui.activity;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.fragment.BlankFragment;
import com.rainowood.wltraffic.ui.fragment.HomeFragment;
import com.rainwood.tools.viewinject.ViewById;

public class HomeActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @ViewById(R.id.tabs_rg)
    private RadioGroup mTabRadioGroup;
    // Fragment 组
    private SparseArray<Fragment> mFragmentSparseArray;

    @Override
    protected void initView() {
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.today_tab,new HomeFragment());
        mFragmentSparseArray.append(R.id.record_tab, BlankFragment.newInstance("记录"));
        mFragmentSparseArray.append(R.id.settings_tab, BlankFragment.newInstance("设置"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.today_tab)).commit();

    }
}
