package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.utils.CountDownTimerUtils;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.view.ClearEditText;
import com.rainwood.tools.view.InputTextHelper;
import com.rainwood.tools.viewinject.ViewById;

import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/21 11:10
 * @Desc: 忘记密码手机号验证页面
 */
public final class CheckPhoneActivity extends BaseActivity implements OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_phone;
    }

    @ViewById(R.id.tv_title)
    private TextView mTitle;
    @ViewById(R.id.btn_back)
    private Button btnBack;
    @ViewById(R.id.btn_get_code)
    private TextView getCode;
    @ViewById(R.id.et_check_phone)
    private ClearEditText checkPhone;
    @ViewById(R.id.tv_check_phone_title)
    private TextView checkPhoneTitle;

    private DialogUtils dialog;

    @Override
    protected void initView() {
        mTitle.setVisibility(View.GONE);
        btnBack.setOnClickListener(this);
        getCode.setOnClickListener(this);
        // EditText 自动聚焦弹起软键盘
        showSoftInputFromWindow(checkPhone);
        // 手机号格式校验,如果输入的手机号格式不正确，则按钮置灰
        InputTextHelper.with(this)
                .addView(checkPhone)
                .setMain(getCode)
                .setListener(new InputTextHelper.OnInputTextListener() {
                    @Override
                    public boolean onInputChange(InputTextHelper helper) {
                        return checkPhone.getText().toString().length() == 11;
                    }
                }).build();

        /*
        判断是发送验证码手机号还是修改手机号
         */
        // 修改手机号
        if (Contants.CHANGEFLAG == 1) {
            checkPhoneTitle.setText("修改手机号");
            checkPhone.setHint("请输入新的手机号");
        }

        if (Contants.CHANGEFLAG == 0){
            checkPhone.setHint("请输入手机号");
            checkPhoneTitle.setText("手机号验证");
        }
    }

    @Override
    protected void initData() {
        super.initData();
        // 获取之前输入过最近的一次手机号,填写在EditTExt上
        if (Contants.PhoneCheckVerify != null) {
            checkPhone.setText(Contants.PhoneCheckVerify);
        }

        // 如果任务定时器的时间不足一分钟，则不允许再次获取验证码
        if (CountDownTimerUtils.CountDownTimerSize > 0) {
            CountDownTimerUtils.initCountDownTimer(CountDownTimerUtils.CountDownTimerSize / 1000, getCode, "可重新发送");
            CountDownTimerUtils.countDownTimer.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                // 判断从哪个页面过来的
                if (Contants.CHANGEFLAG == 1) {       // 从个人中心过来
                    finish();
                }
                if (Contants.CHANGEFLAG == 0) {      // 从登录页面过来
                    openActivity(LoginActivity.class);
                }
                break;
            case R.id.btn_get_code:
                dialog = new DialogUtils(this, "加载中");
                dialog.showDialog();
                // 请求验证码
                RequestPost.getVerfy(checkPhone.getText().toString().trim(), this);
                // 记录最新填写的手机号
                Contants.PhoneCheckVerify = checkPhone.getText().toString().trim();
                break;
        }
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))){      // 请求成功
            openActivity(CodeVerifyActivity.class);
        }else {
            dialog.dismissDialog();
            toast(body.get("warn"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null){
            dialog.dismissDialog();
        }
    }
}
