package com.rainowood.wltraffic.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.domain.UserInfoBean;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.JsonParser;
import com.rainowood.wltraffic.okhttp.OkHttp;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.okhttp.RequestParams;
import com.rainowood.wltraffic.utils.DialogUtils;
import com.rainwood.tools.view.ClearEditText;
import com.rainwood.tools.view.PasswordEditText;
import com.rainwood.tools.viewinject.ViewById;

import java.util.Map;

import static com.rainowood.wltraffic.common.Contants.userInfo;

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

    DialogUtils dialog;

    @Override
    protected void initView() {
        forgetPwd.setOnClickListener(this);
        loginCommit.setOnClickListener(this);
        dialog = new DialogUtils(this, "登录中");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget:              // 忘记密码
                Contants.CHANGEFLAG = 0;
                openActivity(CheckPhoneActivity.class);
                break;
            case R.id.btn_login_commit:                 // 登录
                String account = this.account.getText().toString().trim();
                String password = this.password.getText().toString().trim();
                RequestParams params = new RequestParams();
                params.add("userName", account);
                params.add("password", password);
                dialog.showDialog();
                Login(params);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    /**
     * 登录
     */
    public void Login(RequestParams params) {
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=login", params, new OnHttpListener() {
            @Override
            public void onHttpFailure(HttpResponse result) {
                dialog.dismissDialog();
                Log.d(TAG, result.toString());
            }

            @Override
            public void onHttpSucceed(HttpResponse result) {
                String body = result.body();
                Log.e(TAG, body);
                Map<String, String> map = JsonParser.parseJSONObject(body);
                String code = null;
                if (map != null) {
                    code = map.get("code");
                }
                if ("1".equals(code)) {
                    Map<String, String> data = JsonParser.parseJSONObject(map.get("data"));
                    String type = data.get("type");         // 用户类型
                    String name = data.get("adName");       // 姓名
                    String sex = data.get("sex");           // 性别
                    String post = data.get("departmentName");     // 职位
                    String tel = data.get("adtel");
                    // 赋值
                    userInfo = new UserInfoBean();
                    userInfo.setType(type);
                    userInfo.setUserName(name);
                    userInfo.setUserSex(sex);
                    userInfo.setPost(post);
                    userInfo.setTel(tel);
                    postDelayed(new Runnable() {        // 延时加载
                        @Override
                        public void run() {
                            openActivity(HomeActivity.class);
                        }
                    }, 1000);
                } else {
                    dialog.dismissDialog();
                    toast(map.get("warn"));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null){
            dialog.dismissDialog();
        }
    }
}
