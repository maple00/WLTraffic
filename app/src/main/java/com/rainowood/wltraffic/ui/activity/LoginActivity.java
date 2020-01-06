package com.rainowood.wltraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.App;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.UserInfoBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.fragment.PersonalFragment;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.permission.OnPermission;
import com.rainwood.tools.permission.Permission;
import com.rainwood.tools.permission.XXPermissions;
import com.rainwood.tools.view.ClearEditText;
import com.rainwood.tools.view.PasswordEditText;
import com.rainwood.tools.viewinject.ViewById;

import java.util.List;
import java.util.Map;

/**
 * @Author: a797s
 * @Date: 2019/12/21 10:51
 * @Desc: 登录
 */
public final class LoginActivity extends BaseActivity implements View.OnClickListener, OnHttpListener {

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

    DialogUtils dialog;

    @Override
    protected void initView() {
        forgetPwd.setOnClickListener(this);
        loginCommit.setOnClickListener(this);
        dialog = new DialogUtils(this, "登录中");
        account.setText("lei");
        password.setText("123456");

        /**
         * 请求必要的权限
         */
        getMustPermission();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget:              // 忘记密码
                Contants.CHANGEFLAG = 0;
                openActivity(CheckPhoneActivity.class);
                break;
            case R.id.btn_login_commit:                 // 登录
                if (TextUtils.isEmpty(account.getText())) {
                    toast("账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    toast("密码不能为空");
                    return;
                }
                dialog = new DialogUtils(this, "登录中");
                dialog.showDialog();
                String account = this.account.getText().toString().trim();
                String password = this.password.getText().toString().trim();
                RequestPost.Login(account, password, LoginActivity.this);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismissDialog();
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
                if (result.url().contains("library/mData.php?type=login")) {
                    final UserInfoBean userInfo = JsonParser.parseJSONObject(UserInfoBean.class, body.get("data"));
                    postDelayed(new Runnable() {            // 传递数据到个人中心
                        @Override
                        public void run() {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userInfo", userInfo);
                            PersonalFragment.newInstance(bundle);
                            openActivity(HomeActivity.class);
                            dialog.dismissDialog();
                        }
                    }, 1000);
                }
            } else {
                dialog.dismissDialog();
                toast(body.get("warn"));
            }
        }
    }

    /**
     *  获取必要的权限，没用获取完，则不让登录
     */
    private void getMustPermission() {
        XXPermissions.with(this)
                .constantRequest()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)            // 文件的读取权限
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            //toast("获取权限成功");
                            //openActivity(TbsActivity.class);
                        } else {
                            toast("权限未授予成功！该应用将不能完整运行");
                            // 退出App , 如果当前App在前台运行
                            if (App.isRunningForeground(LoginActivity.this, "com.rainowood.wltraffic")){
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            toast("被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(getActivity());
                        } else {
                            toast("获取权限失败");
                        }
                    }
                });
    }

}
