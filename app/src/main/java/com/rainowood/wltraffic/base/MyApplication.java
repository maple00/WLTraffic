package com.rainowood.wltraffic.base;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.rainowood.wltraffic.helper.ActivityStackManager;
import com.rainowood.wltraffic.ui.activity.CrashActivity;
import com.rainowood.wltraffic.ui.activity.HomeActivity;
import com.rainwood.tools.toast.ToastInterceptor;
import com.rainwood.tools.toast.ToastUtils;
import com.tencent.smtt.sdk.QbSdk;

import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * @Author: shearson
 * @time: 2019/11/27 11:31
 * @des: Application 基类
 */
public class MyApplication extends Application {

    /**
     * 是否调试
     */
    private boolean isDebug = false;

    @Override
    public void onCreate() {
        super.onCreate();
        // initActivity 初始化Activity 栈管理
        initActivity();

        // 初始化三方的框架
        initSDK();

        // 初始化工具类
        initTools();
    }

    /**
     * 初始化活动
     */
    private void initActivity() {
        ActivityStackManager.getInstance().register(this);
    }

    /**
     * 创建Application 对象
     */
    public static MyApplication getInstance(){
        return Instance.INSTANCE;
    }

    private static class Instance{
        static MyApplication INSTANCE = new MyApplication();
    }

    public boolean isDebug() {
        return isDebug;
    }

    public boolean isDetermineNetwork() {
        return true;
    }

    /**
     * 初始化一些三方框架
     */
    private void initSDK() {
        // 本地异常捕捉
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
                .enabled(true)
                .trackActivities(true)
                .minTimeBetweenCrashesMs(2000)
                // 重启的 Activity
                .restartActivity(HomeActivity.class)
                // 错误的 Activity
                .errorActivity(CrashActivity.class)
                // 设置监听器
                //.eventListener(new YourCustomEventListener())
                .apply();

        // TBS 文件预览
        //初始化X5内核
        QbSdk.PreInitCallback callback = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("sxs", "加载内核是否成功:" + b);
            }
        };
        // 允许使用流量下载
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(this, callback);

    }

    /**
     * 初始化工具类
     */
    private void initTools(){
        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(new ToastInterceptor() {
            @Override
            public boolean intercept(Toast toast, CharSequence text) {
                boolean intercept = super.intercept(toast, text);
                if (intercept) {
                    Log.e("Toast", "空 Toast");
                } else {
                    Log.i("Toast", text.toString());
                }
                return intercept;
            }
        });
        // 吐司工具类
        ToastUtils.init(this);
    }
}
