package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:04
 * @Desc: 招投标管理---代理公司
 */
public class SubTenderA implements Serializable {

    private String nameSix;         // 公司名字
    private String moneySix;        //  代理费用
    private String timeOneSix;      // 确认时间

    public String getNameSix() {
        return nameSix;
    }

    public void setNameSix(String nameSix) {
        this.nameSix = nameSix;
    }

    public String getMoneySix() {
        return moneySix;
    }

    public void setMoneySix(String moneySix) {
        this.moneySix = moneySix;
    }

    public String getTimeOneSix() {
        return timeOneSix;
    }

    public void setTimeOneSix(String timeOneSix) {
        this.timeOneSix = timeOneSix;
    }
}
