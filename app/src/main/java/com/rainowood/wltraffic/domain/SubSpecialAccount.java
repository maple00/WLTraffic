package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/8 11:34
 * @Desc: 银行--转入专户资金
 */
public class SubSpecialAccount implements Serializable {

    private String money;               // 转入金额
    private String transfeTime;         // 时间
    private String text;                // 备注

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTransfeTime() {
        return transfeTime;
    }

    public void setTransfeTime(String transfeTime) {
        this.transfeTime = transfeTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
