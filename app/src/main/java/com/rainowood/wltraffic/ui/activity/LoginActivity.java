package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.ui.dialog.WaitDialog;
import com.rainwood.tools.dialog.BaseDialog;
import com.rainwood.tools.view.ClearEditText;
import com.rainwood.tools.view.PasswordEditText;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: a797s
 * @Date: 2019/12/21 10:51
 * @Desc: 登录
 */
public final class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @ViewById(R.id.et_login_account)
    private ClearEditText account;
    @ViewById(R.id.et_login_password)
    private PasswordEditText password;
    @ViewById(R.id.tv_login_forget)
    private TextView forgetPwd;
    @ViewById(R.id.btn_login_commit)
    private Button loginCommit;

    @Override
    protected void initView() {
        forgetPwd.setOnClickListener(this);
        loginCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_forget:
                openActivity(CheckPhoneActivity.class);
                break;
            case R.id.btn_login_commit:
                String account = this.account.getText().toString().trim();
                String password = this.password.getText().toString().trim();


                showDialog();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void showDialog(){
        // 等待对话框
        final BaseDialog dialog = new WaitDialog.Builder(getActivity())
                // 文本消息
                .setMessage("加载中").show();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                // 验证之后跳转到首页
                openActivity(HomeActivity.class);
            }
        }, 2000);
    }
}
