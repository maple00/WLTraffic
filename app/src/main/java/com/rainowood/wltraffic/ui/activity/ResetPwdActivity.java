package com.rainowood.wltraffic.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.view.PasswordEditText;
import com.rainwood.tools.viewinject.ViewById;

import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2020/1/2 11:32
 * @Desc: 重置密码
 */
public final class ResetPwdActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @ViewById(R.id.btn_back)
    private Button btnBack;

    @ViewById(R.id.et_new_pwd)
    private PasswordEditText etNewPwd;
    @ViewById(R.id.et_new_pwd_again)
    private PasswordEditText etNewPwdAgain;
    @ViewById(R.id.btn_reset_confirm)
    private Button resetConfirm;

    @Override
    protected void initView() {
        btnBack.setOnClickListener(this);
        resetConfirm.setOnClickListener(this);
        etNewPwd.setFocusable(true);            // 进入页面聚焦
        etNewPwd.setFocusableInTouchMode(true);
    }

    @Override
    public void onClick(View v) {
        String newPwd = etNewPwd.getText().toString().trim();
        String newPwdAgain = etNewPwdAgain.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_reset_confirm:
                // 密码前端判断
                if (TextUtils.isEmpty(newPwd)) {
                    toast("请输入更改密码");
                    return;
                }
                if (TextUtils.isEmpty(newPwdAgain)) {
                    toast("请输入二次密码");
                    return;
                }
                if (!newPwd.equals(newPwdAgain)) {
                    toast("请保证两次输入密码一致");
                    return;
                }

                waitDialog();
                dialog.showDialog();
                new Thread(() -> RequestPost.resetPwd(newPwd, ResetPwdActivity.this)).start();
                break;
        }
    }

    private DialogUtils dialog;

    private void waitDialog() {
        dialog = new DialogUtils(this, "加载中");
    }

    private void dismissDialog() {
        postDelayed(() -> dialog.dismissDialog(), 500);
    }

    @Override
    public void onHttpFailure(HttpResponse result) {

    }

    @Override
    public void onHttpSucceed(HttpResponse result) {
        Map<String, String> body = JsonParser.parseJSONObject(result.body());
        if ("1".equals(body.get("code"))) {
            toast(body.get("warn"));
            postDelayed(() -> {
                dismissDialog();
                openActivity(HomeActivity.class);
            }, 500);
        } else {
            dismissDialog();
            toast(body.get("warn"));
        }
    }
}
