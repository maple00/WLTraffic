package com.rainowood.wltraffic.request;

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
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=login", params, listener);
    }

    /**
     * 手机号请求验证码
     */
    public static void getVerfy(String tel, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("tel", tel);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=getCaptcha", params, listener);
    }

    /**
     * 验证码登录 captchaLogin
     */
    public static void checkCodeLogin(String captcha, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("captcha", captcha);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=captchaLogin", params, listener);
    }

    /**
     * 修改密码
     */
    public static void resetPwd(String password, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("password", password);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=passwordReset", params, listener);
    }

    /**
     * 请求首页数据
     */
    public static void getHomeDate(OnHttpListener listener) {
        RequestParams params = new RequestParams();
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=item", params, listener);
    }

    /**
     * 请求消息数据
     */
    public static void getMsgData(OnHttpListener listener){
        RequestParams params = new RequestParams();
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=backlog", params, listener);
    }

    /**
     * 推送id，访问消息详情
     * @param id 消息id
     */
    public static void getMsgDetailData(String id, OnHttpListener listener){
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=backlogMx", params, listener);
    }


    /**
     * 请求项目详情数据
     */
    public static void getItemDetailData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=itemMx", params, listener);
    }

    /**
     * 项目详情--计划管理
     */
    public static void getItemPlanManagerData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=planManage", params, listener);
    }

    /**
     * 项目建设程序
     */
    public static void getItemConstructionData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=procedure", params, listener);
    }

    /**
     * 项目进度管理
     */
    public static void getItemProgressData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=itemProcess", params, listener);
    }

    /**
     * 支付管理
     */
    public static void getItemPayManagerData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=terracePay", params, listener);
    }

    /**
     * 质量安全
     */
    public static void getItemQSManagerData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=inspectManager", params, listener);
    }

    /**
     * 质量安全详情
     */
    public static void getItemQSDetailData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=allNewsMx", params, listener);
    }

    /**
     * 变更管理
     */
    public static void getItemChangeManagerData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=changeManager", params, listener);
    }

    /**
     * 招投标
     */
    public static void getItemTenderData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=tendering", params, listener);
    }

    /**
     * 考核管理
     */
    public static void getItemAssessData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=testRule", params, listener);
    }

    /**
     * 农民工工资管理
     */
    // 工资保证金
    public static void getItemFarmerOneData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=nonWorkersState", params, listener);
    }

    // 实名制
    public static void getItemFarmerTwoData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=nonWorkersState1", params, listener);
    }

    // 专户制
    public static void getItemFarmerThreeData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=nonWorkersState2", params, listener);
    }

    // 银行代发制度
    public static void getItemFarmerFourData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=nonWorkersState3", params, listener);
    }

    // 银行代发制-某年数据
    public static void getItemFarmerYearData(String id, String year, String nowList, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        params.add("year", year);
        params.add("nowList", nowList);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=yearsData", params, listener);
    }

    // 劳资
    public static void getItemFarmerFiveData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=nonWorkersState4", params, listener);
    }

    // 通报
    public static void getItemFarmerSixData(String id, OnHttpListener listener) {
        RequestParams params = new RequestParams();
        params.add("id", id);
        OkHttp.post(Contants.BASE_URI + "library/mData.php?type=nonWorkersState5", params, listener);
    }

}
