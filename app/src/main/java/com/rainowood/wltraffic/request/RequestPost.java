package com.rainowood.wltraffic.request;

import android.util.Log;

import com.rainowood.wltraffic.common.Contants;
import com.rainowood.wltraffic.okhttp.OkHttp;
import com.rainowood.wltraffic.okhttp.OnHttpListener;
import com.rainowood.wltraffic.okhttp.RequestParams;

/**
 * @Author: a797s
 * @Date: 2020/1/2 16:24
 * @Desc: post请求
 */
public final class RequestPost {

    /**
     * 登录
     */
    public static void Login(String account, String password, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("userName", account);
        params.add("password", password);
//      params.add(RequestParams.REQUEST_CONTENT_TYPE, RequestParams.REQUEST_CONTENT_JSON);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=login", params, listener);
    }

    /**
     * 手机号请求验证码
     */
    public static void getVerfy(String tel, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("tel", tel);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=getCaptcha", params, listener);
    }

    /**
     * 请求首页数据
     */
    public static void getHomeDate(OnHttpListener listener){
        RequestParams params = new RequestParams();
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=item", params, listener);
    }

    /**
     * 请求项目详情数据
     */
    public static void getItemDetailData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=itemMx", params, listener);
    }

    /**
     * 项目详情--计划管理
     */
    public static void getItemPlanManagerData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=planManage", params, listener);
    }

    /**
     * 项目建设程序
     */
    public static void getItemConstructionData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=procedure", params, listener);
    }

    /**
     * 项目进度管理
     */
    public static void getItemProgressData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=itemProcess", params, listener);
    }

    /**
     * 支付管理
     */
    public static void getItemPayManagerData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=terracePay", params, listener);
    }

    /**
     * 质量安全
     */


    /**
     * 变更管理
     */
    public static void getItemChangeManagerData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=changeManager", params, listener);
    }
}
