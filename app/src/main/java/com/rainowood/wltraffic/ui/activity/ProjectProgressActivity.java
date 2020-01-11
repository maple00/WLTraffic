package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.ProjectProgressBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.adapter.ProjectProgressAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainowood.wltraffic.utils.RecyclerViewSpacesItemDecoration;
import com.rainwood.tools.common.MeasureUtil;
import com.rainwood.tools.viewinject.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:03
 * @Desc: 项目进度
 */
public final class ProjectProgressActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_progress;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.tv_title)
    private TextView pageTitle;
    @ViewById(R.id.tv_title_content)
    private TextView titleContent;

    @ViewById(R.id.rlv_content)
    private RecyclerView rclContentList;

    private DialogUtils dialog;
    // 数据
    private List<ProjectProgressBean> mList;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        pageTitle.setText("进度管理");

        titleContent.setText(getIntent().getStringExtra("itemName"));
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();

        // 加载中
        waitDialog();
        dialog.showDialog();

        new Thread(() -> RequestPost.getItemProgressData(Contants.ITEM_ID, ProjectProgressActivity.this)).start();

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
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        Log.e("sxs", "body: " + JsonParser.parseJSONArray(body.get("data")));
        if ("1".equals(body.get("code"))) {
            mList = JsonParser.parseJSONArray(ProjectProgressBean.class, body.get("data"));

            Message msg = new Message();
            msg.what = 0x1633;
            mHandler.sendMessage(msg);

            dismissDialog();
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
                case 0x1633:
                    ProjectProgressAdapter adapter = new ProjectProgressAdapter(ProjectProgressActivity.this);
                    LinearLayoutManager managerVertical = new LinearLayoutManager(ProjectProgressActivity.this);
                    managerVertical.setOrientation(LinearLayoutManager.VERTICAL);       // 纵向
                    // 设置Item之间的间距
                    HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
                    stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, MeasureUtil.dip2px(ProjectProgressActivity.this, 30)); // 下间距
                    rclContentList.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
                    rclContentList.setLayoutManager(managerVertical);
                    rclContentList.setHasFixedSize(true);
                    rclContentList.setAdapter(adapter);
                    adapter.setVerticalDataList(mList);
                    break;
            }
        }
    };
}
