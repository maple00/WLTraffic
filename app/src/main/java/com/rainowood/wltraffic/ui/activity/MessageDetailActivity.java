package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainwood.tools.view.SmartTextView;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: shearson
 * @Time: 2019/12/21 21:13
 * @Desc: 消息详情页
 */
public final class MessageDetailActivity extends BaseActivity  implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activituy_message_detail;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView titleTv;
    @ViewById(R.id.stv_message)
    private SmartTextView messageTv;


    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        titleTv.setText("消息详情");
    }

    // 模拟数据
    String msg = "\n" +
            "随着公司各类园林绿化景观工程项目的逐步增加、且总多面广、战线拉长，管理层  和管理方式也都发生了变化。为严格项目管理和规范项目资金拨付使用，确保工程项目和资金安全，依照《中华人民共和国建筑法》、《中华人民共和国合同法》等法律法规，结合公司相关规章制度和工作实际，对进一步加强和完善工程项目管理作出具体规定，现通知如下：\n" +
            "一、工程项目管理是指公司承接的各类园林绿化景观建设和管养工程、材料采购管理、工程质量和安全生产管理、工程款催收和内部拨付管理等的总称。公司各部门要按照部门职能责任分工，进一步细化管理内容，规范管理流程，完善管理程序，明确办事时限，并做到系统化、书面化和表格化管理。\n" +
            " ";

    @Override
    protected void initData() {
        super.initData();
        messageTv.setText(msg);
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
