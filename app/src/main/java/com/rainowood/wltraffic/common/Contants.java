package com.rainowood.wltraffic.common;

import com.rainowood.wltraffic.domain.UserInfoBean;

/**
 * @Author: a797s
 * @Date: 2019/12/21 14:03
 * @Desc: 常量类
 */
public final class Contants {

    /*** 后台访问*/
    // 根URI
    public static final String BASE_URI = "https://www.yumukeji.cn/project/wljtj/";
    // 测试接口
    public static final String PRE_URI = "library/mData.php?type=test";

    /**
     * 记录之前输入过的手机号
     */
    // 记录之前输入的手机号
    public static String PhoneCheckVerify = null;
    /**
     * 标记改变
     * 0：修改密码
     * 1：修改手机号
     */
    public static int CHANGEFLAG = -1;

    // 文件类型相关
    /** 文本 */
    public static final String TXT = "txt";
    /** 图片 */
    public static final String PICTURE = "picture";
    /** 音频 */
    public static final String VOICE = "voice";
    /** 视频 */
    public static final String VIDEO = "video";
    // 常量相关
    /** 索引 */
    public static final String INDEX = "index";

    /**
     * 请求失败
     */
    public static final String HTTP_MSG_RESPONSE_FAILED = "The request data failed and the response code is not 200,code = ";


    /**
     * 用户
     */
    public static UserInfoBean userInfo;
}
