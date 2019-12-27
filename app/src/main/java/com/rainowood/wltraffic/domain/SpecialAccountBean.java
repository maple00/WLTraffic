package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:36
 * @Desc: 专户制
 */
public class SpecialAccountBean implements Serializable {

    private String money;
    private String time;
    private String note;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
