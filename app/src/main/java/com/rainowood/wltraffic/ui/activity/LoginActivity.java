package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.App;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.db.SQLiteHelper;
import com.rainowood.wltraffic.domain.UserInfoBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.request.RequestPost;
import com.rainowood.wltraffic.ui.fragment.PersonalFragment;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.permission.OnPermission;
import com.rainwood.tools.permission.Permission;
import com.rainwood.tools.permission.XXPermissions;
import com.rainwood.tools.view.ClearEditText;
import com.rainwood.tools.view.PasswordEditText;
import com.rainwood.tools.viewinject.ViewById;

import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.helper.Logger;

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
        account.setText("");
        password.setText("");

        //从个人中心退出登录---- 判断用的类型
        String type = getIntent().getStringExtra("type");
        if (type != null){                  // 从个人中心跳转
            if ("平台".equals(type)){
                forgetPwd.setVisibility(View.VISIBLE);
            }else {
                forgetPwd.setVisibility(View.GONE);
            }
        }else {                 // 从启动页跳转
            forgetPwd.setVisibility(View.GONE);
        }
        /**
         * 请求必要的权限, 在启动页或者登录页面获取权限
         */
        // getMustPermission();
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

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {         // 回到Home页
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                toast("再按一次退出");
                mExitTime = System.currentTimeMillis();
                return false;
            } else {
                App.backHome(this);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
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
                    if (userInfo != null) {
                        userInfo.setAccount(account.getText().toString().trim());
                    }
                    if (userInfo != null) {
                        userInfo.setPassword(password.getText().toString().trim());
                    }
                    // 传递数据到个人中心
                    postDelayed(() -> {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userInfo", userInfo);
                        PersonalFragment.newInstance(bundle);
                        openActivity(HomeActivity.class);
                        dialog.dismissDialog();
                        // 登录成功，将注册极光
                        // 获取注册的唯一标识
                        JPushInterface.getRegistrationID(getApplicationContext());
                        //登录成功时,用户ID注册极光
                        if (userInfo != null) {
                            mHandler.sendMessage(mHandler.obtainMessage(0xAA, userInfo.getAdid()));
                        }
                        //SQLite-- 每次登录存储一条登录信息
                        SQLiteHelper liteHelper = SQLiteHelper.with(this, "traffic.db", 1);
                        liteHelper.createTable(UserInfoBean.class);
                        List<UserInfoBean> userList = liteHelper.query(UserInfoBean.class, "select * from " + UserInfoBean.class.getSimpleName());
                        // 没有则添加，有删除表中的数据再添加
                        if (ListUtils.getSize(userList) > 0){
                           liteHelper.deleteTable(UserInfoBean.class);
                        }
                        Log.e("sxs", "user:" + userInfo.toString());
                        ContentValues values = new ContentValues();
                        values.put("account", userInfo.getAccount());
                        values.put("password", userInfo.getPassword());
                        values.put("adid", userInfo.getAdid());
                        values.put("type", userInfo.getType());
                        values.put("adName", userInfo.getAdName());
                        values.put("sex", userInfo.getSex());
                        values.put("departmentName", userInfo.getDepartmentName());
                        values.put("address", userInfo.getAddress());
                        values.put("adtel", userInfo.getAdtel());
                        values.put("touxiang", userInfo.getTouxiang());
                        values.put("companyName", userInfo.getCompanyName());
                        liteHelper.insert(UserInfoBean.class.getSimpleName(), values);
                    }, 1000);
                }
            } else {
                dialog.dismissDialog();
                toast(body.get("warn"));
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0xAA) {
                Logger.d("TAG", "Set alias in handler.");
                // 调用 JPush 接口来设置别名。
                JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
            } else {
                Logger.i("TAG", "Unhandled msg - " + msg.what);
            }
        }
    };

    /**
     * /**
     * TagAliasCallback类是JPush开发包jar中的类，用于
     * 设置别名和标签的回调接口，成功与否都会回调该方法
     * 同时给定回调的代码。如果code=0,说明别名设置成功。
     * /**
     * 6001   无效的设置，tag/alias 不应参数都为 null
     * 6002   设置超时    建议重试
     * 6003   alias 字符串不合法    有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6004   alias超长。最多 40个字节    中文 UTF-8 是 3 个字节
     * 6005   某一个 tag 字符串不合法  有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6006   某一个 tag 超长。一个 tag 最多 40个字节  中文 UTF-8 是 3 个字节
     * 6007   tags 数量超出限制。最多 100个 这是一台设备的限制。一个应用全局的标签数量无限制。
     * 6008   tag/alias 超出总长度限制。总长度最多 1K 字节
     * 6011   10s内设置tag或alias大于3次 短时间内操作过于频繁
     **/

    private final TagAliasCallback mAliasCallback = (code, alias, tags) -> {
        String logs;
        switch (code) {
            case 0:
                logs = "Set tag and alias success";
                Logger.i("TAG", logs);
                // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                break;
            case 6002:
                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                Logger.i("TAG", logs);
                // 延迟 60 秒来调用 Handler 设置别名
                mHandler.sendMessageDelayed(mHandler.obtainMessage(0xAA, alias), 100 * 60);
                break;
            default:
                logs = "Failed with errorCode = " + code;
                Logger.e("TAG", logs);
        }

    };
}
