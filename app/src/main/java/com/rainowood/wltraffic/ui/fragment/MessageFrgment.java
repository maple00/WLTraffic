package com.rainowood.wltraffic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseFragment;
import com.rainowood.wltraffic.domain.MessageBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.activity.MessageDetailActivity;
import com.rainowood.wltraffic.ui.adapter.MessageAdapter;
import com.rainowood.wltraffic.utils.DialogUtils;

import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/21 17:43
 * @Desc: 消息
 */
public class MessageFrgment extends BaseFragment implements View.OnClickListener, OnHttpListener {

    @Override
    protected int initLayout() {
        return R.layout.fragment_message;
    }

    // 消息列表
    private ListView mListView;
    private View titleOne;
    private View titleTwo;
    private TextView tvHomeTitleOne;
    private TextView tvHomeTitleTwo;

    private DialogUtils dialog;
    private MessageBean data;

    @Override
    protected void initView(View view) {
        // 初始化项目
        LinearLayoutCompat homeTitleOne = view.findViewById(R.id.ll_home_title_one);
        homeTitleOne.setOnClickListener(this);
        titleOne = view.findViewById(R.id.v_title_one);
        tvHomeTitleOne = view.findViewById(R.id.tv_home_title_one);
        LinearLayoutCompat homeTitleTwo = view.findViewById(R.id.ll_home_title_two);
        homeTitleTwo.setOnClickListener(this);
        titleTwo = view.findViewById(R.id.v_title_two);
        tvHomeTitleTwo = view.findViewById(R.id.tv_home_title_two);
        // 默认样式
        titleTwo.setVisibility(View.INVISIBLE);
        tvHomeTitleOne.setTextSize(20);
        tvHomeTitleOne.setText("待办事项");
        tvHomeTitleTwo.setText("通知公告");
        mListView = view.findViewById(R.id.lv_message);
    }

    @Override
    protected void initData(Context mContext) {
        // 加载中
        waitDialog();
        dialog.showDialog();
        // 请求数据
        new Thread(() -> RequestPost.getMsgData(MessageFrgment.this)).start();
    }


    private void waitDialog() {
        dialog = new DialogUtils(getActivity(), "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_title_one:        // 在建项目
                // 设置样式
                titleOne.setVisibility(View.VISIBLE);
                titleTwo.setVisibility(View.INVISIBLE);
                tvHomeTitleOne.setTextSize(20);
                tvHomeTitleTwo.setTextSize(15);
                showBacklog();
                break;
            case R.id.ll_home_title_two:        // 前期项目
                // 设置样式
                titleOne.setVisibility(View.INVISIBLE);
                titleTwo.setVisibility(View.VISIBLE);
                tvHomeTitleOne.setTextSize(15);
                tvHomeTitleTwo.setTextSize(20);
                showNotice();
                break;
        }
    }


    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if (body != null) {
            if ("1".equals(body.get("code"))) {
                data = JsonParser.parseJSONObject(MessageBean.class, body.get("data"));
                dismissDialog();
                Message msg = new Message();
                msg.what = 0xA11;
                mHandler.sendMessage(msg);
            } else {
                dismissDialog();
                toast(body.get("warn"));
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0xA11) {// 默认展示待办事项
                showBacklog();
            }
        }
    };

    /**
     * show 待办事项
     */
    private void showBacklog() {
        MessageAdapter adapter = new MessageAdapter(getActivity(), data.getBacklog());
        mListView.setAdapter(adapter);
        adapter.setOnClick(position -> {
            Intent intent = new Intent(getActivity(), MessageDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("message", data.getBacklog().get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    /**
     * show 通知公告
     */
    private void showNotice() {
        MessageAdapter adapter = new MessageAdapter(getActivity(), data.getMessage());
        mListView.setAdapter(adapter);
        adapter.setOnClick(position -> {
            Intent intent = new Intent(getActivity(), MessageDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("message", data.getMessage().get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
