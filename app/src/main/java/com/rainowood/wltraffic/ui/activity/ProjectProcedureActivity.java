package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.ProjectProcedureBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ProjectProcedureAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.viewinject.ViewById;
import com.rainwood.tools.widget.MeasureListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/23 17:37
 * @Desc: 项目建设程序
 */
public final class ProjectProcedureActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

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

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("项目建设程序");

    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    private List<ProjectProcedureBean> mList;

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();

        new Thread(() -> RequestPost.getItemConstructionData(Contants.ITEM_ID, ProjectProcedureActivity.this)).start();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back){
            finish();
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))){
            mList = JsonParser.parseJSONArray(ProjectProcedureBean.class,body.get("data"));
            Message msg = new Message();
            msg.what = 0x1557;
            mHandler.sendMessage(msg);
            dismissDialog();
        }else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }
    /**
     * 刷新UI
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0x1557:
                    ProjectProcedureAdapter procedureAdapter = new ProjectProcedureAdapter(ProjectProcedureActivity.this, mList);
                    procedureList.setAdapter(procedureAdapter);
                    procedureAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
