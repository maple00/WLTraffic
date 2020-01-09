package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 11:46
 * @Desc: 银行代发制度--某年的月份的数据
 */
public class SubBankInfoYearInfo implements Serializable {

    private String money;               //金额
    private String month;               // 某月份
    private String type;                // 类型
    private String transfeTime;         // 交易时间
    private List<AttachBean> file;      // 附件


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransfeTime() {
        return transfeTime;
    }

    public void setTransfeTime(String transfeTime) {
        this.transfeTime = transfeTime;
    }

    public List<AttachBean> getFile() {
        return file;
    }

    public void setFile(List<AttachBean> file) {
        this.file = file;
    }
}
