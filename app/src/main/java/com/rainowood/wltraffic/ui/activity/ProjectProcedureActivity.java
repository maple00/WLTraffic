package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.ProjectProcedureBean;
import com.rainowood.wltraffic.ui.adapter.ProjectProcedureAdapter;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 17:37
 * @Desc: 项目建设程序
 */
public class ProjectProcedureActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_procedure;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;

    @ViewById(R.id.lv_procedure_list)
    private MeasureListView procedureList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("项目建设程序");

        ProjectProcedureAdapter procedureAdapter = new ProjectProcedureAdapter(this, mList);
        procedureList.setAdapter(procedureAdapter);
        procedureAdapter.notifyDataSetChanged();

    }

    /*
    模拟数据
     */
    private List<ProjectProcedureBean> mList;
    private String[] mTitles = {"初步设计批复", "质量安全报监", "施工许可证", "交工验收", "竣工验收"};
    private String[] mTimes = {"2019.12.17申请", "2019.12.17申请", "2019.12.17申请", "2019.12.17申请", "2019.12.17申请"};
    private String[] mDocNames = {"安置房改造设计附件.doc", "监督通知书.doc", "许可文本.doc"};
    private String[] mStandard = {"合格", "不合格"};

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ProjectProcedureBean procedure = new ProjectProcedureBean();
            procedure.setTitle(mTitles[i]);
            procedure.setTime(mTimes[i]);
            if (i < 3){
                procedure.setDocName(mDocNames[i]);
            }else {
                procedure.setStandard(mStandard[i - 3]);
            }

            mList.add(procedure);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back){
            finish();
        }
    }
}
