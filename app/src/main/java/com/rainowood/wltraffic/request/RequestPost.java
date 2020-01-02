package com.rainowood.wltraffic.request;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.okhttp.HttpResponse;
import com.rainowood.wltraffic.okhttp.OkHttp;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.okhttp.RequestParams;

/**
 * @Author: a797s
 * @Date: 2020/1/2 16:24
 * @Desc: post请求
 */
public final class RequestPost {

    private static final String TAG = RequestPost.class.getSimpleName();

    /**
     * 登录
     */
    public static void Login(RequestParams params) {
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=login", params, new OnHttpListener() {
            @Override
            public void onHttpFailure(HttpResponse result) {
                Log.e(TAG, "request: " + result.toString());
            }

            @Override
            public void onHttpSucceed(HttpResponse result) {
                String body = result.body();
                Log.e(TAG, ": " + body);
            }
        });
    }
}
