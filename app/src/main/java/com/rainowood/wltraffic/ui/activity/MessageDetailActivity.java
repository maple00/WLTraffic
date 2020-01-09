package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.domain.SubMsgBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.fragment.MessageFrgment;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.view.SmartTextView;
import com.rainwood.tools.viewinject.ViewById;

import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * @Author: shearson
 * @Time: 2019/12/21 21:13
 * @Desc: 消息详情页
 */
public final class MessageDetailActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

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

    private DialogUtils dialog;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        titleTv.setText("消息详情");

        // 从推送过来
        String extra = getIntent().getStringExtra(JPushInterface.EXTRA_EXTRA);
        if (extra != null) {    // 有通知的时候，添加桌面红点
            Map<String, String> json = JsonParser.parseJSONObject(extra);
            String id = json.get("id");
            new Thread(() -> RequestPost.getMsgDetailData(id, MessageDetailActivity.this)).start();
        } else {                 // 从fragment点击进来
            SubMsgBean message = (SubMsgBean) getIntent().getSerializableExtra("message");
            if (message != null) {
                messageTv.setText(Html.fromHtml(message.getText()));
            }
            dismissDialog();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        // 加载中
        waitDialog();
        dialog.showDialog();
    }

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 200);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            //openActivity(MessageFrgment.class);
            //Intent intent = new Intent(this, MessageFrgment.class);
            //startActivity(intent);
            finish();
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            SubMsgBean data = JsonParser.parseJSONObject(SubMsgBean.class, body.get("data"));
            dismissDialog();
            Message msg = new Message();
            msg.what = 0xA12;
            msg.obj = data;
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
            if (msg.what == 0xA12) {
                SubMsgBean subMsg = (SubMsgBean) msg.obj;
                messageTv.setText(Html.fromHtml(subMsg.getText()));
            }
        }
    };
}
