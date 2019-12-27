package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubItemLabelBean;
import com.rainowood.wltraffic.ui.adapter.RealNameAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 16:43
 * @Desc: 实名制
 */
public class RealNameActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_real_name;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.lv_real_list)
    private MeasureListView realList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("实名制");

        RealNameAdapter nameAdapter = new RealNameAdapter(this, mList);
        realList.setAdapter(nameAdapter);
        nameAdapter.setClickListener(new RealNameAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //toast("点击了：" + position);
                openActivity(RealNameDetailActivity.class);
            }
        });
    }

    /*
    模拟数据
     */
    private List<SubItemLabelBean> mList;
    private String[] mTitls = {"花名册附件.doc", "花名册附件花名册附件花名册附件花名册的....doc", "花名册附件.doc....doc", "花名册附件.doc"};
    private String[] mLabels = {"2019.12.28 16:50:00", "2019.12.28 16:50:00", "2019.12.28 16:50:00", "2019.12.28 16:50:00"};


    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < mTitls.length; i++) {
            SubItemLabelBean label = new SubItemLabelBean();
            label.setTitle(mTitls[i]);
            label.setContent(mLabels[i]);
            mList.add(label);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
