package com.rainowood.wltraffic.ui.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.rainowood.wltraffic.R;
import com.rainowood.wltraffic.base.BaseActivity;
import com.rainowood.wltraffic.db.SQLiteHelper;
import com.rainowood.wltraffic.domain.UserInfoBean;
import com.rainowood.wltraffic.ui.fragment.PersonalFragment;
import com.rainowood.wltraffic.utils.DateTimeUtils;
import com.rainowood.wltraffic.utils.ListUtils;
import com.rainwood.tools.permission.OnPermission;
import com.rainwood.tools.permission.Permission;
import com.rainwood.tools.permission.XXPermissions;
import com.rainwood.tools.statusbar.StatusBarUtil;
import com.rainwood.tools.view.SmartTextView;
import com.rainwood.tools.viewinject.ViewById;

import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 17:49
 * @Desc: 启动页
 */
public final class SplashActivity extends BaseActivity implements Animation.AnimationListener, OnPermission {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    private static final int ANIM_TIME = 1000;      // 动画时长

    @ViewById(R.id.iv_splash_bg)
    private View splashBG;           // 背景
    @ViewById(R.id.iv_splash_icon)
    private View iconLogo;           // 启动logo
    @ViewById(R.id.tv_splash_copyright)
    private SmartTextView copyright;        // 版权

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        // 获取当前年
        copyright.setText("\u00a9" + DateTimeUtils.getNowYear() + "All Rights Reserved");
        // 初始化动画
        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f);
        aa.setDuration(ANIM_TIME * 2);
        aa.setAnimationListener(this);
        splashBG.startAnimation(aa);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIM_TIME);
        iconLogo.startAnimation(sa);

        RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(ANIM_TIME);
        copyright.startAnimation(ra);

        // 设置导航栏参数
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }

    /**
     * 数据库操作
     *
     * @param granted 请求成功的权限组
     * @param isAll   是否全部授予了
     */
    @Override
    public void hasPermission(List<String> granted, boolean isAll) {
        // 打开登录页或者HomeActivity
        SQLiteHelper liteHelper = SQLiteHelper.with(this, "traffic.db", 1);
        // 先判断表是否存在
        if (liteHelper.tabbleIsExist(UserInfoBean.class.getSimpleName())){
            List<UserInfoBean> list = liteHelper.query(UserInfoBean.class, "select * from " + UserInfoBean.class.getSimpleName());
            if (ListUtils.getSize(list) > 0){               // 登录过
                Bundle bundle = new Bundle();
                bundle.putSerializable("userInfo", list.get(0));
                PersonalFragment.newInstance(bundle);
                openActivity(HomeActivity.class);
                // postDelayed(() -> openActivity(HomeActivity.class), 500);
            }else {
                postDelayed(() -> openActivity(LoginActivity.class), 500);
            }
        }else {
            postDelayed(() -> openActivity(LoginActivity.class), 500);
        }
    }

    @Override
    public void noPermission(List<String> denied, boolean quick) {
        if (quick) {
            toast(R.string.common_permission_fail);
            XXPermissions.gotoPermissionSettings(SplashActivity.this, true);
        } else {
            toast(R.string.common_permission_hint);
            postDelayed(this::requestPermission, 1000);
        }
    }

    @Override
    public void onBackPressed() {
        // 禁用返回键
        //super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (XXPermissions.isHasPermission(SplashActivity.this, Permission.Group.STORAGE)) {
            hasPermission(null, true);
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        XXPermissions.with(this)
                .permission(Permission.Group.STORAGE)
                .request(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        requestPermission();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
