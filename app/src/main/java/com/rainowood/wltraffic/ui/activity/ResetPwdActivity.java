package com.rainowood.wltraffic.ui.activity;

import android.view.View;
import android.widget.Button;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainwood.tools.view.PasswordEditText;
import com.rainwood.tools.viewinject.ViewById;

/**
 * @Author: a797s
 * @Date: 2020/1/2 11:32
 * @Desc: 重置密码
 */
public class ResetPwdActivity extends BaseActivity implements View.OnClickListener {

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

        //
        String newPwd = etNewPwd.getText().toString().trim();
        String newPwdAgain = etNewPwdAgain.getText().toString().trim();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_reset_confirm:
//                toast("确定了");
                openActivity(HomeActivity.class);
                break;
        }
    }
}
