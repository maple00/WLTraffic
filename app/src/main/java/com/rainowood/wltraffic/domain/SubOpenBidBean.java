package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:45
 * @Desc: 招投标----开标
 */
public class SubOpenBidBean implements Serializable {

    private String nameSix;         // 开标公司
    private String moneySix;        // 中标金额
    private String timeOneSix;      // 开标时间

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
